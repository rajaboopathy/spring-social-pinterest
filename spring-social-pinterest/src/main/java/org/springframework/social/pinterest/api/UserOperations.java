package org.springframework.social.pinterest.api;

import sun.print.PageableDoc;

import java.util.List;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public interface UserOperations {

    User getUserProfile();

    PagedList<Pin> getPins();

    PagedList<Board> getBoards();

    PagedList<User> getFollowers();

    PagedList<User> getFollowingUsers();

    PagedList<Board> getFollowingBoards();

    PagedList<Interest> getInterests();

    static final String[] PROFILE_FIELDS = {
            "id", "username", "first_name", "last_name", "bio"
    };
}
