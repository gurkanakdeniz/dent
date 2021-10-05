package com.dent.crawler.core.crawling.model;

public enum FilterType {

    COUNTRY("COUNTRY"), TITLE("TITLE"), WORKPLACE("WORKPLACE"), SCHOOL("SCHOOL"), EDUCATION_LEVEL("EDUCATION_LEVEL"),
    EDUCATION_SECTION("EDUCATION_SECTION");

    private String code;

    private FilterType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static FilterType fromCode(String code) {
        if (code != null) {
            for (FilterType item : FilterType.values()) {
                if (item.getCode().equals(code)) {
                    return item;
                }
            }
        }

        return null;
    }

}
