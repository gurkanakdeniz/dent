package com.dent.bff.service.remote.model;

import java.util.List;

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
