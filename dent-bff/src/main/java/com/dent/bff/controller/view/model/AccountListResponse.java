package com.dent.bff.controller.view.model;

import java.util.List;

import com.dent.bff.common.model.BaseResponse;
import com.dent.bff.service.remote.model.AuthenticationModel;

public class AccountListResponse extends BaseResponse {

    private List<AuthenticationModel> accounts;

    public AccountListResponse() {
        super();
    }

    public AccountListResponse(List<AuthenticationModel> accounts) {
        this.accounts = accounts;
    }

    public List<AuthenticationModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AuthenticationModel> accounts) {
        this.accounts = accounts;
    }
}
