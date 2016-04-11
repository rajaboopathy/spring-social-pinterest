package org.springframework.social.pinterest.api;

/**
 * Created by Rajaboopathy Vijay on 11/15/15.
 */
public interface BoardOperations {

    Board createBoards(String name, String description);

    Board editBoards(String userName, String boardName, String name, String description);

    void deleteBoards(String userName, String boardName);

    Board getUserBoard(String userName, String boardName);

    PagedList<Pin> getUserBoardPins(String userName, String boardName);
}
