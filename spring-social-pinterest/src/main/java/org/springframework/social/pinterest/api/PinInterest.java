package org.springframework.social.pinterest.api;

import org.springframework.social.ApiBinding;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public interface PinInterest extends PinterestApi, ApiBinding {

    UserOperations getUserOperations();

    BoardOperations getBoardOperations();

    PinOperations getPinOperations();

}
