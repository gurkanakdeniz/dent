package com.dent.iext.controller.user.model;

import com.dent.iext.common.model.BaseResponse;

public class ExtractionResponse extends BaseResponse {

    private String id;

    public ExtractionResponse() {
        super();
    }

    public ExtractionResponse(String id) {
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
