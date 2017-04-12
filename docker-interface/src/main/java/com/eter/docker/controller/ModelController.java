package com.eter.docker.controller;


import com.eter.docker.controller.response.AvailableCommands;
import com.eter.docker.controller.response.ModelPath;
import com.eter.docker.controller.response.ModelStatus;
import com.eter.docker.domain.Model;
import com.eter.docker.qualifier.Active;
import com.eter.docker.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ModelController {
    private ModelService modelService;
    private AvailableCommands availableCommands;

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Autowired
    @Active
    public void setAvailableCommands(AvailableCommands availableCommands) {
        this.availableCommands = availableCommands;
    }

    @RequestMapping(value = "/",
            produces = {"application/json", "application/xml"},
            method = RequestMethod.GET)
    public ResponseEntity<AvailableCommands> getRoot() {
        return ResponseEntity.ok(availableCommands);
    }

    @RequestMapping(value = "/model",
        produces = {"application/json", "application/xml"},
        method = RequestMethod.GET)
    public ResponseEntity<List<Model>> getModels() {
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @RequestMapping(value = "/model/{name}",
        produces = {"application/json", "application/xml"},
        method = RequestMethod.GET)
    public ResponseEntity<Model> getModel(@PathVariable String name) {
        return ResponseEntity.ok(modelService.findModel(name));
    }

    @RequestMapping(value = "/model/status/{name}",
        produces = {"application/json", "application/xml"},
        method = RequestMethod.GET)
    public ResponseEntity<ModelStatus> getModelStatus(@PathVariable String name) {
        return ResponseEntity.ok(ModelStatus.transfromFromModel(
                modelService.findModel(name)
        ));
    }

    @RequestMapping(value = "/model/path/{name}",
            produces = {"application/json", "application/xml"},
            method = RequestMethod.GET)
    public ResponseEntity<ModelPath> getModelPath(@PathVariable String name) {
        return ResponseEntity.ok(new ModelPath(name, modelService.getModelPath(name)));
    }

}
