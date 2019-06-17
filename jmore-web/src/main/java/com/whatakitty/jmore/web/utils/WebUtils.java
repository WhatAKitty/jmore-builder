package com.whatakitty.jmore.web.utils;

import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;

/**
 * web utils
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
public final class WebUtils {

    public static String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            // As of https://en.wikipedia.org/wiki/X-Forwarded-For
            // The general format of the field is: X-Forwarded-For: client, proxy1, proxy2 ...
            // we only want the client
            return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
        }
    }

    private WebUtils() {}

}
