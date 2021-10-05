package com.dent.iext.service.user.model;

import com.dent.iext.service.common.BaseResponse;

public class ResponseInfoDelete extends BaseResponse {

    private String id;

    public ResponseInfoDelete() {
        super();
    }

    public ResponseInfoDelete(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
