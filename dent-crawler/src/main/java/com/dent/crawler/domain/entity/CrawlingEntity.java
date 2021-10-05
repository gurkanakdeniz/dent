package com.dent.crawler.domain.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dent.crawler.core.crawling.core.CrawlingProcessingModel;
import com.dent.crawler.domain.model.CrawlingModelDTO;

@Document(collection = "Crawlings")
public class CrawlingEntity extends BaseEntity {

    @Id
    private String id;

    private String jobId;
    private CrawlingProcessingModel crawlingData;
    private Date crawlingDate;

    private CrawlingModelDTO crawlingModel;

    public CrawlingEntity() {
        super();
    }

    public CrawlingEntity(String jobId, CrawlingProcessingModel crawlingData, Date crawlingDate, CrawlingModelDTO crawlingModel) {
        super();
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
