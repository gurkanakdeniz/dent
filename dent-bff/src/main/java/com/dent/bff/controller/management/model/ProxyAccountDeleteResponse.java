package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseResponse;

public class ProxyAccountDeleteResponse extends BaseResponse {
    private String id;

    public ProxyAccountDeleteResponse() {
        super();
    }

    public ProxyAccountDeleteResponse(String id) {
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
