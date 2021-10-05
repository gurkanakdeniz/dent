package com.dent.bff.service.remote.model;

import java.util.Date;

public class CrawlingJobModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String id;

    private JobStatus status;

    private Date createDate;

    private Date startDate;

    private Date endDate;

    private CrawlingModel crawlingModel;

    public CrawlingJobModel() {
        super();
    }

    public CrawlingJobModel(String id, JobStatus status, Date createDate, Date startDate, Date endDate,
            CrawlingModel crawlingModel) {
        super();
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.crawlingModel = crawlingModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
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

    public CrawlingModel getCrawlingModel() {
        return crawlingModel;
    }

    public void setCrawlingModel(CrawlingModel crawlingModel) {
        this.crawlingModel = crawlingModel;
    }

}
