package com.eter.docker.service.impl;

import com.eter.docker.domain.*;
import com.eter.docker.repository.ApplicationRepository;
import com.eter.docker.service.ApplicationService;
import com.eter.docker.service.RESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abosii on 4/14/2017.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;
    private ApplicationSubmissionBuilder applicationSubmissionBuilder;
    private RESTService restService;

    @Autowired
    public void setApplicationRepository(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Autowired
    public void setApplicationSubmissionBuilder(ApplicationSubmissionBuilder applicationSubmissionBuilder) {
        this.applicationSubmissionBuilder = applicationSubmissionBuilder;
    }

    @Autowired
    public void setRestService(RESTService restService) {
        this.restService = restService;
    }

    @Override
    public List<Application> getApplicationStash() {
        return applicationRepository.findAll();
    }

    @Override
    public Application findApplication(String name) {
        return applicationRepository.findOneByName(name);
    }

    @Override
    public boolean exist(String name) {
        return applicationRepository.existsByName(name);
    }

    @Override
    public SubmissionResponse submit(String name) {
        Application application = applicationRepository.findOneByName(name);

        if (application == null) {
            SubmissionResponse submissionResponse = new SubmissionResponse();
            submissionResponse.setAction("CreateSubmissionResponse");
            submissionResponse.setMessage("Error. Can't find application with name '" + name + "'");
            submissionResponse.setServerSparkVersion("error");
            submissionResponse.setSubmissionId("error");
            submissionResponse.setSuccess(false);
            return submissionResponse;
        }

        ApplicationSubmission applicationSubmission = applicationSubmissionBuilder.build(application);
        SubmissionResponse submissionResponse = restService.submit(applicationSubmission);
        if (submissionResponse.isSuccess()) {
            application.setExecutionDriver(submissionResponse.getSubmissionId());
            applicationRepository.save(application);
        }

        return submissionResponse;
    }

    @Override
    public StatusResponse check(String name) {
        Application application = applicationRepository.findOneByName(name);

        if (application == null || application.getExecutionDriver() == null) {
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setAction("SubmissionStatusResponse");
            statusResponse.setWorkerHostPort("error");

            if (application == null)
                statusResponse.setDriverState("Can't find application");
            else
                statusResponse.setDriverState("No submitted application");

            statusResponse.setWorkerId("error");
            statusResponse.setServerSparkVersion("error");
            statusResponse.setSubmissionId("error");
            statusResponse.setSuccess(false);
            return statusResponse;
        }

        return restService.check(application.getExecutionDriver());
    }
}
