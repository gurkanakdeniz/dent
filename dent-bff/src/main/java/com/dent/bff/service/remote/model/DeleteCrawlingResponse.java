package com.dent.bff.service.remote.model;

public class DeleteCrawlingResponse extends BaseResponse {

    private String id;

    public DeleteCrawlingResponse() {
        super();
    }

    public DeleteCrawlingResponse(String id) {
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
