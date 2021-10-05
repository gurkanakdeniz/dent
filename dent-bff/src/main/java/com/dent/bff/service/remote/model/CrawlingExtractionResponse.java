package com.dent.bff.service.remote.model;

public class CrawlingExtractionResponse extends BaseResponse {

    private String id;

    public CrawlingExtractionResponse() {
        super();
    }

    public CrawlingExtractionResponse(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
