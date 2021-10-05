package com.dent.iext.service.user.model;

import com.dent.iext.service.common.BaseResponse;

public class ResponseDelete extends BaseResponse {

    private static final long serialVersionUID = 7111318638845101537L;
    
    private String username;

    public ResponseDelete(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
