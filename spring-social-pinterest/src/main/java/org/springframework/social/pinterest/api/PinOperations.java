package org.springframework.social.pinterest.api;

/**
 * Created by Rajaboopathy Vijay on 11/15/15.
 */
public interface PinOperations {

    Pin getPin(String pin);

    Pin editPin(String pin, String board, String username, String note, String link);

    void deletePin(String pin);

    Pin CreatePin(String board, String username, Pin pin);

    static final String[] PROFILE_FIELDS = {"attribution", "board", "color", "counts", "created_at", "creator"
            , "id", "image", "link", "media", "metadata", "note", "original_link", "url"
    };
}
