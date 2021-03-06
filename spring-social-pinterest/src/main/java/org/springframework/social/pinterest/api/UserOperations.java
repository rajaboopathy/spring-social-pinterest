package org.springframework.social.pinterest.api;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public interface UserOperations {

    User getUserProfile();

    PagedList<Pin> getPins();

    PagedList<Board> getBoards();

    PagedList<Board> getSuggestedBoards(String pin);

    PagedList<Like> getLikes();

    User getaUser(String userName);

    PagedList<Pin> searchPins(String searchCriteria);

    PagedList<Board> searchBoards(String searchCriteria);

    PagedList<User> getFollowers();

    PagedList<User> getFollowingUsers();

    PagedList<Board> getFollowingBoards();

    PagedList<Interest> getInterests();

    void unFollowBoard(String board, String user);

    void unFollowUser(String user);

    User followUser(String user);

    //Creating following relationships to be added

    Board followBoard(String board, String user);

    static final String[] PROFILE_FIELDS = {
            "account_type", "id", "username", "first_name", "last_name", "bio", "counts", "created_at", "image"
    };
}
