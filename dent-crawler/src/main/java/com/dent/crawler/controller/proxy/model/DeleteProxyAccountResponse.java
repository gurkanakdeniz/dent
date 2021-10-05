package com.dent.crawler.controller.proxy.model;

import com.dent.crawler.common.model.BaseResponse;

public class DeleteProxyAccountResponse extends BaseResponse {

    private static final long serialVersionUID = 8089802405909307060L;
    
    private String id;

    public DeleteProxyAccountResponse() {
        super();
    }

    public DeleteProxyAccountResponse(String id) {
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
