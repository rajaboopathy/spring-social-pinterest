package org.springframework.social.pinterest.OAuth2Pinterest;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Created by dfc677 on 10/30/15.
 */
public class OAuth2TemplatePinterest extends OAuth2Template {

    public OAuth2TemplatePinterest(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    }

    public OAuth2TemplatePinterest(String clientId, String clientSecret, String authorizeUrl, String authenticateUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, authenticateUrl, accessTokenUrl);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        return this.extractAccessGrant((Map) this.getRestTemplate().postForObject(accessTokenUrl, parameters, Map.class, new Object[0]));
    }
    //Nullified scope
    private AccessGrant extractAccessGrant(Map<String, Object> result) {
        return this.createAccessGrant((String)result.get("access_token"),null, (String)result.get("refresh_token"), this.getIntegerValue(result, "expires_in"), result);
    }

    private Long getIntegerValue(Map<String, Object> map, String key) {
        try {
            return Long.valueOf(String.valueOf(map.get(key)));
        } catch (NumberFormatException var4) {
            return null;
        }
    }

}
