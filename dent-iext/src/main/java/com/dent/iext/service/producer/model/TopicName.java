package com.dent.iext.service.producer.model;

public enum TopicName {
    NONE("NONE"), CRAWLING("CRAWLING");

    private String name;

    private TopicName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
