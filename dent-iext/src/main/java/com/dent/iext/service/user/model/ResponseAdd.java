package com.dent.iext.service.user.model;

import com.dent.iext.service.common.BaseResponse;

public class ResponseAdd extends BaseResponse {

    private static final long serialVersionUID = -8296206969586671505L;
    
    private String username;

    public ResponseAdd(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
