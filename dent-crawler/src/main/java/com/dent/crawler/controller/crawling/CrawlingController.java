package com.dent.crawler.controller.crawling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dent.crawler.controller.crawling.model.CrawlingResponse;
import com.dent.crawler.controller.crawling.model.DeleteCrawlingResponse;
import com.dent.crawler.controller.crawling.model.ExtractionResponse;
import com.dent.crawler.controller.crawling.model.ListCrawlingResponse;
import com.dent.crawler.core.crawling.model.CrawlingDataModel;
import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.crawling.model.Mode;
import com.dent.crawler.service.job.IJobService;
import com.dent.crawler.service.job.model.RequestAdd;
import com.dent.crawler.service.producer.impl.Producer;
import com.dent.crawler.service.producer.model.RequestDelete;
import com.dent.crawler.service.producer.model.RequestList;
import com.dent.crawler.service.producer.model.RequestSend;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/crawling")
@Api(tags = "Crawling Data")
public class CrawlingController {

    @Autowired
    IJobService jobService;

    @Autowired
    Producer producer;

    @GetMapping
    public ListCrawlingResponse list() {
        return new ListCrawlingResponse(producer.list(new RequestList()).getCrawlings());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteCrawlingResponse delete(@PathVariable("id") String id) {
        return new DeleteCrawlingResponse(producer.delete(new RequestDelete(id)).getId());
    }

    @RequestMapping(value = "/{id}/crawling", method = RequestMethod.GET)
    public CrawlingResponse crawling(@PathVariable("id") String id) {
        if (id != null) {
            CrawlingDataModel crawlingDataModel = producer.list(new RequestList(id)).getCrawlings().get(0);

            if (crawlingDataModel != null) {
                CrawlingModel crawlingModel = crawlingDataModel.getCrawlingModel();

                if (crawlingModel != null) {
                    jobService.add(new RequestAdd(new CrawlingModel(Mode.PROFILE, crawlingModel.getProfileId(),
                            crawlingModel.getSummary(), crawlingModel.getFilterType(), crawlingModel.getFilter())));
                }
            }
        }

        return new CrawlingResponse(id);
    }

    @RequestMapping(value = "/{id}/extraction", method = RequestMethod.GET)
    public ExtractionResponse extraction(@PathVariable("id") String id) {
        if (id != null) {
            producer.send(new RequestSend(id));
        }

        return new ExtractionResponse(id);
    }
}
