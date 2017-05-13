package com.eter.docker.domain;

/**
 * Created by rusifer on 5/13/17.
 */
public interface ApplicationCallback {
    void callback(Application application, StatusResponse statusResponse);
}
