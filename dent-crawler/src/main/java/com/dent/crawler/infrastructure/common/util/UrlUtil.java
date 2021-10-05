package com.dent.crawler.infrastructure.common.util;

import static com.dent.crawler.infrastructure.common.model.Constant.AUTHWALL;
import static com.dent.crawler.infrastructure.common.model.Constant.CONTACT;
import static com.dent.crawler.infrastructure.common.model.Constant.INTERESTS;
import static com.dent.crawler.infrastructure.common.model.Constant.PEOPLE;
import static com.dent.crawler.infrastructure.common.model.Constant.PROFILE;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public final class UrlUtil {

    public static final String auth() {
        return AUTHWALL;
    }

    public static final String profile(final String profileId) {
        return PROFILE + profileId;
    }

    public static final String contact(final String profileId) {
        return String.format(CONTACT, profileId);
    }
    
    public static final String interests(final String profileId) {
        return String.format(INTERESTS, profileId);
    }

    public static final String people(final String profileId) {
        return String.format(PEOPLE, profileId);
    }

    public static final String decode(final String profile) {
        String response = "";

        try {
            response = URLDecoder.decode(profile.replace("+", "%2B"), StandardCharsets.UTF_8.name()).replace("%2B",
                    "+");
        } catch (Exception e) {
            // ignore;
        }

        return response;
    }

}
