package org.springframework.social.pinterest.api;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Rajaboopathy Vijay on 10/27/15.
 */
public class Board extends PinInterestObject implements Serializable {

    private String url;

    private String id;

    private String name;

    private String description;

    private User creator;

    private Date createdDt;

    private Map<String, Integer> counts;

    private Map<String, Image> image;

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getCounts() {
        return counts;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public User getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Image> getImage() {
        return image;
    }
}
