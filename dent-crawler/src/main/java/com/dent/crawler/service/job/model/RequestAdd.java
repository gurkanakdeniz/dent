package com.dent.crawler.service.job.model;

import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.service.common.model.BaseRequest;

public class RequestAdd extends BaseRequest {

    private static final long serialVersionUID = -6836952900538334059L;
    
    private CrawlingModel crawlingModel;

    public RequestAdd() {
        super();
    }

    public RequestAdd(CrawlingModel crawlingModel) {
        super();
        this.crawlingModel = crawlingModel;
    }

    public CrawlingModel getCrawlingModel() {
        return crawlingModel;
    }

    public void setCrawlingModel(CrawlingModel crawlingModel) {
        this.crawlingModel = crawlingModel;
    }

}
