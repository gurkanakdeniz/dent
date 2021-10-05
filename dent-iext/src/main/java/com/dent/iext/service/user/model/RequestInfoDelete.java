package com.dent.iext.service.user.model;

import com.dent.iext.service.common.BaseRequest;

public class RequestInfoDelete extends BaseRequest {

    private String profileId;
    private String crawlingId;
    private boolean deleteInfo;

    public RequestInfoDelete(String profileId, String crawlingId) {
        super();
        this.profileId = profileId;
        this.crawlingId = crawlingId;
    }

    public RequestInfoDelete(String profileId, String crawlingId, boolean deleteInfo) {
        super();
        this.profileId = profileId;
        this.crawlingId = crawlingId;
        this.deleteInfo = deleteInfo;
    }

    public String getCrawlingId() {
        return crawlingId;
    }

    public void setCrawlingId(String crawlingId) {
        this.crawlingId = crawlingId;
    }

    public boolean isDeleteInfo() {
        return deleteInfo;
    }

    public void setDeleteInfo(boolean deleteInfo) {
        this.deleteInfo = deleteInfo;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

}
