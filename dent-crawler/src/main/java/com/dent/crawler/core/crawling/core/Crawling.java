package com.dent.crawler.core.crawling.core;

import java.util.HashMap;

import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.linkedin.core.WebUnit;

public class Crawling {

    private WebUnit webUnit;
    private CrawlingModel model;

    public Crawling(WebUnit webUnit, CrawlingModel model) {
        super();
        this.webUnit = webUnit;
        this.model = model;
    }

    public static CrawlingBuilder getBuilder() {
        return new CrawlingBuilder();
    }

    public WebUnit getWebUnit() {
        return webUnit;
    }

    public CrawlingModel getModel() {
        return model;
    }

    public HashMap<String, CrawlingProcessingModel> processing() {
        return new CrawlingProcessing().init().processing(this);
    }

}
