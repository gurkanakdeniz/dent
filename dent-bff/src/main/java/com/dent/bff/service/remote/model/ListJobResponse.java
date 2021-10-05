package com.dent.bff.service.remote.model;

import java.util.List;

public class ListJobResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private List<CrawlingJobModel> crawlingJobs;

    public ListJobResponse() {
        super();
    }

    public ListJobResponse(List<CrawlingJobModel> crawlingJobs) {
        super();
        this.crawlingJobs = crawlingJobs;
    }

    public List<CrawlingJobModel> getCrawlingJobs() {
        return crawlingJobs;
    }

    public void setCrawlingJobs(List<CrawlingJobModel> crawlingJobs) {
        this.crawlingJobs = crawlingJobs;
    }

}
