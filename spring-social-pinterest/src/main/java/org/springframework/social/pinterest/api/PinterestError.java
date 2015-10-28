package org.springframework.social.pinterest.api;

import java.util.Date;

/**
 * Created by Rajaboopathy Vijay on 10/28/15.
 */
public class PinterestError {

    private final String status;
    private final Integer code;
    private final String host;
    private final Date generatedDate;
    private final String message;
    private final String data;

    public PinterestError(String status, Integer code, String host, Date generatedDate, String message, String data) {
        this.status = status;
        this.code = code;
        this.host = host;
        this.generatedDate = generatedDate;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getHost() {
        return host;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
