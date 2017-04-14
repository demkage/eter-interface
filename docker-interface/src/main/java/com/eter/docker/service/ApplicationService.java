package com.eter.docker.service;

import com.eter.docker.domain.Application;

import java.util.List;

/**
 * Created by abosii on 4/14/2017.
 */
public interface ApplicationService {

    List<Application> getApplicationStash();

    Application findApplication(String name);

    boolean exist(String name);
}
