package org.springframework.social.pinterest.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.pinterest.api.PinInterestTemplate;
import org.springframework.social.pinterest.api.User;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Created by dfc677 on 10/26/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = UserMixin.UserDeserializer.class)
abstract class UserMixin extends PinterestMixin {
    static class UserDeserializer extends JsonDeserializer<User> {
        @Override
        public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();
            JsonNode profileNode = tree.get("data");
            if (profileNode != null) {
                String id = profileNode.get("id").textValue();
                String firstName = profileNode.get("first_name").textValue();
                String lastName = profileNode.get("last_name").textValue();
                String url = profileNode.get("url").textValue();
                return new User(id, firstName, lastName, url);
            }
            return null;
        }

    }
}
