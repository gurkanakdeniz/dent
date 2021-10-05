package com.dent.crawler.service.linkedin;

import com.dent.crawler.service.linkedin.model.AccountRequest;
import com.dent.crawler.service.linkedin.model.AccountResponse;
import com.dent.crawler.service.linkedin.model.RequestAdd;
import com.dent.crawler.service.linkedin.model.RequestDelete;
import com.dent.crawler.service.linkedin.model.RequestList;
import com.dent.crawler.service.linkedin.model.RequestUpdate;
import com.dent.crawler.service.linkedin.model.ResponseAdd;
import com.dent.crawler.service.linkedin.model.ResponseDelete;
import com.dent.crawler.service.linkedin.model.ResponseList;
import com.dent.crawler.service.linkedin.model.ResponseUpdate;

public interface ILinkedinService {

    AccountResponse getAccount(AccountRequest request);

    ResponseAdd add(RequestAdd request);

    ResponseList list(RequestList request);

    ResponseDelete delete(RequestDelete request);

    ResponseUpdate update(RequestUpdate request);

}
