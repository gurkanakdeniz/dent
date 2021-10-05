package com.dent.crawler.service.job.model;

import java.util.List;

import com.dent.crawler.core.job.model.CrawlingJobModel;
import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseList extends BaseResponse {

    private static final long serialVersionUID = 155719564922188188L;
    
    private List<CrawlingJobModel> crawlingJobs;

    public ResponseList(List<CrawlingJobModel> crawlingJobs) {
        super();
        this.crawlingJobs = crawlingJobs;
    }

    public List<CrawlingJobModel> getCrawlingJobs() {
        return crawlingJobs;
    }

}
