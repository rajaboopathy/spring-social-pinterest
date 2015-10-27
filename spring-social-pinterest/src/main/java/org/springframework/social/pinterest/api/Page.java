package org.springframework.social.pinterest.api;

import java.io.Serializable;

/**
 * Created by dfc677 on 10/27/15.
 */
public class Page extends PinInterestObject implements Serializable {

    String cursor;
    String next;

    public Page() {
    }

    public Page(String cursor, String next) {
        this.cursor = cursor;
        this.next = next;
    }

    public String getCursor() {

        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
