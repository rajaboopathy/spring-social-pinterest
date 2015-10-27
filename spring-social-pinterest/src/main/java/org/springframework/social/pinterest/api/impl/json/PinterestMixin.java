package org.springframework.social.pinterest.api.impl.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Rajaboopathy Vijay on 10/26/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class PinterestMixin {

    @JsonAnySetter
    abstract void add(String key, Object value);
}
