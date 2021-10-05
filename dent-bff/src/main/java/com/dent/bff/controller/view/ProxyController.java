package com.dent.bff.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.view.model.ProxyAccountListResponse;
import com.dent.bff.service.remote.ICrawlerService;

@RestController("ViewProxyController")
@RequestMapping("/view/proxy")
public class ProxyController {

    @Autowired
    private ICrawlerService crawlerService;

    @GetMapping
    public ProxyAccountListResponse list() {
        return new ProxyAccountListResponse(crawlerService.proxyList().getAccounts());
    }
}
