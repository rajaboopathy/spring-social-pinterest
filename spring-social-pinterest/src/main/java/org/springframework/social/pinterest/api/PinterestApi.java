package org.springframework.social.pinterest.api;

import org.springframework.util.MultiValueMap;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 * Defines Low level interactions against Pinterest Api
 */
public interface PinterestApi {

    <T> T fetchObject(String objectId, Class<T> type);

    <T> T fetchObject(String objectId, Class<T> type, String... fields);

    <T> T fetchObject(String objectId, Class<T> type, MultiValueMap<String, String> queryParmeters);

    <T> PagedList<T> fetchListOfObject(String objectId,String pathType, Class<T> type);

    <T> PagedList<T> fetchListOfObject(String objectId,String pathType, Class<T> type, String... fields);

    <T> PagedList<T> fetchListOfObject(String objectId,String pathType, Class<T> type, MultiValueMap<String, String> queryParmeters);

    static final String PINTEREST_API_URL = "https://api.pinterest.com/v1/";
}
