package com.dent.bff.service.remote.model;

public class AddProxyAccountResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    private String id;

    public AddProxyAccountResponse() {
        super();
    }

    public AddProxyAccountResponse(String id) {
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
