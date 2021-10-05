package com.dent.crawler.common.exception;

public class CrawlingFilterException extends Exception {

    private String profileId;
    private String filterType;
    private String filter;

    public CrawlingFilterException(String profileId, String filterType, String filter) {
        super();
        this.profileId = profileId;
        this.filterType = filterType;
        this.filter = filter;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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
