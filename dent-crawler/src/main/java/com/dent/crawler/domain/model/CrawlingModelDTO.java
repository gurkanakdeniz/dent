package com.dent.crawler.domain.model;

import com.dent.crawler.domain.common.BaseModel;

public class CrawlingModelDTO extends BaseModel {

    private static final long serialVersionUID = 6739210264486096198L;

    private String mode;
    private String profileId;
    private String summary;
    private String filterType;
    private String filter;

    public CrawlingModelDTO() {
        super();
    }

    public CrawlingModelDTO(String mode, String profileId, String summary, String filterType, String filter) {
        super();
        this.mode = mode;
        this.profileId = profileId;
        this.summary = summary;
        this.filterType = filterType;
        this.filter = filter;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
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

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
