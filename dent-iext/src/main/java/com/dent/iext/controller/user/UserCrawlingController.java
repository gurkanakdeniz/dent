package com.dent.iext.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dent.iext.controller.common.BaseController;
import com.dent.iext.controller.user.model.CrawlingResponse;
import com.dent.iext.controller.user.model.InfoResponse;
import com.dent.iext.core.iext.model.ConsumerCrawlingModel;
import com.dent.iext.core.iext.model.CrawlingModelDTO;
import com.dent.iext.core.iext.model.Mode;
import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.service.producer.IProducer;
import com.dent.iext.service.producer.model.TopicName;
import com.dent.iext.service.user.IUserService;
import com.dent.iext.service.user.model.RequestInfo;
import com.dent.iext.service.user.model.RequestList;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/crawling")
@Api(tags = "User Crawlings")
public class UserCrawlingController extends BaseController {

    @Autowired
    IUserService userService;

    @Autowired
    IProducer producer;

    @GetMapping
    public InfoResponse info(@RequestParam(name = "id") String id, @RequestParam(name = "profileId") String profileId) {
        return new InfoResponse(userService.info(new RequestInfo(profileId, id)).getUser());
    }

    @RequestMapping(value = "/{username}/crawling", method = RequestMethod.GET)
    public CrawlingResponse crawling(@PathVariable("username") String username) {
        if (username != null) {
            UserModel userModel = userService.list(new RequestList(username)).getUsers().get(0);
            if (userModel != null) {
                ConsumerCrawlingModel crawlingModel = userModel.getCrawlingModel();
                if (crawlingModel == null) {
                    crawlingModel = new ConsumerCrawlingModel(null, null, null, null,
                            new CrawlingModelDTO(Mode.PROFILE.getCode(), username, null, null, null));
                }

                producer.sendMessage(TopicName.CRAWLING, crawlingModel);
            }
        }

        return new CrawlingResponse(username);
    }
}
