package org.springframework.social.pinterest.api;

import java.util.List;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public interface UserOperations {

    User getUserProfile();

    PagedList<Pin> getPins();

    PagedList<Board> getBoards();

    static final String[] PROFILE_FIELDS = {
            "id", "username", "first_name", "last_name", "bio"
    };
}
