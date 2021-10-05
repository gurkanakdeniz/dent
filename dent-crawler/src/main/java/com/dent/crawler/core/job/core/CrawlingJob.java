package com.dent.crawler.core.job.core;

import com.dent.crawler.core.crawling.model.CrawlingModel;

public class CrawlingJob extends Thread {

    private String jobId;
    private CrawlingModel model;

    public CrawlingJob(ThreadGroup group, Runnable target, String jobId, CrawlingModel model) {
        super(group, target);
        this.jobId = jobId;
        this.model = model;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public CrawlingModel getModel() {
        return model;
    }

    public void setModel(CrawlingModel model) {
        this.model = model;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

}
