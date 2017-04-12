package com.eter.docker.service;

import com.eter.docker.domain.Model;

import java.util.List;

public interface ModelService {
    List<Model> getAllModels();

    List<Model> getCompleteModels();

    Model findModel(String name);

    String getModelPath(String name);

    boolean isModelComplete(String name);

    boolean isModelInProcessing(String name);
}
