package org.springframework.social.pinterest.api;

import org.springframework.util.MultiValueMap;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 * Defines Low level interactions against Pinterest Api
 */
public interface PinterestApi {

    static final String PINTEREST_API_URL = "https://api.pinterest.com/v1/";

    <T> T fetchObject(String objectId, Class<T> type);

    <T> T fetchObject(String objectId, Class<T> type, String... fields);

    <T> T fetchObject(String objectId, Class<T> type, MultiValueMap<String, String> queryParmeters);

    <T> PagedList<T> fetchListOfObject(String objectId, String pathType, Class<T> type);

    <T> PagedList<T> fetchListOfObject(String objectId, String pathType, Class<T> type, String... fields);

    <T> PagedList<T> fetchListOfObject(String objectId, String pathType, Class<T> type, MultiValueMap<String, String> queryParmeters);

    <T> T post(String objectId, MultiValueMap<String, String> data, Class<T> type);

    <T> T post(String objectId, MultiValueMap<String, String> data, Class<T> type, String... fields);

    <T> T post(String objectId, String connectionName, MultiValueMap<String, String> data, Class<T> type,MultiValueMap<String, String> queryParmeters);

    <T> T patch(String objectId, MultiValueMap<String, Object> data, Class<T> type);

    <T> T patch(String objectId, String connectionName, MultiValueMap<String, Object> data, Class<T> type, String... fields);

    <T> T patch(String objectId, String connectionName, MultiValueMap<String, Object> data, Class<T> type);

    String publish(String objectId, String connectionName, MultiValueMap<String, Object> data);

    void delete(String objectId);

    void delete(String objectId, String connectionName);

    void delete(String objectId, String connectionName, MultiValueMap<String, String> data);
}
