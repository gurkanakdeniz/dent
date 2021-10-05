package com.dent.bff.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.management.model.JobAddRequest;
import com.dent.bff.controller.management.model.JobAddResponse;
import com.dent.bff.controller.management.model.JobDeleteResponse;
import com.dent.bff.controller.management.model.JobUpdateRequest;
import com.dent.bff.controller.management.model.JobUpdateResponse;
import com.dent.bff.service.remote.ICrawlerService;
import com.dent.bff.service.remote.model.AddJobRequest;
import com.dent.bff.service.remote.model.JobStatus;
import com.dent.bff.service.remote.model.UpdateJobRequest;

@RestController("ManagementCrawlingController")
@RequestMapping("/management/crawling")
public class CrawlingController {

    @Autowired
    private ICrawlerService crawlerService;

    @PostMapping
    public JobAddResponse add(@RequestBody JobAddRequest request) {
        return new JobAddResponse(crawlerService.addJob(new AddJobRequest(request.getMode(), request.getProfileId(),
                request.getSummary(), request.getFilterType(), request.getFilter())).getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public JobUpdateResponse update(@PathVariable("id") String id, @RequestBody JobUpdateRequest request) {
        JobStatus status = request.getStatus();

        if (JobStatus.RUNNING.equals(status)) {
            status = JobStatus.FINISHED;
        } else {
            status = JobStatus.RUNNING;
        }

        return new JobUpdateResponse(crawlerService.updateJob(id, new UpdateJobRequest(status)).getId());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public JobDeleteResponse delete(@PathVariable("id") String id) {
        return new JobDeleteResponse(crawlerService.deleteJob(id).getId());
    }
}
