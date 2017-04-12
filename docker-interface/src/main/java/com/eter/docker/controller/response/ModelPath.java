package com.eter.docker.controller.response;

/**
 * Created by abosii on 4/12/2017.
 */
public class ModelPath {
    private String name;
    private String path;

    public ModelPath() {
    }

    public ModelPath(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
