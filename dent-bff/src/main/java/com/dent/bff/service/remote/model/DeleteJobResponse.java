package com.dent.bff.service.remote.model;

public class DeleteJobResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private String id;

    public DeleteJobResponse() {
        super();
    }

    public DeleteJobResponse(String id) {
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
