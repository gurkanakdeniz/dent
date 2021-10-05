package com.dent.crawler.controller.linkedin.model;

import com.dent.crawler.common.model.BaseResponse;

public class DeleteAccountResponse extends BaseResponse {

    private static final long serialVersionUID = -4888798558872538878L;
    
    private String email;

    public DeleteAccountResponse() {
        super();
    }

    public DeleteAccountResponse(String email) {
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
