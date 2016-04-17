package org.springframework.social.pinterest.api.impl;

import org.springframework.social.pinterest.api.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by Rajaboopathy Vijay on 11/15/15.
 */
public class BoardTemplate implements BoardOperations {
    private final PinterestApi pinterestApi;


    public BoardTemplate(PinterestApi pinterestApi) {
        this.pinterestApi = pinterestApi;
    }

    @Override
    public Board createBoards(String name, String description) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("name", name);
        map.add("description", description);
        return pinterestApi.post("boards/", map, Board.class);
    }

    @Override
    public Board editBoards(String userName, String boardName, String name, String description) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("name", name);
        map.add("description", description);
        return pinterestApi.patch("boards", userName + "/" + boardName, map, Board.class);
    }

    @Override
    public void deleteBoards(String userName, String boardName) {
        pinterestApi.delete("boards", userName + "/" + boardName);
    }

    @Override
    public Board getUserBoard(String userName, String boardName) {
        return pinterestApi.fetchObject("boards/" + userName + "/" + boardName, Board.class);
    }

    @Override
    public PagedList<Pin> getUserBoardPins(String userName, String boardName) {
        return pinterestApi.fetchListOfObject("boards/" + userName + "/" + boardName, null, Pin.class);
    }
}
