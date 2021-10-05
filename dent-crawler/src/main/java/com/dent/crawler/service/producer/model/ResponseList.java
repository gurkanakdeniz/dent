package com.dent.crawler.service.producer.model;

import java.util.List;

import com.dent.crawler.core.crawling.model.CrawlingDataModel;
import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseList extends BaseResponse {

    private List<CrawlingDataModel> crawlings;

    public ResponseList() {
        super();
    }

    public ResponseList(List<CrawlingDataModel> crawlings) {
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
