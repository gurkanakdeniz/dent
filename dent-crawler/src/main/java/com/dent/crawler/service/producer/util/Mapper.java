package com.dent.crawler.service.producer.util;

import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.crawling.model.FilterType;
import com.dent.crawler.core.crawling.model.Mode;
import com.dent.crawler.domain.model.CrawlingModelDTO;

public class Mapper {

    public static CrawlingModelDTO map(CrawlingModel crawlingModel) {
        if (crawlingModel == null) {
            return null;
        }

        String filterType = crawlingModel.getFilterType() != null ? crawlingModel.getFilterType().getCode() : null;

        return new CrawlingModelDTO(crawlingModel.getMode().getCode(), crawlingModel.getProfileId(),
                crawlingModel.getSummary(), filterType, crawlingModel.getFilter());
    }

    public static CrawlingModel map(CrawlingModelDTO crawlingModel) {
        if (crawlingModel == null) {
            return null;
        }

        return new CrawlingModel(Mode.fromCode(crawlingModel.getMode()), crawlingModel.getProfileId(),
                crawlingModel.getSummary(), FilterType.fromCode(crawlingModel.getFilterType()),
                crawlingModel.getFilter());
    }

}
