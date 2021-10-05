package com.dent.bff.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.api.model.ProfileListResponse;
import com.dent.bff.controller.api.model.UserData;
import com.dent.bff.service.remote.IIExtService;
import com.dent.bff.service.remote.model.ConsumerCrawlingModel;
import com.dent.bff.service.remote.model.CrawlingModelDTO;
import com.dent.bff.service.remote.model.CrawlingProcessingModel;
import com.dent.bff.service.remote.model.ListUserResponse;
import com.dent.bff.service.remote.model.ProfileKey;
import com.dent.bff.service.remote.model.ProfileModel;
import com.dent.bff.service.remote.model.UserModel;

@RestController("ApiProfileController")
@RequestMapping("/api/profile")
public class ProfileController {
    
    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    
    @Autowired
    private IIExtService iextService;

    @GetMapping
    public ProfileListResponse list() {
        List<UserData> users = new ArrayList<UserData>();

        ListUserResponse userList = iextService.userList();

        if (userList != null) {
            List<UserModel> userModels = userList.getUsers();

            if (userModels != null && !userModels.isEmpty()) {
                ExecutorService service = Executors.newFixedThreadPool(15);
                
                for (UserModel item : userModels) {
                    
                    service.execute(new Runnable() {
                        
                        @Override
                        public void run() {
                            String infoId = null;
                            EnumMap<ProfileKey, List<Object>> info = null;
                            Date infoDate = null;

                            ProfileModel profileModel = item.getProfileModel();
                            if (profileModel != null) {
                                infoId = profileModel.getId();
                                info = profileModel.getInfo();
                                infoDate = profileModel.getInfoDate();
                            }

                            String crawlingId = null;
                            String jobId = null;
                            Date crawlingDate = null;
                            CrawlingProcessingModel crawlingData = null;
                            CrawlingModelDTO crawlingModelDTO = null;
                            ConsumerCrawlingModel crawlingModel = item.getCrawlingModel();
                            if (crawlingModel != null) {
                                crawlingId = crawlingModel.getId();
                                jobId = crawlingModel.getJobId();
                                crawlingDate = crawlingModel.getCrawlingDate();
                                crawlingData = crawlingModel.getCrawlingData();
                                crawlingModelDTO = crawlingModel.getCrawlingModel();
                            }

                            users.add(new UserData(item.getUsername(), infoId, info, infoDate, crawlingId, jobId, crawlingData,
                                    crawlingDate, crawlingModelDTO));
                        }
                    });
                }
                
                service.shutdown();
                try {
                    service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                   logger.error("--- await termination ---");
                }
                
                
                Collections.sort(users, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData item1, UserData item2) {
                        if (item1.getInfoDate() == null || item2.getInfoDate() == null) {
                            return 0;
                        }
                        
                        return item2.getInfoDate().compareTo(item1.getInfoDate());
                    }
                });
            }
        }

        return new ProfileListResponse(users);
    }
}
