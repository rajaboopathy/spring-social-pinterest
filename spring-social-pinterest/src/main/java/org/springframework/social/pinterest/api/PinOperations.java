package org.springframework.social.pinterest.api;

/**
 * Created by Rajaboopathy Vijay on 11/15/15.
 */
public interface PinOperations {

    Pin getPin(String pin);

    Pin editPin(String pin, String board, String username, String note, String link);

    void deletePin(String pin);
}
