package com.dent.iext.infrastructure.common.model;

public enum SessionKey {

    DURATION("DURATION");

    private String code;

    private SessionKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
