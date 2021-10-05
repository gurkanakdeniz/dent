package com.dent.crawler.service.linkedin.model;

import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseUpdate extends BaseResponse {
    
    private static final long serialVersionUID = 2685381381587551758L;

    private String email;

    public ResponseUpdate(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
