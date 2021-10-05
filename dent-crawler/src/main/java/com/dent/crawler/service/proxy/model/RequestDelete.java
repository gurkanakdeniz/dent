package com.dent.crawler.service.proxy.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestDelete extends BaseRequest {

    private static final long serialVersionUID = 7196570670333518080L;
    
    private String id;

    public RequestDelete(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
