package com.dent.crawler.service.job;

import com.dent.crawler.service.job.model.RequestAdd;
import com.dent.crawler.service.job.model.RequestDelete;
import com.dent.crawler.service.job.model.RequestList;
import com.dent.crawler.service.job.model.RequestUpdate;
import com.dent.crawler.service.job.model.ResponseAdd;
import com.dent.crawler.service.job.model.ResponseDelete;
import com.dent.crawler.service.job.model.ResponseList;
import com.dent.crawler.service.job.model.ResponseUpdate;

public interface IJobService {

    ResponseAdd add(RequestAdd request);

    ResponseList list(RequestList request);

    ResponseDelete delete(RequestDelete request);

    ResponseUpdate update(RequestUpdate request);
}
