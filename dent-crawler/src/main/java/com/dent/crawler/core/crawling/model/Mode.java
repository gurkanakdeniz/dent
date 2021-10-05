package com.dent.crawler.core.crawling.model;

public enum Mode {

    PROFILE("PROFILE"), CORPORATE("CORPORATE"), RELATED_PROFILE("RELATED_PROFILE");

    private String code;

    private Mode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Mode fromCode(String code) {
        if (code != null) {
            for (Mode item : Mode.values()) {
                if (item.getCode().equals(code)) {
                    return item;
                }
            }
        }

        return null;
    }

}
