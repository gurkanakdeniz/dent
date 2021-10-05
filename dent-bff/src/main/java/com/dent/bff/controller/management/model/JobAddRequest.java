package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseRequest;
import com.dent.bff.service.remote.model.FilterType;
import com.dent.bff.service.remote.model.Mode;

public class JobAddRequest extends BaseRequest {

    private Mode mode;
    private String profileId;
    private String summary;
    private FilterType filterType;
    private String filter;

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
