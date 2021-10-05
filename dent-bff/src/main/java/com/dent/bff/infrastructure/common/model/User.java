package com.dent.bff.infrastructure.common.model;

import com.dent.bff.common.model.BaseModel;

public class User extends BaseModel {

    private String username;
    private AuthRole authRole;

    public User(String username, AuthRole authRole) {
        super();
        this.username = username;
        this.authRole = authRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AuthRole getAuthRole() {
        return authRole;
    }

    public void setAuthRole(AuthRole authRole) {
        this.authRole = authRole;
    }

}
