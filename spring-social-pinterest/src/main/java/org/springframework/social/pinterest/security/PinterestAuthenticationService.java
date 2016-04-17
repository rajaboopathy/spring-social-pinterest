package org.springframework.social.pinterest.security;

import org.springframework.social.pinterest.api.PinInterest;
import org.springframework.social.pinterest.connect.PinterestConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

/**
 * Created by Rajaboopathy Vijay on 10/28/15.
 */
public class PinterestAuthenticationService extends OAuth2AuthenticationService<PinInterest> {

    public PinterestAuthenticationService(String apiKey, String appSecret) {
        super(new PinterestConnectionFactory(apiKey, appSecret));
    }

    public PinterestAuthenticationService(String apiKey, String appSecret, String appNameSpace) {
        super(new PinterestConnectionFactory(apiKey, appSecret, appNameSpace));
    }
}
