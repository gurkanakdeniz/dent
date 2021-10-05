package com.dent.crawler.controller.linkedin.model;

import com.dent.crawler.common.model.BaseResponse;

public class UpdateAccountResponse extends BaseResponse {
    private static final long serialVersionUID = 1530667684863476875L;

    private String email;

    public UpdateAccountResponse(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
