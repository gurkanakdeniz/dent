package com.dent.bff.service.remote.model;

import java.util.List;

public class ListProxyAccountResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    private List<ProxyAccountModel> accounts;

    public ListProxyAccountResponse() {
        super();
    }

    public ListProxyAccountResponse(List<ProxyAccountModel> accounts) {
        super();
        this.accounts = accounts;
    }

    public List<ProxyAccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<ProxyAccountModel> accounts) {
        this.accounts = accounts;
    }

}
