package com.dent.crawler.controller.linkedin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.crawler.controller.common.BaseController;
import com.dent.crawler.controller.linkedin.model.AddAccountRequest;
import com.dent.crawler.controller.linkedin.model.AddAccountResponse;
import com.dent.crawler.controller.linkedin.model.DeleteAccountResponse;
import com.dent.crawler.controller.linkedin.model.ListAccountResponse;
import com.dent.crawler.controller.linkedin.model.UpdateAccountRequest;
import com.dent.crawler.controller.linkedin.model.UpdateAccountResponse;
import com.dent.crawler.service.linkedin.ILinkedinService;
import com.dent.crawler.service.linkedin.model.RequestAdd;
import com.dent.crawler.service.linkedin.model.RequestDelete;
import com.dent.crawler.service.linkedin.model.RequestList;
import com.dent.crawler.service.linkedin.model.RequestUpdate;
import com.dent.crawler.service.linkedin.model.ResponseList;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/account")
@Api(tags = "Linkedin Account Management")
public class LinkedinController extends BaseController {

    @Autowired
    ILinkedinService linkedinService;

    @GetMapping
    public ListAccountResponse list() {
        ResponseList accounts = linkedinService.list(new RequestList());
        return new ListAccountResponse(accounts.getAccounts());
    }

    @PostMapping
    public AddAccountResponse add(@RequestBody AddAccountRequest request) {
        linkedinService.add(new RequestAdd(request.getEmail(), request.getPassword()));
        return new AddAccountResponse(request.getEmail());
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public DeleteAccountResponse delete(@PathVariable("email") String email) {
        linkedinService.delete(new RequestDelete(email));
        return new DeleteAccountResponse(email);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.POST)
    public UpdateAccountResponse update(@PathVariable("email") String email,
            @RequestBody UpdateAccountRequest request) {
        linkedinService.update(new RequestUpdate(email, request.getStatus()));
        return new UpdateAccountResponse(email);
    }
}
