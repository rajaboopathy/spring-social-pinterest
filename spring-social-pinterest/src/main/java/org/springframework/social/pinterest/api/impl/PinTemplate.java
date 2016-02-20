package org.springframework.social.pinterest.api.impl;

import org.springframework.social.pinterest.api.Pin;
import org.springframework.social.pinterest.api.PinOperations;
import org.springframework.social.pinterest.api.PinterestApi;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Rajaboopathy Vijay on 11/15/15.
 */
public class PinTemplate implements PinOperations {

    private PinterestApi pinterestApi;

    private RestTemplate restTemplate;

    public PinTemplate(PinterestApi pinterestApi, RestTemplate restTemplate) {
        this.pinterestApi = pinterestApi;
        this.restTemplate = restTemplate;
    }

    @Override
    public Pin getPin(String pin) {
        return pinterestApi.fetchObject("pins/" + pin, Pin.class);
    }

    @Override
    public Pin editPin(String pin, String board, String username, String note, String link) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("note", note);
        map.add("link", link);
        return pinterestApi.patch("pins/" + pin, "/" + username + "/" + board + "/", null, Pin.class);
    }

    @Override
    public void deletePin(String pin) {
        pinterestApi.delete("pins/" + pin);
    }
}
