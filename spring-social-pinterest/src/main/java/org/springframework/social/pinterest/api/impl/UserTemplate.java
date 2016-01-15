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
        return pinterestApi.fetchListOfObject("me", "pins", Pin.class);
    }

    @Override
    public PagedList<Board> getBoards() {
        return pinterestApi.fetchListOfObject("me", "boards", Board.class);
    }

    @Override
    public PagedList<Board> getSuggestedBoards(String pin) {
        return pinterestApi.fetchListOfObject("me","boards/suggested",Board.class,pin);
    }

    @Override
    public PagedList<Like> getLikes() {
        return pinterestApi.fetchListOfObject("me", "likes", Like.class);
    }

    @Override
    public User getaUser(String userName) {
        return pinterestApi.fetchObject("users/" + userName, User.class);
    }

    @Override
    public PagedList<Pin> searchPins(String searchCriteria) {
        return pinterestApi.fetchListOfObject("me","search/pins",Pin.class,searchCriteria);
    }

    @Override
    public PagedList<Board> searchBoards(String searchCriteria) {
        return pinterestApi.fetchListOfObject("me","search/boards",Board.class,searchCriteria);
    }

    @Override
    public PagedList<User> getFollowers() {
        return pinterestApi.fetchListOfObject("me", "followers", User.class);
    }

    @Override
    public PagedList<User> getFollowingUsers() {
        return pinterestApi.fetchListOfObject("me", "following/users", User.class);
    }

    @Override
    public PagedList<Board> getFollowingBoards() {
        return pinterestApi.fetchListOfObject("me", "following/boards", Board.class);
    }

    @Override
    public PagedList<Interest> getInterests() {
        return pinterestApi.fetchListOfObject("me", "following/interests", Interest.class);
    }

    //Creating following relationships to be added
    //Delete the board and user to unfollow
}
