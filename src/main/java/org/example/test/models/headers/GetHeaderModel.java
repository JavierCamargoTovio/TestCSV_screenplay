package org.example.test.models.headers;

import java.util.HashMap;
import java.util.Map;

public class GetHeaderModel {
    private static final Map<String, String> headers = new HashMap<>();

    private GetHeaderModel() {
    }

    public static Map<String, String> headersDefault() {
        headers.put(HeaderValueModel.CONTENT_TYPE.getHeader(), HeaderValueModel.CONTENT_TYPE.getValue());
        headers.put(HeaderValueModel.ACCEPT.getHeader(), HeaderValueModel.ACCEPT.getValue());
        return headers;
    }

    public static Map<String, String> headersApiKey() {
        headers.put(HeaderValueModel.API_KEY.getHeader(), HeaderValueModel.API_KEY.getValue());
        headers.put(HeaderValueModel.ACCEPT.getHeader(), HeaderValueModel.ACCEPT.getValue());
        return headers;
    }

}
