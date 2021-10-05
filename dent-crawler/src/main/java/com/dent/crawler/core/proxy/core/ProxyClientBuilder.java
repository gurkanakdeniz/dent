package com.dent.crawler.core.proxy.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dent.crawler.core.proxy.model.ProxyAccountModel;
import com.dent.crawler.infrastructure.common.core.AppContext;
import com.dent.crawler.service.proxy.IProxyService;
import com.dent.crawler.service.proxy.model.RequestAccount;

@Component
public class ProxyClientBuilder {

    private final static Logger logger = LoggerFactory.getLogger(ProxyClientBuilder.class);

    private static IProxyService proxyService;

    public ProxyClientBuilder init() {
        if (ProxyClientBuilder.proxyService == null) {
            ProxyClientBuilder.proxyService = AppContext.getContext().getBean(IProxyService.class);
        }

        return this;
    }

    public ProxyClientBuilder init(IProxyService proxyService) {
        ProxyClientBuilder.proxyService = proxyService;

        return this;
    }

    public static ProxyClient build() {
        ProxyClient response = null;

        try {
            ProxyAccountModel proxyAccountModel = proxyService.getAccount(new RequestAccount()).getProxyAccountModel();
            if (proxyAccountModel != null) {
                response = new ProxyClient(proxyAccountModel.getId(), proxyAccountModel.getUsername(),
                        proxyAccountModel.getPassword(), proxyAccountModel.getType(),
                        Integer.valueOf(proxyAccountModel.getPort()).intValue(), proxyAccountModel.getHostname());
            }
        } catch (Exception e) {
            logger.error("--- proxy client error --- ", e);
        }

        return response;
    }

}
