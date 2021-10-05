package com.dent.iext.domain.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class UserEntity extends BaseEntity {

    @Id
    private String username;

    private UserInfoEntity userInfoEntity;

    private List<UserInfoEntity> info;
    
    private Date updateDate;

    public UserEntity() {
        super();
    }

    public UserEntity(String username) {
        super();
        this.username = username;
    }

    public UserEntity(String username, UserInfoEntity userInfoEntity, List<UserInfoEntity> info) {
        super();
        this.username = username;
        this.userInfoEntity = userInfoEntity;
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public List<UserInfoEntity> getInfo() {
        return info;
    }

    public void setInfo(List<UserInfoEntity> info) {
        this.info = info;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
