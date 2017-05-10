package com.eter.docker.domain;

/**
 * Created by rusifer on 5/9/17.
 */
public class StatusResponse {
    private String action;
    private String driverState;
    private String serverSparkVersion;
    private String submissionId;
    private String workerHostPort;
    private String workerId;
    private boolean success;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDriverState() {
        return driverState;
    }

    public void setDriverState(String driverState) {
        this.driverState = driverState;
    }

    public String getServerSparkVersion() {
        return serverSparkVersion;
    }

    public void setServerSparkVersion(String serverSparkVersion) {
        this.serverSparkVersion = serverSparkVersion;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getWorkerHostPort() {
        return workerHostPort;
    }

    public void setWorkerHostPort(String workerHostPort) {
        this.workerHostPort = workerHostPort;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
