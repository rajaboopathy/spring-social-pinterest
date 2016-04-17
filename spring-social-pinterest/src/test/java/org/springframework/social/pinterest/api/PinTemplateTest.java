package org.springframework.social.pinterest.api;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @Author Raja Vijay
 * @Date 4/10/16
 */
public class PinTemplateTest extends AbstractPinterestApiTest {


    @Test
    public void testGetPin() throws Exception {
        mockServer.expect(requestTo(containsString(pinUrl("pins/12345")))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("pin"), MediaType.APPLICATION_JSON));
        Pin pin = pinterest.getPinOperations().getPin("12345");
        Assert.assertThat(pin.getId(), containsString("462041243000070645"));
    }

    @Test
    public void testEditPin() throws Exception {
        mockServer.expect(requestTo(containsString(pinUrl("pins/12345")))).andExpect(method(HttpMethod.POST))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("pin"), MediaType.APPLICATION_JSON));
        Pin pin = pinterest.getPinOperations().editPin("12345", "test", "test", "test", "test");
        Assert.assertThat(pin.getId(), containsString("462041243000070645"));
    }

    @Test
    public void testDeletePin() throws Exception {
        mockServer.expect(requestTo(pinUrl("pins/12345"))).andExpect(method(HttpMethod.DELETE))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess());
        pinterest.getPinOperations().deletePin("12345");
    }

    @Test
    public void testCreatePin() throws Exception {
        mockServer.expect(requestTo(containsString(pinUrl("pins/test/test/")))).andExpect(method(HttpMethod.POST))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("pin"), MediaType.APPLICATION_JSON));
        Pin pin = pinterest.getPinOperations().CreatePin("test", "test", createPin());
        Assert.assertThat(pin.getId(), containsString("462041243000070645"));

    }

    private Pin createPin() {
        Pin pin = new Pin("url", "id", "link");
        pin.setNote("test");
        return pin;
    }
}