package com.dent.iext.service.user.model;

import com.dent.iext.service.common.BaseRequest;

public class RequestInfo extends BaseRequest {

    private String profileId;
    private String crawlingId;

    public RequestInfo(String profileId, String crawlingId) {
        super();
        this.profileId = profileId;
        this.crawlingId = crawlingId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getCrawlingId() {
        return crawlingId;
    }

    public void setCrawlingId(String crawlingId) {
        this.crawlingId = crawlingId;
    }

}
