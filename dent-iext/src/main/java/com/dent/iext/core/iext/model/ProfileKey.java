package com.dent.iext.core.iext.model;

public enum ProfileKey {
    // @formatter:off
    NONE("NONE"), 
    NAME("NAME"),
    PHOTO("PHOTO"),
    CONNECTION("CONNECTION"),
    TITLE("TITLE"),
    EMAIL("EMAIL"),
    PHONE("NAME"),
    ABOUT("ABOUT"),
    EXPERIENCE("EXPERIENCE"),
    EDUCATION("EDUCATION"),
    CERTIFICATE("CERTIFICATE"),
    SKILL("SKILL"),
    REFERENCE("REFERENCE"),
    ACCOMPLISHMENT("ACCOMPLISHMENT"),
    PROJECT("PROJECT"),
    INTEREST("INTEREST");
    // @formatter:on

    private String code;

    private ProfileKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ProfileKey fromCode(String code) {
        if (code != null) {
            for (ProfileKey item : ProfileKey.values()) {
                if (item.getCode().equals(code)) {
                    return item;
                }
            }
        }

        return NONE;
    }
}