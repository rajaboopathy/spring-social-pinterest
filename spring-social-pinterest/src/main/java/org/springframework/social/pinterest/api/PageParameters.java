package org.springframework.social.pinterest.api;

import java.io.Serializable;

/**
 * Created by Rajaboopathy Vijay on 10/27/15.
 * Used to hold page parameters for Pagination
 */
public class PageParameters implements Serializable {

    private final String cursor;
    private final String next;


    public PageParameters(String cursor, String next) {
        this.cursor = cursor;
        this.next = next;
    }

    public String getCursor() {
        return cursor;
    }


    public String getNext() {
        return next;
    }

}
