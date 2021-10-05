package com.dent.crawler.core.job.model;

public enum JobStatus {

    WAITING("WAITING"), RUNNING("RUNNING"), FINISHED("FINISHED");

    private String code;

    private JobStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static JobStatus fromCode(String code) {
        if (code != null) {
            for (JobStatus item : JobStatus.values()) {
                if (item.getCode().equals(code)) {
                    return item;
                }
            }
        }

        return WAITING;
    }

}
