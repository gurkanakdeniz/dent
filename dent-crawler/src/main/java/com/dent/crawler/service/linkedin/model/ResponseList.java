package com.dent.crawler.service.linkedin.model;

import java.util.List;

import com.dent.crawler.core.linkedin.model.AuthenticationModel;
import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseList extends BaseResponse {

    private static final long serialVersionUID = 5813794315117211153L;
    
    private List<AuthenticationModel> accounts;

    public ResponseList(List<AuthenticationModel> accounts) {
        super();
        this.accounts = accounts;
    }

    public List<AuthenticationModel> getAccounts() {
        return accounts;
    }

}
