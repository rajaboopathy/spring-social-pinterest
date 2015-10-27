package org.springframework.social.pinterest.api;

import java.io.Serializable;

/**
 * Created by Rajaboopathy Vijay on 10/27/15.
 */
public class Pin extends PinInterestObject implements Serializable {

    String url;
    String link;
    String id;
    String note;

    public Pin() {
    }

    public Pin(String url, String link, String id, String note) {
        this.url = url;
        this.link = link;
        this.id = id;
        this.note = note;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
