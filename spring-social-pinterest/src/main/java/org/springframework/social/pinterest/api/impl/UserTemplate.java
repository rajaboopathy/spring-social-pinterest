package org.springframework.social.pinterest.api.impl;

import org.springframework.social.pinterest.api.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public class UserTemplate implements UserOperations {

    private final PinterestApi pinterestApi;

    public UserTemplate(PinterestApi pinterestApi) {
        this.pinterestApi = pinterestApi;
    }

    @Override
    public User getUserProfile() {
        return getUserProfile("me");
    }

    public User getUserProfile(String pinterestId) {
        return pinterestApi.fetchObject(pinterestId, User.class);
    }

    @Override
    public PagedList<Board> getBoards() {
        return pinterestApi.fetchListOfObject("me", "boards", Board.class);
    }

    @Override
    public PagedList<Board> getSuggestedBoards(String pin_id) {
        return pinterestApi.fetchListOfObject("me", "boards/suggested", Board.class, pin_id);
    }

    @Override
    public PagedList<Like> getLikes() {
        return pinterestApi.fetchListOfObject("me", "likes", Like.class);
    }

    @Override
    public PagedList<Pin> getPins() {
        return pinterestApi.fetchListOfObject("me", "pins", Pin.class);
    }

    @Override
    public User getaUser(String userName) {
        return pinterestApi.fetchObject("users/" + userName, User.class);
    }

    @Override
    public PagedList<Board> searchBoards(String searchCriteria) {
        return pinterestApi.fetchListOfObject("me", "search/boards", Board.class, searchCriteria);
    }

    @Override
    public PagedList<Pin> searchPins(String searchCriteria) {
        return pinterestApi.fetchListOfObject("me", "search/pins", Pin.class, searchCriteria);
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

    @Override
    public void unFollowBoard(String board, String user) {
        pinterestApi.delete("me/following/boards/" + board + "/" + user);
    }

    @Override
    public void unFollowUser(String user) {
        pinterestApi.delete("me/following/users/" + user);
    }

    @Override
    public User followUser(String user) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("user", user);
        return pinterestApi.post("me/following/users", map, User.class);
    }

    @Override
    public Board followBoard(String board, String user) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
        multiValueMap.add("board", user + "/" + board);
        return pinterestApi.post("me/following/boards", multiValueMap, Board.class);
    }
}
