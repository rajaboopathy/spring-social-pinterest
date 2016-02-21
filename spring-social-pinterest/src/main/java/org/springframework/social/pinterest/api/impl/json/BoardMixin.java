package org.springframework.social.pinterest.api.impl.json;

import java.io.IOException;

import org.springframework.social.pinterest.api.Board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @Author dfc677
 * @Date 2/21/16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BoardMixin.BoardDeserializer.class)
abstract class BoardMixin extends PinterestMixin {
    static class BoardDeserializer extends JsonDeserializer<Board> {

        @Override
        public Board deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode tree = jp.readValueAsTree();
            JsonNode profileNode = tree.get("data");
            if (profileNode != null) {
                String id = profileNode.get("id").textValue();
                String name = profileNode.get("name").textValue();
                String url = profileNode.get("url").textValue();
                return new Board(url, id, name);
            }
            return null;
        }
    }
}
