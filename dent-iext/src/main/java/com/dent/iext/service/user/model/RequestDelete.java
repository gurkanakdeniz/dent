package com.dent.iext.service.user.model;

import com.dent.iext.service.common.BaseRequest;

public class RequestDelete extends BaseRequest {

    private static final long serialVersionUID = -7795078029583127576L;
    
    private String username;

    public RequestDelete(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
