package org.springframework.social.pinterest.api.impl;

import org.springframework.social.pinterest.api.*;
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
        return null;
    }

    @Override
    public Board editBoards(String userName, String boardName, String name, String description) {
        return null;
    }

    @Override
    public void deleteBoards(String userName, String boardName) {

    }

    @Override
    public Board getUserBoards(String userName, String boardName) {
        return null;
    }

    @Override
    public PagedList<Pin> getUserBoardPins(String userName, String boardName) {
        return null;
    }
}
