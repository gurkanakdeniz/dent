package com.dent.bff.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.view.model.AccountListResponse;
import com.dent.bff.service.remote.ICrawlerService;

@RestController("ViewLinkedinController")
@RequestMapping("/view/linkedin")
public class LinkedinController {

    @Autowired
    private ICrawlerService crawlerService;

    @GetMapping
    public AccountListResponse list() {
        return new AccountListResponse(crawlerService.accountList().getAccounts());
    }
}
