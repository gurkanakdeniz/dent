package com.dent.iext.domain.entity;

import org.springframework.data.annotation.Id;

public class UserInfoEntity extends BaseEntity {

    @Id
    private String id;

    private InfoEntity infoEntity;
    private CrawlingEntity crawlingEntity;

    public UserInfoEntity(String id, InfoEntity infoEntity, CrawlingEntity crawlingEntity) {
        super();
        this.id = id;
        this.infoEntity = infoEntity;
        this.crawlingEntity = crawlingEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InfoEntity getInfoEntity() {
        return infoEntity;
    }

    public void setInfoEntity(InfoEntity infoEntity) {
        this.infoEntity = infoEntity;
    }

    public CrawlingEntity getCrawlingEntity() {
        return crawlingEntity;
    }

    public void setCrawlingEntity(CrawlingEntity crawlingEntity) {
        this.crawlingEntity = crawlingEntity;
    }

}
