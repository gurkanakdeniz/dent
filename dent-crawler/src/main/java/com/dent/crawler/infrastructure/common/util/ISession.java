package com.dent.crawler.infrastructure.common.util;

import com.dent.crawler.infrastructure.common.model.SessionKey;

public interface ISession {

    void put(SessionKey key, Object value);

    Object get(SessionKey key);

    void clear();

}
