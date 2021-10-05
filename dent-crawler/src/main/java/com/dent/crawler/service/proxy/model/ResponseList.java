package com.dent.crawler.service.proxy.model;

import java.util.List;

import com.dent.crawler.core.proxy.model.ProxyAccountModel;
import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseList extends BaseResponse {

    private static final long serialVersionUID = 382664878587526326L;
    
    private List<ProxyAccountModel> accounts;

    public ResponseList(List<ProxyAccountModel> accounts) {
        super();
        this.accounts = accounts;
    }

    public List<ProxyAccountModel> getAccounts() {
        return accounts;
    }

}
