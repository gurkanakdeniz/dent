package com.dent.iext.core.iext.core;

import com.dent.iext.core.iext.model.ConsumerCrawlingModel;

public class InformationExtraction {

    private ConsumerCrawlingModel crawlingModel;

    public InformationExtraction(ConsumerCrawlingModel crawlingModel) {
        super();
        this.crawlingModel = crawlingModel;
    }

    public ConsumerCrawlingModel getCrawlingModel() {
        return crawlingModel;
    }

    public void setCrawlingModel(ConsumerCrawlingModel crawlingModel) {
        this.crawlingModel = crawlingModel;
    }

    public static InformationExtractionBuilder getBuilder() {
        return new InformationExtractionBuilder();
    }

    public InformationExtractionProcessingModel processing() {
        return new InformationExtractionProcessing().init().processing(this);
    }

}
