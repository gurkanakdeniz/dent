package com.dent.crawler.controller.proxy.model;

import com.dent.crawler.common.model.BaseRequest;

public class UpdateProxyAccountRequest extends BaseRequest {

    private static final long serialVersionUID = 2632367603723571622L;

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
