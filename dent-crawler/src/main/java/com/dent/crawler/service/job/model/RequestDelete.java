package com.dent.crawler.service.job.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestDelete extends BaseRequest {

    private static final long serialVersionUID = -4859384909557883498L;
    
    private String id;

    public RequestDelete(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
