package com.eter.docker.service;

import com.eter.docker.domain.Application;
import com.eter.docker.domain.ApplicationCallback;

/**
 * Created by rusifer on 5/13/17.
 */
public interface ApplicationUpdateService {
    boolean submitForUpdate(Application application, ApplicationCallback applicationCallback);

    Application getUpdateForApplication(String applicationName);

    boolean dismissForUpdate(String applicationName);
}
