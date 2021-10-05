package com.dent.crawler.controller.job.model;

import com.dent.crawler.common.model.BaseRequest;
import com.dent.crawler.core.crawling.model.FilterType;
import com.dent.crawler.core.crawling.model.Mode;

public class AddJobRequest extends BaseRequest {

    private static final long serialVersionUID = -2992575095213584473L;
    
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
