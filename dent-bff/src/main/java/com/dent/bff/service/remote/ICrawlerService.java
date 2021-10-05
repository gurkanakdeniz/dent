package com.dent.bff.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dent.bff.service.remote.model.AddAccountRequest;
import com.dent.bff.service.remote.model.AddAccountResponse;
import com.dent.bff.service.remote.model.AddJobRequest;
import com.dent.bff.service.remote.model.AddJobResponse;
import com.dent.bff.service.remote.model.AddProxyAccountRequest;
import com.dent.bff.service.remote.model.AddProxyAccountResponse;
import com.dent.bff.service.remote.model.CrawlingExtractionResponse;
import com.dent.bff.service.remote.model.CrawlingResponse;
import com.dent.bff.service.remote.model.DeleteAccountResponse;
import com.dent.bff.service.remote.model.DeleteCrawlingResponse;
import com.dent.bff.service.remote.model.DeleteJobResponse;
import com.dent.bff.service.remote.model.DeleteProxyAccountResponse;
import com.dent.bff.service.remote.model.ListAccountResponse;
import com.dent.bff.service.remote.model.ListCrawlingResponse;
import com.dent.bff.service.remote.model.ListJobResponse;
import com.dent.bff.service.remote.model.ListProxyAccountResponse;
import com.dent.bff.service.remote.model.UpdateAccountRequest;
import com.dent.bff.service.remote.model.UpdateAccountResponse;
import com.dent.bff.service.remote.model.UpdateJobRequest;
import com.dent.bff.service.remote.model.UpdateJobResponse;
import com.dent.bff.service.remote.model.UpdateProxyAccountRequest;
import com.dent.bff.service.remote.model.UpdateProxyAccountResponse;

@FeignClient(name = "crawler-service", url = "${feign.url.crawler}")
public interface ICrawlerService {

    /**
     * JOB
     * 
     */

    @GetMapping("/job")
    public ListJobResponse jobList();

    @PostMapping("/job")
    public AddJobResponse addJob(@RequestBody AddJobRequest request);

    @RequestMapping(value = "/job/{id}", method = RequestMethod.DELETE)
    public DeleteJobResponse deleteJob(@PathVariable("id") String id);

    @RequestMapping(value = "/job/{id}", method = RequestMethod.POST)
    public UpdateJobResponse updateJob(@PathVariable("id") String id, @RequestBody UpdateJobRequest request);

    /**
     * ACCOUNT
     * 
     */

    @GetMapping("/account")
    public ListAccountResponse accountList();

    @PostMapping("/account")
    public AddAccountResponse addAccount(@RequestBody AddAccountRequest request);

    @RequestMapping(value = "/account/{email}", method = RequestMethod.DELETE)
    public DeleteAccountResponse deleteAccount(@PathVariable("email") String email);

    @RequestMapping(value = "/account/{email}", method = RequestMethod.POST)
    public UpdateAccountResponse updateAccount(@PathVariable("email") String email,
            @RequestBody UpdateAccountRequest request);

    /**
     * PROXY
     * 
     */

    @GetMapping("/proxy")
    public ListProxyAccountResponse proxyList();

    @PostMapping("/proxy")
    public AddProxyAccountResponse addProxy(@RequestBody AddProxyAccountRequest request);

    @RequestMapping(value = "/proxy/{id}", method = RequestMethod.DELETE)
    public DeleteProxyAccountResponse deleteProxyAccount(@PathVariable("id") String id);

    @RequestMapping(value = "/proxy/{id}", method = RequestMethod.POST)
    public UpdateProxyAccountResponse updateProxy(@PathVariable("id") String id,
            @RequestBody UpdateProxyAccountRequest request);

    /**
     * CRAWLING
     * 
     */

    @GetMapping("/crawling")
    public ListCrawlingResponse crawlingList();

    @RequestMapping(value = "/crawling/{id}", method = RequestMethod.DELETE)
    public DeleteCrawlingResponse deleteCrawling(@PathVariable("id") String id);

    @RequestMapping(value = "/crawling/{id}/extraction", method = RequestMethod.GET)
    public CrawlingExtractionResponse extraction(@PathVariable("id") String id);

    @RequestMapping(value = "/crawling/{id}/crawling", method = RequestMethod.GET)
    public CrawlingResponse crawling(@PathVariable("id") String id);

}
