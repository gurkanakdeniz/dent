package com.dent.crawler.infrastructure.common.util;

import java.util.Hashtable;
import java.util.Map;

import com.dent.crawler.infrastructure.common.model.SessionKey;

public final class Session implements ISession {

    private static ThreadLocal<Session> local = new ThreadLocal<Session>();

    protected Map<String, Object> context = new Hashtable<String, Object>();

    public Session() {
        super();
        local.set(this);
    }

    public static Session instance() {
        Session session = local.get();
        if (session == null) {
            session = new Session();
        }

        return session;
    }

    @Override
    public void clear() {
        context.clear();
    }

    @Override
    public final void put(SessionKey key, Object value) {
        if (value != null) {
            context.put(key.getCode(), value);
        }
    }

    @Override
    public final Object get(SessionKey key) {
        return context.get(key.getCode());
    }

}
