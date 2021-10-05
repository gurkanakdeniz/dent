package com.dent.bff.service.remote.model;

public class UpdateJobResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;

    private String id;

    public UpdateJobResponse() {
        super();
    }

    public UpdateJobResponse(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
