package com.dent.crawler.service.proxy.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestUpdate extends BaseRequest {

    private static final long serialVersionUID = -7441353655442641265L;
    
    private String id;
    private Boolean status;

    public RequestUpdate(String id, Boolean status) {
        super();
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Boolean getStatus() {
        return status;
    }

}
