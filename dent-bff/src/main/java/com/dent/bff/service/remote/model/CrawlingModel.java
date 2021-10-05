package com.dent.bff.service.remote.model;

public class CrawlingModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Mode mode;
    private String profileId;
    private String summary;
    private FilterType filterType;
    private String filter;

    public CrawlingModel() {
        super();
    }

    public CrawlingModel(Mode mode, String profileId, String summary, FilterType filterType, String filter) {
        super();
        this.mode = mode;
        this.profileId = profileId;
        this.summary = summary;
        this.filterType = filterType;
        this.filter = filter;
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
