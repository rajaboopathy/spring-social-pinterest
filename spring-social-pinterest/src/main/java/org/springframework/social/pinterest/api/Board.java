package org.springframework.social.pinterest.api;

import java.io.Serializable;

/**
 * Created by Rajaboopathy Vijay on 10/27/15.
 */
public class Board extends  PinInterestObject implements Serializable {

    private  String url;
    private  String id;
    private  String name ;


    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
