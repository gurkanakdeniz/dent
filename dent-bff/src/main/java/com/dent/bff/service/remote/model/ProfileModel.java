package com.dent.bff.service.remote.model;

import java.util.Date;
import java.util.EnumMap;
import java.util.List;

public class ProfileModel extends BaseModel {

    private String id;
    private EnumMap<ProfileKey, List<Object>> info;
    private Date infoDate;

    public ProfileModel() {
        super();
    }

    public ProfileModel(EnumMap<ProfileKey, List<Object>> info) {
        super();
        this.info = info;
    }

    public ProfileModel(String id, EnumMap<ProfileKey, List<Object>> info, Date infoDate) {
        super();
        this.id = id;
        this.info = info;
        this.infoDate = infoDate;
    }

    public EnumMap<ProfileKey, List<Object>> getInfo() {
        return info;
    }

    public void setInfo(EnumMap<ProfileKey, List<Object>> info) {
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Date infoDate) {
        this.infoDate = infoDate;
    }

}
