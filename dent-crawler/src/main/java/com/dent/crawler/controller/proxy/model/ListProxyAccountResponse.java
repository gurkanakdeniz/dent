package com.dent.crawler.controller.proxy.model;

import java.util.List;

import com.dent.crawler.common.model.BaseResponse;
import com.dent.crawler.core.proxy.model.ProxyAccountModel;

public class ListProxyAccountResponse extends BaseResponse {

    private static final long serialVersionUID = -7325349741833637701L;
    
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
