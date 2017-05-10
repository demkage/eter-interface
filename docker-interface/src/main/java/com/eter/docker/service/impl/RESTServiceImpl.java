package com.eter.docker.service.impl;

import com.eter.docker.domain.ApplicationSubmission;
import com.eter.docker.domain.StatusResponse;
import com.eter.docker.domain.SubmissionResponse;
import com.eter.docker.service.RESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rusifer on 5/9/17.
 */
@Service
public class RESTServiceImpl implements RESTService {
    private RestTemplate restTemplate;
    private String sparkSubmitUrl;
    private String sparkStatusUrl;

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
        return restTemplate.postForObject(sparkSubmitUrl, applicationSubmission, SubmissionResponse.class);
    }

    @Override
    public StatusResponse check(String driverId) {
        return restTemplate.getForObject(sparkStatusUrl + driverId, StatusResponse.class);
    }
}
