package com.eter.docker.service.impl;

import com.eter.docker.domain.Application;
import com.eter.docker.service.ApplicationService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by abosii on 4/14/2017.
 */
@Component
public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public List<Application> getApplicationStash() {
        return null;
    }

    @Override
    public Application findApplication(String name) {
        return null;
    }

    @Override
    public boolean exist(String name) {
        return false;
    }
}
