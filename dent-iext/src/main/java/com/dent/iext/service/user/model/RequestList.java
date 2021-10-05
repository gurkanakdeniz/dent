package com.dent.iext.service.user.model;

import com.dent.iext.service.common.BaseRequest;

public class RequestList extends BaseRequest {

    private static final long serialVersionUID = 1394065978961357199L;

    private String id;

    public RequestList() {
        super();
    }

    public RequestList(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
