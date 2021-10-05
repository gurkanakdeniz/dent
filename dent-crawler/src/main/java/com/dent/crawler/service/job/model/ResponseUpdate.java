package com.dent.crawler.service.job.model;

import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseUpdate extends BaseResponse {

    private static final long serialVersionUID = 8712648114800891958L;
    
    private String jobId;

    public ResponseUpdate(String jobId) {
        super();
        this.jobId = jobId;
    }

    public String getJobId() {
        return jobId;
    }

}
