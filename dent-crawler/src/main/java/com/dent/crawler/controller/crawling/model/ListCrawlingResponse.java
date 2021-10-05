package com.dent.crawler.controller.crawling.model;

import java.util.List;

import com.dent.crawler.common.model.BaseResponse;
import com.dent.crawler.core.crawling.model.CrawlingDataModel;

public class ListCrawlingResponse extends BaseResponse {

    private List<CrawlingDataModel> crawlings;

    public ListCrawlingResponse() {
        super();
    }

    public ListCrawlingResponse(List<CrawlingDataModel> crawlings) {
        super();
        this.crawlings = crawlings;
    }

    public List<CrawlingDataModel> getCrawlings() {
        return crawlings;
    }

    public void setCrawlings(List<CrawlingDataModel> crawlings) {
        this.crawlings = crawlings;
    }

}
