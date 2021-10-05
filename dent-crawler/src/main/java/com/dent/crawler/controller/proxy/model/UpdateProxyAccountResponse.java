package com.dent.crawler.controller.proxy.model;

import com.dent.crawler.common.model.BaseResponse;

public class UpdateProxyAccountResponse extends BaseResponse {

    private static final long serialVersionUID = -6203345786142434553L;

    private String id;

    public UpdateProxyAccountResponse() {
        super();
    }

    public UpdateProxyAccountResponse(String id) {
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
