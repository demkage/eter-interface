package com.eter.docker.domain;

import javax.persistence.*;


@Entity
@Table(name = "applications")
public class Application {
    private Integer id;
    private String name;
    private String mainClass;
    private String appArgs;
    private String appPath;
    private String executionDriver;
    private Model model;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "mainclass")
    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    @Column(name = "appArgs")
    public String getAppArgs() {
        return appArgs;
    }

    public void setAppArgs(String appArgs) {
        this.appArgs = appArgs;
    }

    @Column(name = "appPath")
    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    @Column(name = "executionDriver", nullable = true)
    public String getExecutionDriver() {
        return executionDriver;
    }

    public void setExecutionDriver(String executionDriver) {
        this.executionDriver = executionDriver;
    }

    @OneToOne
    @JoinColumn(referencedColumnName = "id", nullable = true, name = "modelid")
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
