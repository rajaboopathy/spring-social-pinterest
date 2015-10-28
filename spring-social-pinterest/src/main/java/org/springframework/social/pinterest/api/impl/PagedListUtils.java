
package org.springframework.social.pinterest.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.social.pinterest.api.PageParameters;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PagedListUtils {

    public static PageParameters getPagedListParameters(JsonNode pagingNode, String pageKey) {
        if (pagingNode == null) {
            return null;
        }
        String cursor = pagingNode.get("cursor").textValue();
        String next = pagingNode.get("next").textValue();

        return new PageParameters(cursor, next);
    }

    public static MultiValueMap<String, String> getPagingParameters(PageParameters pagedListParameters) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        if (pagedListParameters.getCursor() != null) {
            parameters.add("cursor", String.valueOf(pagedListParameters.getCursor()));
        }
        if (pagedListParameters.getNext() != null) {
            parameters.add("next", String.valueOf(pagedListParameters.getNext()));
        }
        return parameters;
    }

    private static String extractEncodedParameterValueFromUrl(String url, String paramName) {
        try {
            String value = extractParameterValueFromUrl(url, paramName);
            return value != null ? URLDecoder.decode(value, "UTF-8") : null;
        } catch (UnsupportedEncodingException e) {
            // shouldn't happen
            return null;
        }
    }

    private static String extractParameterValueFromUrl(String url, String paramName) {
        int queryStart = url.indexOf('?') >= 0 ? url.indexOf('?') : 0;
        int startPos = url.indexOf(paramName + "=", queryStart);
        if (startPos == -1) {
            return null;
        }
        int ampPos = url.indexOf("&", startPos);
        if (ampPos >= 0) {
            return url.substring(startPos + paramName.length() + 1, ampPos);
        }
        return url.substring(startPos + paramName.length() + 1);
    }

}
