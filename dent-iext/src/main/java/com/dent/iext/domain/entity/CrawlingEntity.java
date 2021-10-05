package com.dent.iext.domain.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class CrawlingEntity extends BaseEntity {

    @Id
    private String id;

    private String jobId;
    private Date crawlingDate;
    private Object crawlingModel;
    private Object crawlingData;

    public CrawlingEntity() {
        super();
    }

    public CrawlingEntity(String id, String jobId, Date crawlingDate, Object crawlingModel, Object crawlingData) {
        super();
        this.id = id;
        this.jobId = jobId;
        this.crawlingDate = crawlingDate;
        this.crawlingModel = crawlingModel;
        this.crawlingData = crawlingData;
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

    public Date getCrawlingDate() {
        return crawlingDate;
    }

    public void setCrawlingDate(Date crawlingDate) {
        this.crawlingDate = crawlingDate;
    }

    public Object getCrawlingModel() {
        return crawlingModel;
    }

    public void setCrawlingModel(Object crawlingModel) {
        this.crawlingModel = crawlingModel;
    }

    public Object getCrawlingData() {
        return crawlingData;
    }

    public void setCrawlingData(Object crawlingData) {
        this.crawlingData = crawlingData;
    }

}
