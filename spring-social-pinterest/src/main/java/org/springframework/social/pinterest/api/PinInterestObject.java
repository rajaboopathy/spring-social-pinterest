package org.springframework.social.pinterest.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 * Abstract Version of the PinInterest Object
 */
public abstract class PinInterestObject {
    private Map<String, Object> extradata;

    public PinInterestObject() {
        this.extradata = new HashMap<String, Object>();
    }

    public Map<String, Object> getExtradata() {
        return extradata;
    }

    protected void add(String key,Object value){
        extradata.put(key,value);
    }
}
