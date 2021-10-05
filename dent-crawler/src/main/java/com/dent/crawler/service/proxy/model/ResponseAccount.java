package com.dent.crawler.service.proxy.model;

import com.dent.crawler.core.proxy.model.ProxyAccountModel;
import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseAccount extends BaseResponse {

    private static final long serialVersionUID = -211375730637590098L;

    private ProxyAccountModel proxyAccountModel;

    public ResponseAccount(ProxyAccountModel proxyAccountModel) {
        super();
        this.proxyAccountModel = proxyAccountModel;
    }

    public ProxyAccountModel getProxyAccountModel() {
        return proxyAccountModel;
    }

}
