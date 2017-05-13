package com.eter.docker.service.impl;

import com.eter.docker.domain.ApplicationSubmission;
import com.eter.docker.domain.StatusResponse;
import com.eter.docker.domain.SubmissionResponse;
import com.eter.docker.service.RESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rusifer on 5/9/17.
 */
@Service
public class RESTServiceImpl implements RESTService {
    private RestTemplate restTemplate;
    private String sparkSubmitUrl;
    private String sparkStatusUrl;
    private SyncStack syncStack = new SyncStack();

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setSparkSubmitUrl(@Value("${eter.spark.rest.submit}") String sparkSubmitUrl) {
        this.sparkSubmitUrl = sparkSubmitUrl;
    }

    @Autowired
    public void setSparkStatusUrl(@Value("${eter.spark.rest.status}") String sparkStatusUrl) {
        this.sparkStatusUrl = sparkStatusUrl;
    }

    @Override
    public SubmissionResponse submit(ApplicationSubmission applicationSubmission) {
        try {
            syncStack.getRestService();
            return restTemplate.postForObject(sparkSubmitUrl, applicationSubmission, SubmissionResponse.class);
        } finally {
            syncStack.releaseRestService();
        }
    }

    @Override
    public StatusResponse check(String driverId) {
        try {
            syncStack.getRestService();
            return restTemplate.getForObject(sparkStatusUrl + driverId, StatusResponse.class);
        } finally {
            syncStack.releaseRestService();
        }
    }

    private class SyncStack {
        private ReentrantLock restLock = new ReentrantLock();

        public void getRestService() {
            restLock.lock();
        }
        public void releaseRestService() {
            restLock.unlock();
        }
    }
}
