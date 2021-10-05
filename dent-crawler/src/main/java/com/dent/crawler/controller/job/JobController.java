package com.dent.crawler.controller.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.crawler.controller.job.model.AddJobRequest;
import com.dent.crawler.controller.job.model.AddJobResponse;
import com.dent.crawler.controller.job.model.DeleteJobResponse;
import com.dent.crawler.controller.job.model.ListJobResponse;
import com.dent.crawler.controller.job.model.UpdateJobRequest;
import com.dent.crawler.controller.job.model.UpdateJobResponse;
import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.job.core.CrawlingJobFactory;
import com.dent.crawler.core.job.model.JobStatus;
import com.dent.crawler.service.job.IJobService;
import com.dent.crawler.service.job.model.RequestAdd;
import com.dent.crawler.service.job.model.RequestDelete;
import com.dent.crawler.service.job.model.RequestList;
import com.dent.crawler.service.job.model.RequestUpdate;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/job")
@Api(tags = "Job Management")
public class JobController {

    @Autowired
    IJobService jobService;

    @Autowired
    CrawlingJobFactory jobFactory;

    @GetMapping
    public ListJobResponse list() {
        return new ListJobResponse(jobService.list(new RequestList()).getCrawlingJobs());
    }

    @PostMapping
    public AddJobResponse add(@RequestBody AddJobRequest request) {
        return new AddJobResponse(jobService.add(new RequestAdd(new CrawlingModel(request.getMode(),
                request.getProfileId(), request.getSummary(), request.getFilterType(), request.getFilter()))).getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public UpdateJobResponse update(@PathVariable("id") String id, @RequestBody UpdateJobRequest request) {
        if (JobStatus.FINISHED.equals(request.getStatus())) {
            jobFactory.stop(id);
        }

        return new UpdateJobResponse(jobService.update(new RequestUpdate(id, request.getStatus())).getJobId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteJobResponse delete(@PathVariable("id") String id) {
        jobFactory.stop(id);

        return new DeleteJobResponse(jobService.delete(new RequestDelete(id)).getId());
    }

}
