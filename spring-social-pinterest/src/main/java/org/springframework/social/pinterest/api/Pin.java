package org.springframework.social.pinterest.api;

import java.io.Serializable;

/**
 * Created by Rajaboopathy Vijay on 10/27/15.
 */
public class Pin extends PinInterestObject implements Serializable {

    private String url;
    private String link;
    private String id;
    private String note;

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getNote() {
        return note;
    }

    public String getUrl() {
        return url;
    }
}
