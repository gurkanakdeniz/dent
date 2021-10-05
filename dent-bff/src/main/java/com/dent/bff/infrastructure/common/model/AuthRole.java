package com.dent.bff.infrastructure.common.model;

public enum AuthRole {

    ADMIN("ADMIN"), USER("USER"), API("API");

    private String code;

    private AuthRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AuthRole fromCode(String code) {
        if (code != null) {
            for (AuthRole role : AuthRole.values()) {
                if (role.getCode().equals(code)) {
                    return role;
                }
            }
        }

        return null;
    }

}
