package com.dent.crawler.controller.job.model;

import com.dent.crawler.common.model.BaseRequest;
import com.dent.crawler.core.job.model.JobStatus;

public class UpdateJobRequest extends BaseRequest {

    private static final long serialVersionUID = 4269333180802989506L;

    private JobStatus status;

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

}
