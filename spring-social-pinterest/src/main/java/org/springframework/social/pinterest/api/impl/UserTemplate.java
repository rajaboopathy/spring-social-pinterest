package org.springframework.social.pinterest.api.impl;

import org.springframework.social.pinterest.api.*;
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

    @Override
    public PagedList<Pin> getPins() {
        return pinterestApi.fetchListOfObject("me","search/pins",Pin.class);
    }

    @Override
    public PagedList<Board> getBoards() {
        return pinterestApi.fetchListOfObject("me","search/boards",Board.class);
    }

    @Override
    public PagedList<User> getFollowers() {
        return pinterestApi.fetchListOfObject("me","followers",User.class);
    }

    @Override
    public PagedList<User> getFollowingUsers() {
        return pinterestApi.fetchListOfObject("me","following/users",User.class);
    }

    @Override
    public PagedList<Board> getFollowingBoards() {
        return pinterestApi.fetchListOfObject("me","following/boards",Board.class);
    }

    @Override
    public PagedList<Interest> getInterests() {
        return pinterestApi.fetchListOfObject("me","following/interests",Interest.class);
    }
}
