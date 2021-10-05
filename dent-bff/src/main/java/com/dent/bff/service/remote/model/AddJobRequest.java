package com.dent.bff.service.remote.model;

public class AddJobRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    private Mode mode;
    private String profileId;
    private String summary;
    private FilterType filterType;
    private String filter;

    public AddJobRequest(Mode mode, String profileId, String summary, FilterType filterType, String filter) {
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

    public String getProfileId() {
        return profileId;
    }

    public String getSummary() {
        return summary;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public String getFilter() {
        return filter;
    }

}
