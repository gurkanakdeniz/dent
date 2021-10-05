package com.dent.crawler.service.job.model;

import com.dent.crawler.core.job.model.JobStatus;
import com.dent.crawler.service.common.model.BaseRequest;

public class RequestUpdate extends BaseRequest {

    private static final long serialVersionUID = 2425372221358206433L;
    
    private String jobId;
    private JobStatus jobStatus;

    public RequestUpdate(String jobId, JobStatus jobStatus) {
        super();
        this.jobId = jobId;
        this.jobStatus = jobStatus;
    }

    public String getJobId() {
        return jobId;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

}
