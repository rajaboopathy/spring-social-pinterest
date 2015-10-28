package org.springframework.social.pinterest.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.pinterest.api.PinInterest;
import org.springframework.social.pinterest.api.PinInterestTemplate;
import org.springframework.social.pinterest.api.PinterestApi;

/**
 * Created by Rajaboopathy Vijay on 10/28/15.
 */
public class PinterestServiceProvider extends AbstractOAuth2ServiceProvider<PinInterest> {

    private String appNameSpace;

    public PinterestServiceProvider(String appId, String appSecret, String appNameSpace) {
        super(getOAuth2Template(appId, appSecret));
        this.appNameSpace = appNameSpace;

    }

    private static OAuth2Template getOAuth2Template(String appId, String appSecret) {
        OAuth2Template oAuth2Template = new OAuth2Template(appId, appSecret,
                "https://api.pinterest.com/oauth/",
                PinterestApi.PINTEREST_API_URL + "oauth/token");
        oAuth2Template.setUseParametersForClientAuthentication(true);
        return oAuth2Template;
    }

    @Override
    public PinInterest getApi(String accessToken) {
        return new PinInterestTemplate(accessToken);
    }
}
