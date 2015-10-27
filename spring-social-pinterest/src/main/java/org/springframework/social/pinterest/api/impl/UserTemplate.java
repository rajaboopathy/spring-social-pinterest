package org.springframework.social.pinterest.api.impl;

import org.springframework.social.pinterest.api.PinterestApi;
import org.springframework.social.pinterest.api.User;
import org.springframework.social.pinterest.api.UserOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public class UserTemplate implements UserOperations {

    private final PinterestApi pinterestApi;
    private final RestTemplate restTemplate;

    public UserTemplate(PinterestApi pinterestApi, RestTemplate restTemplate) {
        this.pinterestApi = pinterestApi;
        this.restTemplate = restTemplate;
    }

    @Override
    public User getUserProfile() {
        return getUserProfile("me");
    }

    public User getUserProfile(String pinterestId) {
        return pinterestApi.fetchObject(pinterestId, User.class);
    }
}
