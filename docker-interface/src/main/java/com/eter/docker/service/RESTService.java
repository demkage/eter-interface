package com.eter.docker.service;

import com.eter.docker.domain.ApplicationSubmission;
import com.eter.docker.domain.StatusResponse;
import com.eter.docker.domain.SubmissionResponse;

/**
 * Created by rusifer on 5/9/17.
 */
public interface RESTService {
    SubmissionResponse submit(ApplicationSubmission applicationSubmission);

    StatusResponse check(String driverId);
}
