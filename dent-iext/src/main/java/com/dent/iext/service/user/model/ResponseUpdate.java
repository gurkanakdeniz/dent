package com.dent.iext.service.user.model;

import com.dent.iext.service.common.BaseResponse;

public class ResponseUpdate extends BaseResponse {

    private static final long serialVersionUID = 3051635187082356324L;
    
    private String username;

    public ResponseUpdate(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
