package com.tbkj.rent.utils;

import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {

    public static final String AUTHENTICATION = "Authentication";

    public static final String BEARER = "Bearer";

    public static String parseBearerToken(String authHeader) {
        if (!authHeader.startsWith(String.format("%s ", BEARER))) {
            return null;
        }
        final String[] arr = authHeader.split(String.format("%s ", BEARER));
        if (arr.length < 2) {
            return null;
        }
        return arr[1];
    }

    public static String parseBearerToken(HttpServletRequest request) {
        final String authHeader = parseAuthHeader(request);
        if (Objects.isNull(authHeader)) {
            return null;
        }
        return parseBearerToken(authHeader);
    }

    public static String parseAuthHeader(HttpServletRequest request) {
        return request.getHeader(AUTHENTICATION);
    }
}
