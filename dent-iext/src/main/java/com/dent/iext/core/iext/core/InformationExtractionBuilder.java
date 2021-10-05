package com.dent.iext.core.iext.core;

import com.dent.iext.core.iext.model.ConsumerCrawlingModel;

public class InformationExtractionBuilder {

    public InformationExtraction build(ConsumerCrawlingModel model) {
        return new InformationExtraction(model);
    }
}
