package com.dent.iext.domain.entity;

import java.util.Date;
import java.util.EnumMap;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.dent.iext.core.iext.model.ProfileKey;

public class InfoEntity extends BaseEntity {

    @Id
    private String id;

    private EnumMap<ProfileKey, List<Object>> info;

    private Date infoDate;

    public InfoEntity(String id, EnumMap<ProfileKey, List<Object>> info, Date infoDate) {
        super();
        this.id = id;
        this.info = info;
        this.infoDate = infoDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EnumMap<ProfileKey, List<Object>> getInfo() {
        return info;
    }

    public void setInfo(EnumMap<ProfileKey, List<Object>> info) {
        this.info = info;
    }

    public Date getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Date infoDate) {
        this.infoDate = infoDate;
    }

}
