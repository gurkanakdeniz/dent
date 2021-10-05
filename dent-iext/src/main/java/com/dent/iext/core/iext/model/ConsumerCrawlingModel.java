package com.dent.iext.core.iext.model;

import java.util.Date;

import com.dent.iext.infrastructure.common.model.BaseModel;

public class ConsumerCrawlingModel extends BaseModel {

    private String id;

    private String jobId;
    private CrawlingProcessingModel crawlingData;
    private Date crawlingDate;

    private CrawlingModelDTO crawlingModel;

    public ConsumerCrawlingModel() {
        super();
    }

    public ConsumerCrawlingModel(String jobId, CrawlingProcessingModel crawlingData, Date crawlingDate,
            CrawlingModelDTO crawlingModel) {
        super();
        this.jobId = jobId;
        this.crawlingData = crawlingData;
        this.crawlingDate = crawlingDate;
        this.crawlingModel = crawlingModel;
    }

    public ConsumerCrawlingModel(String id, String jobId, CrawlingProcessingModel crawlingData, Date crawlingDate,
            CrawlingModelDTO crawlingModel) {
        super();
        this.id = id;
        this.jobId = jobId;
        this.crawlingData = crawlingData;
        this.crawlingDate = crawlingDate;
        this.crawlingModel = crawlingModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public CrawlingProcessingModel getCrawlingData() {
        return crawlingData;
    }

    public void setCrawlingData(CrawlingProcessingModel crawlingData) {
        this.crawlingData = crawlingData;
    }

    public Date getCrawlingDate() {
        return crawlingDate;
    }

    public void setCrawlingDate(Date crawlingDate) {
        this.crawlingDate = crawlingDate;
    }

    public CrawlingModelDTO getCrawlingModel() {
        return crawlingModel;
    }

    public void setCrawlingModel(CrawlingModelDTO crawlingModel) {
        this.crawlingModel = crawlingModel;
    }
}
