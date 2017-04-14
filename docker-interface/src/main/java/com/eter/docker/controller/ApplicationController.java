package com.eter.docker.controller;

import com.eter.docker.domain.Application;
import com.eter.docker.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by abosii on 4/14/2017.
 */
@RestController
public class ApplicationController {
    private ApplicationService applicationService;

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping(value = "/application",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            method = RequestMethod.GET)
    public ResponseEntity<List<Application>> getApplicationsList() {
        return ResponseEntity.ok(applicationService.getApplicationStash());
    }

    @RequestMapping(value = "/application/{name}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            method = RequestMethod.GET)
    public ResponseEntity<Application> getApplication(@PathVariable String name) {
        return ResponseEntity.ok(applicationService.findApplication(name));
    }
}
