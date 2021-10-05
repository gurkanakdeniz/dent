package com.dent.bff.service.remote.model;

public class UpdateAccountRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    private Boolean status;

    public UpdateAccountRequest() {
        super();
    }

    public UpdateAccountRequest(Boolean status) {
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
