package com.dent.bff.controller.api.model;

import java.util.Date;
import java.util.EnumMap;
import java.util.List;

import com.dent.bff.common.model.BaseModel;
import com.dent.bff.service.remote.model.CrawlingModelDTO;
import com.dent.bff.service.remote.model.CrawlingProcessingModel;
import com.dent.bff.service.remote.model.ProfileKey;

public class UserData extends BaseModel {

    private String username;

    private String infoId;
    private EnumMap<ProfileKey, List<Object>> info;
    private Date infoDate;

    private String crawlingId;
    private String jobId;
    private CrawlingProcessingModel crawlingData;
    private Date crawlingDate;
    private CrawlingModelDTO crawlingModel;

    public UserData() {
        super();
    }

    public UserData(String username, String infoId, EnumMap<ProfileKey, List<Object>> info, Date infoDate,
            String crawlingId, String jobId, CrawlingProcessingModel crawlingData, Date crawlingDate,
            CrawlingModelDTO crawlingModel) {
        super();
        this.username = username;
        this.infoId = infoId;
        this.info = info;
        this.infoDate = infoDate;
        this.crawlingId = crawlingId;
        this.jobId = jobId;
        this.crawlingData = crawlingData;
        this.crawlingDate = crawlingDate;
        this.crawlingModel = crawlingModel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
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

    public String getCrawlingId() {
        return crawlingId;
    }

    public void setCrawlingId(String crawlingId) {
        this.crawlingId = crawlingId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public CrawlingProcessingModel getCrawlingData() {
        return crawlingData;
    }

    public void setCrawlingData(CrawlingProcessingModel crawlingData) {
        this.crawlingData = crawlingData;
    }

    public Date getCrawlingDate() {
        return crawlingDate;
    }

    public void setCrawlingDate(Date crawlingDate) {
        this.crawlingDate = crawlingDate;
    }

    public CrawlingModelDTO getCrawlingModel() {
        return crawlingModel;
    }

    public void setCrawlingModel(CrawlingModelDTO crawlingModel) {
        this.crawlingModel = crawlingModel;
    }

}
