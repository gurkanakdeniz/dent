package com.dent.bff.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.management.model.CrawlingResponse;
import com.dent.bff.controller.management.model.ExtractionResponse;
import com.dent.bff.controller.management.model.ProxyAccountDeleteResponse;
import com.dent.bff.service.remote.ICrawlerService;

@RestController("ManagementProfileDataController")
@RequestMapping("/management/data")
public class ProfileDataController {

    @Autowired
    private ICrawlerService crawlerService;

    @RequestMapping(value = "/extraction/{id}", method = RequestMethod.GET)
    public ExtractionResponse extraction(@PathVariable("id") String id) {
        return new ExtractionResponse(crawlerService.extraction(id).getId());
    }

    @RequestMapping(value = "/crawling/{id}", method = RequestMethod.GET)
    public CrawlingResponse crawling(@PathVariable("id") String id) {
        return new CrawlingResponse(crawlerService.crawling(id).getId());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ProxyAccountDeleteResponse delete(@PathVariable("id") String id) {
        return new ProxyAccountDeleteResponse(crawlerService.deleteCrawling(id).getId());
    }

}
