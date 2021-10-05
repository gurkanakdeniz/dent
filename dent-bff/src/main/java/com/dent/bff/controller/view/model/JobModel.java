package com.dent.bff.controller.view.model;

import java.util.Date;

import com.dent.bff.common.model.BaseModel;
import com.dent.bff.service.remote.model.FilterType;
import com.dent.bff.service.remote.model.JobStatus;
import com.dent.bff.service.remote.model.Mode;

public class JobModel extends BaseModel {

    private String id;
    private JobStatus status;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private Mode mode;
    private String profileId;
    private FilterType filterType;
    private String summary;
    private String filter;

    public JobModel() {
        super();
    }

    public JobModel(String id, JobStatus status, Date createDate, Date startDate, Date endDate, Mode mode,
            String profileId, FilterType filterType, String summary, String filter) {
        super();
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mode = mode;
        this.profileId = profileId;
        this.filterType = filterType;
        this.summary = summary;
        this.filter = filter;
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

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
