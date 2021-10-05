package com.dent.crawler.controller.linkedin.model;

import java.util.List;

import com.dent.crawler.common.model.BaseResponse;
import com.dent.crawler.core.linkedin.model.AuthenticationModel;

public class ListAccountResponse extends BaseResponse {

    private static final long serialVersionUID = 3188673071836067527L;
    
    private List<AuthenticationModel> accounts;

    public ListAccountResponse() {
        super();
    }

    public ListAccountResponse(List<AuthenticationModel> accounts) {
        this.accounts = accounts;
    }

    public List<AuthenticationModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AuthenticationModel> accounts) {
        this.accounts = accounts;
    }

}
