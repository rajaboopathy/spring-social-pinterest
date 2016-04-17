package org.springframework.social.pinterest.api.impl;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.ServerException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.pinterest.api.PinterestError;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.social.pinterest.api.PinterestErrors.SERVICE;
import static org.springframework.social.pinterest.api.PinterestErrors.UNKNOWN;

/**
 * Created by Rajaboopathy Vijay on 10/28/15.
 */
public class PinterestErrorHandler extends DefaultResponseErrorHandler {

    private static final String PINTEREST_PROVIDER_ID = "pinterest";
    private static final Log logger = LogFactory.getLog(PinterestErrorHandler.class);


    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        PinterestError error = extractErrorFromResponse(response);
        handlePinterestError(response.getStatusCode(), error);
    }

    void handlePinterestError(HttpStatus statusCode, PinterestError error) {
        if (error != null && error.getCode() != null) {
            int code = error.getCode();

            if (code == UNKNOWN) {
                throw new UncategorizedApiException(PINTEREST_PROVIDER_ID, error.getMessage(), null);
            } else if (code == SERVICE) {
                throw new ServerException(PINTEREST_PROVIDER_ID, error.getMessage());
            } else {
                throw new UncategorizedApiException(PINTEREST_PROVIDER_ID, error.getMessage(), null);
            }
        }

    }

    private PinterestError extractErrorFromResponse(ClientHttpResponse response) throws IOException {
        String json = readResponseJson(response);
        try {
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            JsonNode errorNode = mapper.readValue(json, JsonNode.class);
            if (errorNode != null) {
                String status = errorNode.has("status") ? errorNode.get("status").asText() : null;
                Integer code = errorNode.has("code") ? errorNode.get("code").intValue() : null;
                String host = errorNode.has("host") ? errorNode.get("host").asText() : null;
                //Date generatedDt = errorNode.has("generated_at") ? errorNode.get("generated_at")..get : null;
                String userMessage = errorNode.has("message") ? errorNode.get("message").asText() : null;
                String userData = errorNode.has("data") ? errorNode.get("data").asText() : null;

                PinterestError error = new PinterestError(status, code, host, null, userMessage, userData);
                if (logger.isDebugEnabled()) {
                    logger.debug("Pinterest error: ");
                    logger.debug("   CODE        : " + error.getCode());
                    logger.debug("   STATUS        : " + error.getStatus());
                    logger.debug("   HOST     : " + error.getHost());
                    logger.debug("   GENERATEDDT     : " + error.getGeneratedDate());
                    logger.debug("   USER MESSAGE  : " + error.getMessage());
                    logger.debug("   USER DATA: " + error.getData());
                }
                return error;
            }
        } catch (JsonParseException e) {
            return null;
        }
        return null;
    }

    private String readResponseJson(ClientHttpResponse response) throws IOException {
        String json = readFully(response.getBody());
        if (logger.isDebugEnabled()) {
            logger.debug("Error from Pinterest: " + json);
        }
        return json;
    }

    private String readFully(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        return sb.toString();
    }
}
