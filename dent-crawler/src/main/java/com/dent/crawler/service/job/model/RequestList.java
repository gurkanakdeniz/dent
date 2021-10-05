package com.dent.crawler.service.job.model;

import com.dent.crawler.core.job.model.JobStatus;
import com.dent.crawler.service.common.model.BaseRequest;

public class RequestList extends BaseRequest {

    private static final long serialVersionUID = -3404834976304601044L;

    private String jobId;

    private JobStatus jobStatus;

    public RequestList() {
        super();
    }

    public RequestList(JobStatus jobStatus) {
        super();
        this.jobStatus = jobStatus;
    }

    public RequestList(String jobId) {
        super();
        this.jobId = jobId;
    }

    public RequestList(String jobId, JobStatus jobStatus) {
        super();
        this.jobId = jobId;
        this.jobStatus = jobStatus;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
