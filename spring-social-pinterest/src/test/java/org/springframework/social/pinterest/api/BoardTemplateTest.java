package org.springframework.social.pinterest.api;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.pinterest.api.AbstractPinterestApiTest;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @Author Raja Vijay
 * @Date 4/10/16
 */
public class BoardTemplateTest  extends AbstractPinterestApiTest{

    @Test
    public void testCreateBoards() throws Exception {

        mockServer.expect(requestTo(pinUrl("boards/?name=tech&description=tech"))).andExpect(method(HttpMethod.POST))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("board"), MediaType.APPLICATION_JSON));
        Board board = pinterest.getBoardOperations().createBoards("tech","tech");
        Assert.assertThat(board.getName(), containsString("Tech"));
    }

    @Test
    public void testEditBoards() throws Exception {

        mockServer.expect(requestTo(pinUrl("boards/tech/tech"))).andExpect(method(HttpMethod.POST))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("board"), MediaType.APPLICATION_JSON));
        Board board = pinterest.getBoardOperations().editBoards("tech","tech","tech","tech");
        Assert.assertThat(board.getName(), containsString("Tech"));

    }

    @Test
    public void testDeleteBoards() throws Exception {

        mockServer.expect(requestTo(pinUrl("boards/tech/tech"))).andExpect(method(HttpMethod.DELETE))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("board"), MediaType.APPLICATION_JSON));
        pinterest.getBoardOperations().deleteBoards("tech","tech");

    }

    @Test
    public void testGetUserBoards() throws Exception {

        mockServer.expect(requestTo(pinUrl("boards/tech/tech/"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("board"), MediaType.APPLICATION_JSON));
        Board board = pinterest.getBoardOperations().getUserBoard("tech","tech");
        Assert.assertThat(board.getName(), containsString("Tech"));
    }

    @Test
    public void testGetUserBoardPins() throws Exception {

        mockServer.expect(requestTo(pinUrl("boards/tech/tech"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("pins"), MediaType.APPLICATION_JSON));
        List<Pin> pins = pinterest.getBoardOperations().getUserBoardPins("tech","tech");
        Assert.assertTrue(pins.size()>1);

    }
}