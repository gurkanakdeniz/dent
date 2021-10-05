package com.dent.crawler.core.crawling.model;

import java.util.Date;

import com.dent.crawler.core.crawling.core.CrawlingProcessingModel;
import com.dent.crawler.infrastructure.common.model.BaseModel;

public class CrawlingDataModel extends BaseModel {

    private String id;
    private Date crawlingDate;

    private String jobId;
    private CrawlingProcessingModel crawlingData;
    private CrawlingModel crawlingModel;

    public CrawlingDataModel() {
        super();
    }

    public CrawlingDataModel(String id, Date crawlingDate, String jobId, CrawlingProcessingModel crawlingData,
            CrawlingModel crawlingModel) {
        super();
        this.id = id;
        this.crawlingDate = crawlingDate;
        this.jobId = jobId;
        this.crawlingData = crawlingData;
        this.crawlingModel = crawlingModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCrawlingDate() {
        return crawlingDate;
    }

    public void setCrawlingDate(Date crawlingDate) {
        this.crawlingDate = crawlingDate;
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

    public CrawlingModel getCrawlingModel() {
        return crawlingModel;
    }

    public void setCrawlingModel(CrawlingModel crawlingModel) {
        this.crawlingModel = crawlingModel;
    }

}
