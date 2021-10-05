package com.dent.bff.service.remote.model;

public class UpdateJobRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    private JobStatus status;

    public UpdateJobRequest() {
        super();
    }

    public UpdateJobRequest(JobStatus status) {
        super();
        this.status = status;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

}
