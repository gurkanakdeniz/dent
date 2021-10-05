package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseRequest;

public class AccountUpdateRequest extends BaseRequest {

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
