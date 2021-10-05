package com.dent.bff.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dent.bff.service.remote.model.CrawlingResponse;
import com.dent.bff.service.remote.model.DeleteUserResponse;
import com.dent.bff.service.remote.model.ExtractionResponse;
import com.dent.bff.service.remote.model.InfoResponse;
import com.dent.bff.service.remote.model.ListUserResponse;

@FeignClient(name = "iext-service", url = "${feign.url.iext}")
public interface IIExtService {

    @GetMapping("/crawling")
    public InfoResponse info(@RequestParam(name = "id") String id, @RequestParam(name = "profileId") String profileId);

    @GetMapping("/user")
    public ListUserResponse userList();

    @RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
    public DeleteUserResponse delete(@PathVariable("username") String username);

    @RequestMapping(value = "/user/{username}/extraction", method = RequestMethod.GET)
    public ExtractionResponse extraction(@PathVariable("username") String username);

    @RequestMapping(value = "/crawling/{username}/crawling", method = RequestMethod.GET)
    public CrawlingResponse crawling(@PathVariable("username") String username);

}
