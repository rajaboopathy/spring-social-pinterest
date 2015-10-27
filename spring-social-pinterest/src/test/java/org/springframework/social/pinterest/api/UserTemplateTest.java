package org.springframework.social.pinterest.api;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public class UserTemplateTest extends AbstractPinterestApiTest {

    private static String PROFILE_FIELDS;

    static {
        StringBuilder builder = new StringBuilder(UserOperations.PROFILE_FIELDS[0]);
        for (int i = 1; i < UserOperations.PROFILE_FIELDS.length; i++) {
            builder.append("%2C").append(UserOperations.PROFILE_FIELDS[i]);
        }
        PROFILE_FIELDS = builder.toString();
    }

    @Test
    public void testGetUserProfile() throws Exception {
        mockServer.expect(requestTo(pinUrl("me")))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "OAuth someAccessToken"))
                .andRespond(withSuccess(jsonResource("profile"), MediaType.APPLICATION_JSON));
        User user = pinterest.getUserOperations().getUserProfile();
        Assert.assertEquals("Rajaboopathy", user.getFirstName());


    }
}