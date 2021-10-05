package com.dent.bff.service.remote.model;

import java.util.List;

public class ListAccountResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
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
