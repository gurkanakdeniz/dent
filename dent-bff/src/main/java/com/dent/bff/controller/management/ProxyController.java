package com.dent.bff.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.management.model.ProxyAccountAddRequest;
import com.dent.bff.controller.management.model.ProxyAccountAddResponse;
import com.dent.bff.controller.management.model.ProxyAccountDeleteResponse;
import com.dent.bff.controller.management.model.ProxyAccountUpdateRequest;
import com.dent.bff.controller.management.model.ProxyAccountUpdateResponse;
import com.dent.bff.service.remote.ICrawlerService;
import com.dent.bff.service.remote.model.AddProxyAccountRequest;
import com.dent.bff.service.remote.model.UpdateProxyAccountRequest;

@RestController("ManagementProxyController")
@RequestMapping("/management/proxy")
public class ProxyController {

    @Autowired
    private ICrawlerService crawlerService;

    @PostMapping
    public ProxyAccountAddResponse add(@RequestBody ProxyAccountAddRequest request) {
        return new ProxyAccountAddResponse(crawlerService.addProxy(new AddProxyAccountRequest(request.getPort(),
                request.getType(), request.getUsername(), request.getPassword(), request.getHostname())).getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ProxyAccountUpdateResponse update(@PathVariable("id") String id,
            @RequestBody ProxyAccountUpdateRequest request) {
        Boolean status = request.getStatus();
        status = status == null ? Boolean.FALSE : Boolean.valueOf(!status.booleanValue());

        return new ProxyAccountUpdateResponse(
                crawlerService.updateProxy(id, new UpdateProxyAccountRequest(status)).getId());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ProxyAccountDeleteResponse delete(@PathVariable("id") String id) {
        return new ProxyAccountDeleteResponse(crawlerService.deleteProxyAccount(id).getId());
    }

}
