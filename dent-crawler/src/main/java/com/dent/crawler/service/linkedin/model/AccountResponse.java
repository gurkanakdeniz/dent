package com.dent.crawler.service.linkedin.model;

import com.dent.crawler.core.linkedin.model.AuthenticationModel;
import com.dent.crawler.service.common.model.BaseResponse;

public class AccountResponse extends BaseResponse {

    private static final long serialVersionUID = -5301796509357592369L;
    
    private AuthenticationModel authenticationModel;

    public AccountResponse(AuthenticationModel authenticationModel) {
        super();
        this.authenticationModel = authenticationModel;
    }

    public AuthenticationModel getAuthenticationModel() {
        return authenticationModel;
    }

}
