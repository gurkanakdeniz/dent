package com.dent.bff.controller.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import com.dent.bff.controller.view.model.CrawlingListResponse;
import com.dent.bff.controller.view.model.CrawlingModel;
import com.dent.bff.service.remote.ICrawlerService;
import com.dent.bff.service.remote.IIExtService;
import com.dent.bff.service.remote.model.CrawlingDataModel;
import com.dent.bff.service.remote.model.FilterType;
import com.dent.bff.service.remote.model.InfoResponse;
import com.dent.bff.service.remote.model.ListCrawlingResponse;
import com.dent.bff.service.remote.model.Mode;
import com.dent.bff.service.remote.model.ProfileModel;
import com.dent.bff.service.remote.model.UserModel;

@RestController("ViewProfileDataController")
@RequestMapping("/view/data")
public class ProfileDataController {

    private final Logger logger = LoggerFactory.getLogger(ProfileDataController.class);

    @Autowired
    private ICrawlerService crawlerService;

    @Autowired
    private IIExtService iextService;

    @GetMapping
    public CrawlingListResponse list() {
        List<CrawlingModel> crawlings = new ArrayList<CrawlingModel>();

        ListCrawlingResponse crawlingList = crawlerService.crawlingList();

        if (crawlingList != null) {
            List<CrawlingDataModel> crawlingDataList = crawlingList.getCrawlings();
            if (crawlingDataList != null && !crawlingDataList.isEmpty()) {
                ExecutorService service = Executors.newFixedThreadPool(15);
                
                for (CrawlingDataModel item : crawlingDataList) {
                    
                    service.execute(new Runnable() {
                        
                        @Override
                        public void run() {
                            Mode mode = null;
                            String profileId = null;
                            String summary = null;
                            FilterType filterType = null;
                            String filter = null;
                            com.dent.bff.service.remote.model.CrawlingModel crawlingModel = item.getCrawlingModel();
                            if (crawlingModel != null) {
                                mode = crawlingModel.getMode();
                                profileId = crawlingModel.getProfileId();
                                summary = crawlingModel.getSummary();
                                filterType = crawlingModel.getFilterType();
                                filter = crawlingModel.getFilter();
                            }

                            if (profileId != null) {
                                UserModel user = null;
                                try {
                                    InfoResponse info = iextService.info(item.getId(), profileId);
                                    if (info != null) {
                                        user = info.getUser();
                                    }
                                } catch (Exception e) {
                                    logger.error("--- list info ---", e);
                                }

                                String username = null;
                                ProfileModel profileModel = null;
                                String processingStatus = null;
                                Date processingDate = null;
                                if (user != null) {
                                    profileModel = user.getProfileModel();
                                    username = user.getUsername();
                                    if (profileModel != null) {
                                        processingStatus = Boolean.TRUE.toString();
                                        processingDate = profileModel.getInfoDate();
                                    }
                                }

                                crawlings.add(new CrawlingModel(item.getId(), item.getJobId(), item.getCrawlingDate(), mode,
                                        profileId, summary, filterType, filter, item.getCrawlingData(), username, profileModel,
                                        processingStatus, processingDate));
                            }
                        }
                    });
                }
                
                service.shutdown();
                try {
                    service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                   logger.error("--- await termination ---");
                }
                
                Collections.sort(crawlings, new Comparator<CrawlingModel>() {
                    @Override
                    public int compare(CrawlingModel item1, CrawlingModel item2) {
                        if (item1.getCrawlingDate() == null || item2.getCrawlingDate() == null) {
                            return 0;
                        }
                        
                        return item2.getCrawlingDate().compareTo(item1.getCrawlingDate());
                    }
                });
            }

        }

        return new CrawlingListResponse(crawlings);
    }

}
