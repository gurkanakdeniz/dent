package com.dent.crawler.domain.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dent.crawler.domain.model.CrawlingModelDTO;

@Document(collection = "CrawlingJobs")
public class CrawlingJobEntity extends BaseEntity {

    @Id
    private String id;

    private String status;

    private Date createDate;

    private Date startDate;

    private Date endDate;

    private CrawlingModelDTO crawlingModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CrawlingModelDTO getCrawlingModel() {
        return crawlingModel;
    }

    public void setCrawlingModel(CrawlingModelDTO crawlingModel) {
        this.crawlingModel = crawlingModel;
    }

}
