package com.dent.bff.controller.view.model;

import java.util.List;

import com.dent.bff.common.model.BaseResponse;
import com.dent.bff.service.remote.model.ProxyAccountModel;

public class ProxyAccountListResponse  extends BaseResponse {

    private List<ProxyAccountModel> accounts;

    public ProxyAccountListResponse() {
        super();
    }

    public ProxyAccountListResponse(List<ProxyAccountModel> accounts) {
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
