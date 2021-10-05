package com.dent.crawler.core.job.core;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dent.crawler.core.crawling.core.Crawling;
import com.dent.crawler.core.crawling.core.CrawlingProcessingModel;
import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.crawling.model.Mode;
import com.dent.crawler.core.job.model.JobStatus;
import com.dent.crawler.infrastructure.common.core.AppContext;
import com.dent.crawler.service.job.IJobService;
import com.dent.crawler.service.job.model.RequestUpdate;
import com.dent.crawler.service.producer.IProducer;
import com.dent.crawler.service.producer.model.RequestSend;

public class CrawlingJobRunner implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CrawlingJobFactory.class);

    private String jobId;
    private CrawlingModel model;

    public CrawlingJobRunner(String jobId, CrawlingModel model) {
        super();
        this.jobId = jobId;
        this.model = model;
    }

    @Override
    public void run() {
        logger.info("--- job running : {}-{}-{} ---", jobId, model.getProfileId(), model.getMode());

        Mode mode = model.getMode();
        String profileId = model.getProfileId();

        try {
            IJobService jobService = AppContext.getContext().getBean(IJobService.class);
            jobService.update(new RequestUpdate(jobId, JobStatus.RUNNING));

            HashMap<String, CrawlingProcessingModel> processing = Crawling.getBuilder().build(this.model).processing();
            if (processing != null && !processing.isEmpty()) {
                IProducer producerService = AppContext.getContext().getBean(IProducer.class);

                for (String key : processing.keySet()) {
                    CrawlingProcessingModel item = processing.get(key);
                    model.setMode(Mode.PROFILE);
                    model.setProfileId(key);
                    producerService.send(new RequestSend(jobId, item, model));
                }
            }

            jobService.update(new RequestUpdate(jobId, JobStatus.FINISHED));
        } catch (Exception e) {
            logger.error("--- job run ---", e);
            logger.error("--- job error : {}-{}-{} ---", jobId, model.getProfileId(), model.getMode());
        }
        model.setMode(mode);
        model.setProfileId(profileId);

        logger.info("--- job finished : {}-{}-{} ---", jobId, model.getProfileId(), model.getMode());
    }

}
