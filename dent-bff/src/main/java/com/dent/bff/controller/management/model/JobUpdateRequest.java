package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseRequest;
import com.dent.bff.service.remote.model.JobStatus;

public class JobUpdateRequest extends BaseRequest {

    private JobStatus status;

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

}
