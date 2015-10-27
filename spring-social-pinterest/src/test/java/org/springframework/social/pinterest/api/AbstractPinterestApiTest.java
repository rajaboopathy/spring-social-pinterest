package org.springframework.social.pinterest.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.client.MockRestServiceServer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public class AbstractPinterestApiTest {

    protected static final String ACCESS_TOKEN = "someAccessToken";

    protected PinInterestTemplate pinterest;
    protected MockRestServiceServer mockServer;
    protected MockRestServiceServer unauthorizedMockServer;
    protected MockRestServiceServer appPinterestMockServer;

    @Before
    public void setup() {
        pinterest = createPinterestTemplate();
        mockServer = MockRestServiceServer.createServer(pinterest.getRestTemplate());

    }

    protected PinInterestTemplate createPinterestTemplate() {
        return new PinInterestTemplate(ACCESS_TOKEN, "APP_NAMESPACE", "APP_ID");
    }

    protected Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json", getClass());
    }

    protected Date toDate(String dateString) {
        try {
            return PI_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    protected String pinUrl(String path) {
        return PinterestApi.PINTEREST_API_URL + path;
    }

    private static final DateFormat PI_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);


}