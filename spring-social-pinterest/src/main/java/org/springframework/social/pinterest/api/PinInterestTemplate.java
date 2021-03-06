package org.springframework.social.pinterest.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.pinterest.api.impl.BoardTemplate;
import org.springframework.social.pinterest.api.impl.PinTemplate;
import org.springframework.social.pinterest.api.impl.PinterestErrorHandler;
import org.springframework.social.pinterest.api.impl.UserTemplate;
import org.springframework.social.pinterest.api.impl.json.PinterestModule;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.social.pinterest.api.impl.PagedListUtils.getPagedListParameters;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public class PinInterestTemplate extends AbstractOAuth2ApiBinding implements PinInterest {

    UserOperations userOperations;

    BoardOperations boardOperations;

    PinOperations pinOperations;

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
        super(accessToken); // Temp Patch until the retrieved token works
        Assert.hasLength(accessToken, "Access token cannot be null or empty.");
        this.applicationNamespace = applicationNamespace;
        this.appId = appId;
        initialize();
    }

    public BoardOperations getBoardOperations() {
        return boardOperations;
    }

    public PinOperations getPinOperations() {
        return pinOperations;
    }

    // private helpers
    private void initialize() {
        // Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat
        // reads on the response.getBody()
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
        initSubApis();
    }

    @Override
    public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
        // Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat
        // reads on the response.getBody()
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(requestFactory));
    }

    // AbstractOAuth2ApiBinding hooks
    @Override
    protected OAuth2Version getOAuth2Version() {
        return OAuth2Version.BEARER;
    }

    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        restTemplate.setErrorHandler(new PinterestErrorHandler());
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PinterestModule());
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    private void initSubApis() {
        userOperations = new UserTemplate(this);
        boardOperations = new BoardTemplate(this);
        pinOperations = new PinTemplate(this);
    }

    public UserOperations getUserOperations() {
        return userOperations;
    }

    @Override
    public <T> T fetchObject(String objectId, Class<T> type) {
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId.concat("/"))).build();
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

    @Override
    public <T> PagedList<T> fetchListOfObject(String objectId, String pathType, Class<T> type) {
        String connectionPath = pathType != null && pathType.length() > 0 ? "/" + pathType : "";
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId).concat(connectionPath)).build();
        JsonNode jsonNode = getRestTemplate().getForObject(uri, JsonNode.class);
        return pagify(type, jsonNode);
    }

    @Override
    public <T> PagedList<T> fetchListOfObject(String objectId, String pathType, Class<T> type, String... fields) {
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
        if (fields.length > 0) {
            String joinedFields = join(fields);
            queryParameters.set("fields", joinedFields);
        }
        return fetchListOfObject(objectId, pathType, type, queryParameters);
    }

    @Override
    public <T> PagedList<T> fetchListOfObject(String objectId, String pathType, Class<T> type,
                                              MultiValueMap<String, String> queryParmeters) {
        String connectionPath = pathType != null && pathType.length() > 0 ? "/" + pathType : "";
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId).concat(connectionPath))
                .queryParams(queryParmeters).build();
        JsonNode jsonNode = getRestTemplate().getForObject(uri, JsonNode.class);
        return pagify(type, jsonNode);
    }

    @Override
    public <T> T post(String objectId, MultiValueMap<String, String> data, Class<T> type) {
        return post(objectId, null, data, type, null);
    }

    @Override
    public <T> T post(String objectId, MultiValueMap<String, String> data, Class<T> type, String... fields) {
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
        if (fields.length > 0) {
            String joinedFields = join(fields);
            queryParameters.set("fields", joinedFields);
        }
        return post(objectId, null, data, type, queryParameters);
    }


    @Override
    public <T> T post(String objectId, String connectionName, MultiValueMap<String, String> data, Class<T> t, MultiValueMap<String, String> queryParmeters) {
        String connectionPath = connectionName != null ? "/" + connectionName : "";
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId).concat(connectionPath)).queryParams(queryParmeters).build();
        return getRestTemplate().postForObject(uri.toString(), data, t);
    }

    @Override
    public <T> T patch(String objectId, MultiValueMap<String, Object> data, Class<T> type) {
        return patch(objectId, null, data, type);
    }

    @Override
    public <T> T patch(String objectId, String connectionName, MultiValueMap<String, Object> data, Class<T> type, String... fields) {
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
        if (fields.length > 0) {
            String joinedFields = join(fields);
            queryParameters.set("fields", joinedFields);
        }
        return patch(objectId, null, data, type);
    }

    @Override
    public <T> T patch(String objectId, String connectionName, MultiValueMap<String, Object> data, Class<T> t) {
        LinkedMultiValueMap<String, Object> patchRequest = new LinkedMultiValueMap<String, Object>(data);
        patchRequest.set("method", "patch");
        String connectionPath = connectionName != null ? "/" + connectionName : "";
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId).concat(connectionPath)).build();
        return getRestTemplate().postForObject(uri, patchRequest, t);
    }

    @Override
    public String publish(String objectId, String connectionName, MultiValueMap<String, Object> data) {
        String connectionPath = connectionName != null ? "/" + connectionName : "";
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId).concat(connectionPath)).build();
        Map<String, Object> response = getRestTemplate().postForObject(uri,
                new LinkedMultiValueMap<String, Object>(data), Map.class);
        return (String) response.get("id").toString();
    }

    @Override
    public void delete(String objectId) {
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL + objectId).build();
        getRestTemplate().delete(uri);
    }

    @Override
    public void delete(String objectId, String connectionName) {
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId.concat("/").concat(connectionName))).build();
        getRestTemplate().delete(uri);
    }

    @Override
    public void delete(String objectId, String connectionName, MultiValueMap<String, String> data) {
        data.set("method", "delete");
        URI uri = URIBuilder.fromUri(PINTEREST_API_URL.concat(objectId.concat("/").concat(connectionName))).build();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(data,
                new HttpHeaders());
        getRestTemplate().exchange(uri, HttpMethod.POST, entity, String.class);
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

    private <T> PagedList<T> pagify(Class<T> type, JsonNode jsonNode) {
        List<T> data = deserializeDataList(jsonNode.get("data"), type);
        if (!jsonNode.has("page")) {
            return new PagedList<T>(data, null, null);
        }
        JsonNode pageNode = jsonNode.get("page");
        PageParameters next = getPagedListParameters(pageNode, "page");
        return new PagedList<T>(data, null, next);

    }

    @SuppressWarnings("unchecked")
    private <T> List<T> deserializeDataList(JsonNode jsonNode, final Class<T> elementType) {
        try {
            CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, elementType);
            return (List<T>) objectMapper.reader(listType).readValue(jsonNode.toString());
        } catch (IOException e) {
            throw new UncategorizedApiException("Pinterest", "Error deserializing data from Pinterest: "
                    + e.getMessage(), e);
        }
    }

}
