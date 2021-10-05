package com.dent.crawler.service.producer.model;

import com.dent.crawler.core.crawling.core.CrawlingProcessingModel;
import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.service.common.model.BaseRequest;

public class RequestSend extends BaseRequest {

    private static final long serialVersionUID = 8001993962020555765L;

    private String crawlingId;

    private String jobId;
    private CrawlingProcessingModel crawlingData;
    private CrawlingModel crawlingModel;

    public RequestSend(String crawlingId) {
        super();
        this.crawlingId = crawlingId;
    }

    public RequestSend(String jobId, CrawlingProcessingModel crawlingData, CrawlingModel crawlingModel) {
        super();
        this.jobId = jobId;
        this.crawlingData = crawlingData;
        this.crawlingModel = crawlingModel;
    }

    public String getJobId() {
        return jobId;
    }

    public CrawlingProcessingModel getCrawlingData() {
        return crawlingData;
    }

    public CrawlingModel getCrawlingModel() {
        return crawlingModel;
    }

    public String getCrawlingId() {
        return crawlingId;
    }

}
