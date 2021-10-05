package com.dent.bff.controller.view.model;

import java.util.Date;

import com.dent.bff.common.model.BaseModel;
import com.dent.bff.service.remote.model.CrawlingProcessingModel;
import com.dent.bff.service.remote.model.FilterType;
import com.dent.bff.service.remote.model.Mode;
import com.dent.bff.service.remote.model.ProfileModel;

public class CrawlingModel extends BaseModel {

    private String id;
    private String jobId;

    private Date crawlingDate;

    private Mode mode;
    private String profileId;
    private String summary;
    private FilterType filterType;
    private String filter;

    private CrawlingProcessingModel crawlingData;

    private String username;
    private ProfileModel profileModel;

    private String processingStatus;
    private Date processingDate;

    public CrawlingModel() {
        super();
    }

    public CrawlingModel(String id, String jobId, Date crawlingDate, Mode mode, String profileId, String summary,
            FilterType filterType, String filter, CrawlingProcessingModel crawlingData, String username,
            ProfileModel profileModel, String processingStatus, Date processingDate) {
        super();
        this.id = id;
        this.jobId = jobId;
        this.crawlingDate = crawlingDate;
        this.mode = mode;
        this.profileId = profileId;
        this.summary = summary;
        this.filterType = filterType;
        this.filter = filter;
        this.crawlingData = crawlingData;
        this.username = username;
        this.profileModel = profileModel;
        this.processingStatus = processingStatus;
        this.processingDate = processingDate;
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

    public CrawlingProcessingModel getCrawlingData() {
        return crawlingData;
    }

    public void setCrawlingData(CrawlingProcessingModel crawlingData) {
        this.crawlingData = crawlingData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfileModel getProfileModel() {
        return profileModel;
    }

    public void setProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }

    public String getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(String processingStatus) {
        this.processingStatus = processingStatus;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
