package org.springframework.social.pinterest.api.impl;

import org.springframework.social.pinterest.api.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Rajaboopathy Vijay on 11/15/15.
 */
public class BoardTemplate implements BoardOperations {
    private final PinterestApi pinterestApi;

    private final RestTemplate restTemplate;

    public BoardTemplate(PinterestApi pinterestApi, RestTemplate restTemplate) {
        this.pinterestApi = pinterestApi;
        this.restTemplate = restTemplate;
    }

    @Override
    public Board createBoards(String name, String description) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        map.add("description", description);
        return pinterestApi.post("v1/Boards/", map, Board.class);
    }

    @Override
    public Board editBoards(String userName, String boardName, String name, String description) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        map.add("description", description);
        return pinterestApi.patch("v1/Boards/", "/" + userName + "/" + boardName + "/", map, Board.class);
    }

    @Override
    public void deleteBoards(String userName, String boardName) {
        pinterestApi.delete("v1/Boards/", "/" + userName + "/" + boardName + "/");
    }

    @Override
    public Board getUserBoards(String userName, String boardName) {
        return pinterestApi.fetchObject("v1/Boards/" + userName + "/" + boardName + "/", Board.class);
    }

    @Override
    public PagedList<Pin> getUserBoardPins(String userName, String boardName) {
        return pinterestApi.fetchListOfObject("v1/Boards/" + userName + "/" + boardName + "/", null, Pin.class);
    }
}
