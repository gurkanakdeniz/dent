package com.dent.crawler.service.job.model;

import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseDelete extends BaseResponse {

    private static final long serialVersionUID = 2848290267144592570L;
    
    private String id;

    public ResponseDelete(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
