package com.dent.bff.controller.view.model;

import java.util.List;

import com.dent.bff.common.model.BaseResponse;

public class ListResponse extends BaseResponse {

    private List<JobModel> crawlingJobs;

    public ListResponse() {
        super();
    }

    public ListResponse(List<JobModel> crawlingJobs) {
        super();
        this.crawlingJobs = crawlingJobs;
    }

    public List<JobModel> getCrawlingJobs() {
        return crawlingJobs;
    }

    public void setCrawlingJobs(List<JobModel> crawlingJobs) {
        this.crawlingJobs = crawlingJobs;
    }

}
