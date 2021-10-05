package com.dent.iext.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dent.iext.controller.common.BaseController;
import com.dent.iext.controller.user.model.AddUserRequest;
import com.dent.iext.controller.user.model.AddUserResponse;
import com.dent.iext.controller.user.model.DeleteUserResponse;
import com.dent.iext.controller.user.model.ExtractionResponse;
import com.dent.iext.controller.user.model.ListUserResponse;
import com.dent.iext.controller.user.model.UpdateUserRequest;
import com.dent.iext.controller.user.model.UpdateUserResponse;
import com.dent.iext.core.iext.core.InformationExtraction;
import com.dent.iext.core.iext.core.InformationExtractionProcessingModel;
import com.dent.iext.core.iext.model.ConsumerCrawlingModel;
import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.infrastructure.common.core.AppContext;
import com.dent.iext.service.user.IUserService;
import com.dent.iext.service.user.model.RequestAdd;
import com.dent.iext.service.user.model.RequestDelete;
import com.dent.iext.service.user.model.RequestInfoDelete;
import com.dent.iext.service.user.model.RequestList;
import com.dent.iext.service.user.model.RequestUpdate;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(tags = "User Management")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @GetMapping
    public ListUserResponse list() {
        return new ListUserResponse(userService.list(new RequestList()).getUsers());
    }

    @PostMapping
    public AddUserResponse add(@RequestBody AddUserRequest request) {
        return new AddUserResponse(userService.add(new RequestAdd(request.getUserModel())).getUsername());
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public DeleteUserResponse delete(@PathVariable("username") String username) {
        return new DeleteUserResponse(userService.delete(new RequestDelete(username)).getUsername());
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public UpdateUserResponse update(@PathVariable("username") String username,
            @RequestBody UpdateUserRequest request) {
        return new UpdateUserResponse(userService.update(new RequestUpdate(request.getUserModel())).getUsername());
    }

    @RequestMapping(value = "/{username}/extraction", method = RequestMethod.GET)
    public ExtractionResponse extraction(@PathVariable("username") String username) {
        if (username != null) {
            UserModel userModel = userService.list(new RequestList(username)).getUsers().get(0);

            if (userModel != null) {
                ConsumerCrawlingModel crawlingModel = userModel.getCrawlingModel();
                if (crawlingModel != null) {
                    try {
                        Thread extractionThread = new Thread() {
                            public void run() {
                                IUserService service = AppContext.getContext().getBean(IUserService.class);

                                service.infoDelete(new RequestInfoDelete(
                                        crawlingModel.getCrawlingModel().getProfileId(), crawlingModel.getId(), true));

                                InformationExtractionProcessingModel processing = InformationExtraction.getBuilder()
                                        .build(crawlingModel).processing();

                                UserModel userModel = processing.getUserModel();
                                service.add(new RequestAdd(userModel));
                            }
                        };

                        extractionThread.setDaemon(true);
                        extractionThread.start();
                    } catch (Throwable e) {
                        logger.error("--- extraction ---", e);
                    }
                }
            }
        }

        return new ExtractionResponse(username);
    }

}
