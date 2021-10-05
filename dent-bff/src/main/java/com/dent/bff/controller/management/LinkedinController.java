package com.dent.bff.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.management.model.AccountAddRequest;
import com.dent.bff.controller.management.model.AccountAddResponse;
import com.dent.bff.controller.management.model.AccountDeleteResponse;
import com.dent.bff.controller.management.model.AccountUpdateRequest;
import com.dent.bff.controller.management.model.AccountUpdateResponse;
import com.dent.bff.service.remote.ICrawlerService;
import com.dent.bff.service.remote.model.AddAccountRequest;
import com.dent.bff.service.remote.model.UpdateAccountRequest;

@RestController("ManagementLinkedinController")
@RequestMapping("/management/linkedin")
public class LinkedinController {

    @Autowired
    private ICrawlerService crawlerService;

    @PostMapping
    public AccountAddResponse add(@RequestBody AccountAddRequest request) {
        return new AccountAddResponse(
                crawlerService.addAccount(new AddAccountRequest(request.getEmail(), request.getPassword())).getEmail());
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.POST)
    public AccountUpdateResponse update(@PathVariable("email") String email, @RequestBody AccountUpdateRequest request) {
        Boolean status = request.getStatus();
        status = status == null ? Boolean.FALSE : Boolean.valueOf(!status.booleanValue());

        return new AccountUpdateResponse(crawlerService.updateAccount(email, new UpdateAccountRequest(status)).getEmail());
    }

    @RequestMapping(value = "/delete/{email}", method = RequestMethod.GET)
    public AccountDeleteResponse delete(@PathVariable("email") String email) {
        return new AccountDeleteResponse(crawlerService.deleteAccount(email).getEmail());
    }

}
