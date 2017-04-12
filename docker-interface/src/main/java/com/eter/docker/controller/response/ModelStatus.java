package com.eter.docker.controller.response;

import com.eter.docker.domain.Model;

/**
 * Created by abosii on 4/12/2017.
 */
public class ModelStatus {
    private String name;
    private String status;
    private boolean completed;

    public ModelStatus() {
        status = "Unknown Model";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ModelStatus transfromFromModel(Model model) {
        if(model == null) {
            return new ModelStatus();
        } else {
            ModelStatus status = new ModelStatus();
            status.setStatus(model.getStatus());
            status.setCompleted(model.getComplete());
            status.setName(model.getName());

            return status;
        }
    }
}
