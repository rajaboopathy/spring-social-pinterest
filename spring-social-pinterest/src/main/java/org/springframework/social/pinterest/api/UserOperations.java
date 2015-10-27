package org.springframework.social.pinterest.api;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public interface UserOperations {

    User getUserProfile();

    static final String[] PROFILE_FIELDS = {
            "id", "username", "first_name", "last_name", "bio"
    };
}
