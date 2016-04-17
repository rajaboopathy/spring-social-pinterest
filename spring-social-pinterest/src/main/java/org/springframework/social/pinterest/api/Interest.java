package org.springframework.social.pinterest.api;

/**
 * Created by Rajaboopathy Vijay on 11/8/15.
 */
public class Interest {


    private String id;

    private String name;

    private String url;

    public Interest(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url ;
    }

    public Interest() {
    }
}
