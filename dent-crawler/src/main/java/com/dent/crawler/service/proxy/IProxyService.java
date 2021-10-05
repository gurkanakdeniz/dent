package com.dent.crawler.service.proxy;

import com.dent.crawler.service.proxy.model.RequestAccount;
import com.dent.crawler.service.proxy.model.RequestAdd;
import com.dent.crawler.service.proxy.model.RequestDelete;
import com.dent.crawler.service.proxy.model.RequestList;
import com.dent.crawler.service.proxy.model.RequestUpdate;
import com.dent.crawler.service.proxy.model.ResponseAccount;
import com.dent.crawler.service.proxy.model.ResponseAdd;
import com.dent.crawler.service.proxy.model.ResponseDelete;
import com.dent.crawler.service.proxy.model.ResponseList;
import com.dent.crawler.service.proxy.model.ResponseUpdate;

public interface IProxyService {
    
    ResponseAccount getAccount(RequestAccount request);

    ResponseAdd add(RequestAdd request);

    ResponseList list(RequestList request);

    ResponseDelete delete(RequestDelete request);

    ResponseUpdate update(RequestUpdate request);

}
