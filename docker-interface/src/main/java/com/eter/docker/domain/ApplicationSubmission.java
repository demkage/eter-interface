package com.eter.docker.domain;

import java.util.Map;

/**
 * Created by rusifer on 5/9/17.
 */
public class ApplicationSubmission {
    private String action;
    private String[] appArgs;
    private String appResource;
    private String clientSparkVersion;
    private Map<String, String> environmentVariables;
    private String mainClass;
    private Map<String, String> sparkProperties;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String[] getAppArgs() {
        return appArgs;
    }

    public void setAppArgs(String[] appArgs) {
        this.appArgs = appArgs;
    }

    public String getAppResource() {
        return appResource;
    }

    public void setAppResource(String appResource) {
        this.appResource = appResource;
    }

    public String getClientSparkVersion() {
        return clientSparkVersion;
    }

    public void setClientSparkVersion(String clientSparkVersion) {
        this.clientSparkVersion = clientSparkVersion;
    }

    public Map<String, String> getEnvironmentVariables() {
        return environmentVariables;
    }

    public void setEnvironmentVariables(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public Map<String, String> getSparkProperties() {
        return sparkProperties;
    }

    public void setSparkProperties(Map<String, String> sparkProperties) {
        this.sparkProperties = sparkProperties;
    }


    public static ApplicationSubmission build(Application application) {
        return new ApplicationSubmissionBuilder().build(application);
    }


}
