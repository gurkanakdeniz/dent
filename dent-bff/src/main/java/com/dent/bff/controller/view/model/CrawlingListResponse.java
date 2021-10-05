package com.dent.bff.controller.view.model;

import java.util.List;

import com.dent.bff.common.model.BaseResponse;

public class CrawlingListResponse extends BaseResponse {

    private List<CrawlingModel> crawlings;

    public CrawlingListResponse() {
        super();
    }

    public CrawlingListResponse(List<CrawlingModel> crawlings) {
        super();
        this.crawlings = crawlings;
    }

    public List<CrawlingModel> getCrawlings() {
        return crawlings;
    }

    public void setCrawlings(List<CrawlingModel> crawlings) {
        this.crawlings = crawlings;
    }

}
