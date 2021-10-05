package com.dent.crawler.infrastructure.common.model;

public class ExceptionModel extends BaseModel {

    private String code;
    private String desc;
    private String text;

    public ExceptionModel() {
        super();
    }

    public ExceptionModel(String code, String desc, String text) {
        super();
        this.code = code;
        this.desc = desc;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
