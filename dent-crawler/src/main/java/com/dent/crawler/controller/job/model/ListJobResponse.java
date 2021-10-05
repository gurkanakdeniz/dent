package com.dent.crawler.controller.job.model;

import java.util.List;

import com.dent.crawler.common.model.BaseResponse;
import com.dent.crawler.core.job.model.CrawlingJobModel;

public class ListJobResponse extends BaseResponse {

    private static final long serialVersionUID = 8735845330419005253L;
    
    private List<CrawlingJobModel> crawlingJobs;

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
