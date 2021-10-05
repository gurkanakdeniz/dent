package com.dent.crawler.controller.proxy.model;

import com.dent.crawler.common.model.BaseResponse;

public class AddProxyAccountResponse extends BaseResponse {

    private static final long serialVersionUID = -6277746070836129818L;
    
    private String id;

    public AddProxyAccountResponse() {
        super();
    }

    public AddProxyAccountResponse(String id) {
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
