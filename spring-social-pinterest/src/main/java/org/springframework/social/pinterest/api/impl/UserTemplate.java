package org.springframework.social.pinterest.api.impl;

import org.springframework.social.pinterest.api.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @Override
    public PagedList<Pin> getPins() {
        return pinterestApi.fetchListOfObject("me","search/pins",Pin.class);
    }

    @Override
    public PagedList<Board> getBoards() {
        return pinterestApi.fetchListOfObject("me","search/boards",Board.class);
    }

    public User getUserProfile(String pinterestId) {
        return pinterestApi.fetchObject(pinterestId, User.class);
    }
}
