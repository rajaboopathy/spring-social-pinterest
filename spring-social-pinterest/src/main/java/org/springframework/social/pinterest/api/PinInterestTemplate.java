package org.springframework.social.pinterest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.pinterest.api.impl.UserTemplate;
import org.springframework.social.pinterest.api.impl.json.PinterestModule;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by dfc677 on 10/25/15.
 */
public class PinInterestTemplate extends AbstractOAuth2ApiBinding implements PinInterest {

    UserOperations userOperations;
    String applicationNamespace;
    String appId;
    private ObjectMapper objectMapper;

    public PinInterestTemplate(String accessToken) {
        this(accessToken, null, null);
    }

    public PinInterestTemplate(String accessToken, String applicationNamespace) {
        this(accessToken, applicationNamespace, null);
    }

    public PinInterestTemplate(String accessToken, String applicationNamespace, String appId) {
        super(accessToken);
        this.applicationNamespace = applicationNamespace;
        this.appId = appId;
        initialize();
    }

    // private helpers
    private void initialize() {
        // Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
        initSubApis();
    }
    // AbstractOAuth2ApiBinding hooks
    @Override
    protected OAuth2Version getOAuth2Version() {
        return OAuth2Version.DRAFT_10;
    }


    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PinterestModule());
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    //TODO Exception Handling to be added for Service failures

    private void initSubApis() {
        userOperations = new UserTemplate(this, getRestTemplate());
    }

    public UserOperations getUserOperations() {
        return userOperations;
    }

    @Override
    public <T> T fetchObject(String objectId, Class<T> type) {
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId)).build();
        return getRestTemplate().getForObject(uri, type);
    }

    @Override
    public <T> T fetchObject(String objectId, Class<T> type, String... fields) {
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
        if (fields.length > 0) {
            String joinedFields = join(fields);
            queryParameters.set("fields", joinedFields);
        }
        return fetchObject(objectId, type, queryParameters);
    }


    @Override
    public <T> T fetchObject(String objectId, Class<T> type, MultiValueMap<String, String> queryParmeters) {
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId)).queryParams(queryParmeters).build();
        return getRestTemplate().getForObject(uri, type);
    }

    private String join(String[] fields) {
        StringBuilder builder = new StringBuilder();
        if (fields.length > 0) {
            builder.append(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                builder.append(fields[i]);
            }
        }
        return builder.toString();
    }
}
