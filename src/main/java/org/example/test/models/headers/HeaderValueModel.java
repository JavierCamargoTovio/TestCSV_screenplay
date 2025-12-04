package org.example.test.models.headers;

public enum HeaderValueModel {
    ACCEPT("Accept", "application/json"),
    //AUTHORIZATION("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM="),
    CONTENT_TYPE("Content-Type", "application/json"),
    API_KEY("x-api-key", "reqres_a3d71857f5cd4ce99f4dbe16bb720b48"),
    COOKIE("Cookie", "");

    private String header;
    private String value;

    HeaderValueModel(String header, String value) {
        this.header = header;
        this.value = value;
    }

    public String getHeader() {
        return header;
    }

    public String getValue() {
        return value;
    }
}
