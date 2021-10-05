package com.dent.crawler.service.producer.model;

public enum TopicName {

    NONE("NONE"), IEXT("IEXT"), IEXT_DELETE("IEXTDELETE");

    private String name;

    private TopicName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
