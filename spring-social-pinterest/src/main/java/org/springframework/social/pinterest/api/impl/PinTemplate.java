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

    public PinTemplate(PinterestApi pinterestApi) {
        this.pinterestApi = pinterestApi;
    }

    @Override
    public Pin getPin(String pin) {
        return pinterestApi.fetchObject("pins/" + pin, Pin.class);
    }

    @Override
    public Pin editPin(String pin, String board, String username, String note, String link) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("note", note);
        map.add("link", link);
        return pinterestApi.patch("pins/" + pin, username + "/" + board + "/", map, Pin.class);
    }

    @Override
    public void deletePin(String pin) {
        pinterestApi.delete("pins/" + pin);
    }

    @Override
    public Pin CreatePin(String board, String username, Pin pin) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("board", username + "/" + board + "/");
        map.add("note", pin.getNote());
        map.add("link", pin.getLink());
        map.add("image_url", pin.getLink());
        return pinterestApi.post("pins/" + username + "/" + board + "/", map, Pin.class);
    }
}
