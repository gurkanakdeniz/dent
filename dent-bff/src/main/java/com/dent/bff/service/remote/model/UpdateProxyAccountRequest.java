package com.dent.bff.service.remote.model;

public class UpdateProxyAccountRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    private Boolean status;

    public UpdateProxyAccountRequest() {
        super();
    }

    public UpdateProxyAccountRequest(Boolean status) {
        super();
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
