package org.springframework.social.pinterest.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.pinterest.api.Board;
import org.springframework.social.pinterest.api.Pin;

import java.io.IOException;

/**
 * @Author Raja Vijay
 * @Date 2/21/16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = PinMixin.PinDeserializer.class)
abstract class PinMixin  extends PinterestMixin{
    static class PinDeserializer extends JsonDeserializer<Pin>{

        @Override
        public Pin deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();
            JsonNode profileNode = tree.get("data") == null ? tree : tree.get("data");
            if (profileNode != null) {
                String id = profileNode.get("id").textValue();
                String link = profileNode.get("link").textValue();
                String url = profileNode.get("url").textValue();
                return new Pin(url, id, link);
            }
            return null;
        }
    }
}
