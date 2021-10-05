package com.dent.crawler.controller.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.crawler.controller.common.BaseController;
import com.dent.crawler.controller.proxy.model.AddProxyAccountRequest;
import com.dent.crawler.controller.proxy.model.AddProxyAccountResponse;
import com.dent.crawler.controller.proxy.model.DeleteProxyAccountResponse;
import com.dent.crawler.controller.proxy.model.ListProxyAccountResponse;
import com.dent.crawler.controller.proxy.model.UpdateProxyAccountRequest;
import com.dent.crawler.controller.proxy.model.UpdateProxyAccountResponse;
import com.dent.crawler.service.proxy.IProxyService;
import com.dent.crawler.service.proxy.model.RequestAdd;
import com.dent.crawler.service.proxy.model.RequestDelete;
import com.dent.crawler.service.proxy.model.RequestList;
import com.dent.crawler.service.proxy.model.RequestUpdate;
import com.dent.crawler.service.proxy.model.ResponseAdd;
import com.dent.crawler.service.proxy.model.ResponseList;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/proxy")
@Api(tags = "Proxy Account Management")
public class ProxyController extends BaseController {

    @Autowired
    IProxyService proxyService;

    @GetMapping
    public ListProxyAccountResponse list() {
        ResponseList accounts = proxyService.list(new RequestList());
        return new ListProxyAccountResponse(accounts.getAccounts());
    }

    @PostMapping
    public AddProxyAccountResponse add(@RequestBody AddProxyAccountRequest request) {
        ResponseAdd add = proxyService.add(new RequestAdd(request.getUsername(), request.getPassword(),
                request.getPort(), request.getType(), request.getHostname()));
        return new AddProxyAccountResponse(add.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteProxyAccountResponse delete(@PathVariable("id") String id) {
        proxyService.delete(new RequestDelete(id));
        return new DeleteProxyAccountResponse(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public UpdateProxyAccountResponse update(@PathVariable("id") String id,
            @RequestBody UpdateProxyAccountRequest request) {
        proxyService.update(new RequestUpdate(id, request.getStatus()));
        return new UpdateProxyAccountResponse(id);
    }
}
