package com.eter.docker.service.impl;

import com.eter.docker.domain.Application;
import com.eter.docker.domain.ApplicationCallback;
import com.eter.docker.domain.StatusResponse;
import com.eter.docker.service.ApplicationUpdateService;
import com.eter.docker.service.RESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by rusifer on 5/13/17.
 */
@Service
public class ApplicationUpdateServiceImpl implements ApplicationUpdateService {
    private Map<String, ApplicationHolder> applicationMap = new ConcurrentHashMap<>();
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private RESTService restService;
    private Long updateDelay;

    @Autowired
    public void setThreadPoolTaskScheduler(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    @Autowired
    public void setRestService(RESTService restService) {
        this.restService = restService;
    }

    @Autowired
    public void setUpdateDelay(@Value("${eter.interface.appUpdateDelay:5000}") Long updateDelay) {
        this.updateDelay = updateDelay;
    }

    @Override
    public boolean submitForUpdate(Application application, ApplicationCallback applicationCallback) {
        applicationMap.put(application.getName(), new ApplicationHolder(
                threadPoolTaskScheduler.scheduleWithFixedDelay(
                        new UpdateApplicationTask(application, restService, applicationCallback),
                updateDelay), application));

        return true;
    }



    @Override
    public Application getUpdateForApplication(String applicationName) {
        return applicationMap.get(applicationName).getApplication();
    }

    @Override
    public boolean dismissForUpdate(String applicationName) {
        ScheduledFuture future = applicationMap.get(applicationName).getScheduledFuture();
        return future.cancel(true);
    }

    private class ApplicationHolder {
        private ScheduledFuture scheduledFuture;
        private Application application;

        public ApplicationHolder(ScheduledFuture scheduledFuture, Application application) {
            this.scheduledFuture = scheduledFuture;
            this.application = application;
        }

        public Application getApplication() {
            return application;
        }

        public ScheduledFuture getScheduledFuture() {
            return scheduledFuture;
        }
    }

    private class UpdateApplicationTask implements Runnable {
        private Application application;
        private String applicationName;
        private RESTService restService;
        private ApplicationCallback applicationCallback;

        public UpdateApplicationTask(Application application, RESTService restService, ApplicationCallback callback) {
            this.application = application;
            this.applicationName = application.getName();
            this.restService = restService;
            this.applicationCallback = callback;

        }

        @Override
        public void run() {
            if(application.getExecutionDriver() != null | !application.getExecutionDriver().equals("")) {
                StatusResponse status = restService.check(application.getExecutionDriver());
                updateApplicationForStatus(status);
                if(status.getDriverState().equals("FINISHED") | status.getDriverState().equals("FAILED"))
                    applicationCallback.callback(application, status);
            }
        }

        public String getApplicationName() {
            return applicationName;
        }

        private void updateApplicationForStatus(StatusResponse statusResponse) {
            if(statusResponse.isSuccess()) {
                if(application.getModel() != null) {
                    updateApplicationModel(application, statusResponse);
                }
            }
        }

        private void updateApplicationModel(Application application, StatusResponse statusResponse) {
            if(application.getModel() != null) {
                application.getModel().setStatus(statusResponse.getDriverState());
                if(statusResponse.getDriverState().equals("FINISHED")) {
                    application.getModel().setComplete(true);
                    application.getModel().setEndTime(LocalDateTime.now());
                } else {
                    application.getModel().setComplete(false);
                }
            }
        }

    }

}
