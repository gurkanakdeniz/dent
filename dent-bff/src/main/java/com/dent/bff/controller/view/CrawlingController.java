package com.dent.bff.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.view.model.JobModel;
import com.dent.bff.controller.view.model.ListResponse;
import com.dent.bff.service.remote.ICrawlerService;
import com.dent.bff.service.remote.model.CrawlingJobModel;
import com.dent.bff.service.remote.model.CrawlingModel;
import com.dent.bff.service.remote.model.FilterType;
import com.dent.bff.service.remote.model.Mode;

@RestController("ViewCrawlingController")
@RequestMapping("/view/crawling")
public class CrawlingController {

    @Autowired
    private ICrawlerService crawlerService;

    @GetMapping
    public ListResponse list() {
        List<JobModel> jobs = new ArrayList<JobModel>();

        List<CrawlingJobModel> crawlingJobs = crawlerService.jobList().getCrawlingJobs();
        if (crawlingJobs != null && !crawlingJobs.isEmpty()) {
            for (CrawlingJobModel item : crawlingJobs) {
                CrawlingModel crawlingModel = item.getCrawlingModel();
                Mode mode = null;
                String profileId = null;
                FilterType filterType = null;
                String filter = null;
                String summary = null;

                if (crawlingModel != null) {
                    mode = crawlingModel.getMode();
                    profileId = crawlingModel.getProfileId();
                    filterType = crawlingModel.getFilterType();
                    filter = crawlingModel.getFilter();
                    summary = crawlingModel.getSummary();
                }

                jobs.add(new JobModel(item.getId(), item.getStatus(), item.getCreateDate(), item.getStartDate(),
                        item.getEndDate(), mode, profileId, filterType, summary, filter));
            }
        }

        return new ListResponse(jobs);
    }
}
