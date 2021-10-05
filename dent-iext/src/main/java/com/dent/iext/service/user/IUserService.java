package com.dent.iext.service.user;

import com.dent.iext.service.user.model.RequestAdd;
import com.dent.iext.service.user.model.RequestDelete;
import com.dent.iext.service.user.model.RequestInfo;
import com.dent.iext.service.user.model.RequestInfoDelete;
import com.dent.iext.service.user.model.RequestList;
import com.dent.iext.service.user.model.RequestUpdate;
import com.dent.iext.service.user.model.ResponseAdd;
import com.dent.iext.service.user.model.ResponseDelete;
import com.dent.iext.service.user.model.ResponseInfo;
import com.dent.iext.service.user.model.ResponseInfoDelete;
import com.dent.iext.service.user.model.ResponseList;
import com.dent.iext.service.user.model.ResponseUpdate;

public interface IUserService {

    ResponseAdd add(RequestAdd request);

    ResponseList list(RequestList request);

    ResponseDelete delete(RequestDelete request);

    ResponseUpdate update(RequestUpdate request);

    ResponseInfo info(RequestInfo request);

    ResponseInfoDelete infoDelete(RequestInfoDelete request);

}
