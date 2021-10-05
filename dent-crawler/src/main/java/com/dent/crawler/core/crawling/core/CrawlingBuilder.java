package com.dent.crawler.core.crawling.core;

import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.linkedin.core.WebUnit;

public class CrawlingBuilder {

    public Crawling build(CrawlingModel model) {
        return new Crawling(WebUnit.getBuilder().build(), model);
    }
}
