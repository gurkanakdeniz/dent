package com.dent.bff.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.management.model.CrawlingResponse;
import com.dent.bff.controller.management.model.ExtractionResponse;
import com.dent.bff.controller.management.model.UserDeleteResponse;
import com.dent.bff.service.remote.IIExtService;

@RestController("ManagementProfileController")
@RequestMapping("/management/profile")
public class ProfileController {

    @Autowired
    private IIExtService iextService;

    @RequestMapping(value = "/extraction/{username}", method = RequestMethod.GET)
    public ExtractionResponse extraction(@PathVariable("username") String username) {
        return new ExtractionResponse(iextService.extraction(username).getId());
    }

    @RequestMapping(value = "/crawling/{username}", method = RequestMethod.GET)
    public CrawlingResponse crawling(@PathVariable("username") String username) {
        return new CrawlingResponse(iextService.crawling(username).getId());
    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
    public UserDeleteResponse delete(@PathVariable("username") String username) {
        return new UserDeleteResponse(iextService.delete(username).getUsername());
    }

}
