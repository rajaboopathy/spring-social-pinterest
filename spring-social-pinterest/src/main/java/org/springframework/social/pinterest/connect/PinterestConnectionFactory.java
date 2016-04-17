package org.springframework.social.pinterest.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.pinterest.api.PinInterest;

/**
 * Created by Rajaboopathy Vijay on 10/28/15.
 */
public class PinterestConnectionFactory extends OAuth2ConnectionFactory<PinInterest> {

    public PinterestConnectionFactory(String appId, String appSecret) {
        this(appId, appSecret, null);
    }

    public PinterestConnectionFactory(String appId, String appSecret, String appNamespace) {
        super("pinterest", new PinterestServiceProvider(appId, appSecret, appNamespace), new PinterestAdapter());
    }
}
