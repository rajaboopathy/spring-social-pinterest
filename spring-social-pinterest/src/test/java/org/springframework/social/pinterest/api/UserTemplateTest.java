package org.springframework.social.pinterest.api;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
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

    // Fetch User Data test starts
    @Test
    public void testGetUserProfile() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("profile"), MediaType.APPLICATION_JSON));
        User user = pinterest.getUserOperations().getUserProfile();
        Assert.assertEquals("Rajaboopathy", user.getFirstName());
    }

    @Test
    public void testGetUserLikes() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/likes"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("likes"), MediaType.APPLICATION_JSON));
        PagedList<Like> likes = pinterest.getUserOperations().getLikes();
        Assert.assertEquals(2, likes.size());
        Assert.assertThat(likes.get(0).getNote(), containsString("Modified"));
    }

    @Test
    public void testGetUserPins() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/pins"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("pins"), MediaType.APPLICATION_JSON));
        PagedList<Pin> pins = pinterest.getUserOperations().getPins();
        Assert.assertTrue(pins.size() > 2);
        Assert.assertEquals(pins.get(0).getId(), "462041243000070645");

    }

    @Test
    public void testGetUserBoards() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/boards"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("boards"), MediaType.APPLICATION_JSON));
        PagedList<Board> boards = pinterest.getUserOperations().getBoards();
        Assert.assertTrue(boards.size() > 1);
    }

    @Test
    public void testGetUserBoardsSuggested() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/boards/suggested?fields=123"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("boards"), MediaType.APPLICATION_JSON));
        PagedList<Board> boards = pinterest.getUserOperations().getSuggestedBoards("123");
        Assert.assertTrue(boards.size() > 1);
    }
    // Fetch User Data test ends

    // Search User Data starts
    @Test
    public void testSearchPins() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/search/pins?fields=query"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("pins"), MediaType.APPLICATION_JSON));
        PagedList<Pin> pins = pinterest.getUserOperations().searchPins("query");
        Assert.assertNotNull(pins);

    }

    @Test
    public void testSearchBoards() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/search/boards?fields=criteria"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("boards"), MediaType.APPLICATION_JSON));
        PagedList<Board> boards = pinterest.getUserOperations().searchBoards("criteria");
        Assert.assertNotNull(boards);
    }
    // Search User Data Ends

    // Fetch follow data
    @Test
    public void testFollowers() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/followers"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("users"), MediaType.APPLICATION_JSON));
        PagedList<User> users = pinterest.getUserOperations().getFollowers();
        Assert.assertNotNull(users);
    }

    @Test
    public void testFollowingBoards() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/following/boards"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("boards"), MediaType.APPLICATION_JSON));
        PagedList<Board> boards = pinterest.getUserOperations().getFollowingBoards();
        Assert.assertNotNull(boards);
    }

    @Test
    public void testFollowingInterests() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/following/interests"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("interests"), MediaType.APPLICATION_JSON));
        PagedList<Interest> interests = pinterest.getUserOperations().getInterests();
        Assert.assertNotNull(interests);
    }

    @Test
    public void testFollowingUsers() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/following/users"))).andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("users"), MediaType.APPLICATION_JSON));
        PagedList<User> users = pinterest.getUserOperations().getFollowingUsers();
        Assert.assertNotNull(users);
    }
    // Fetch follow data Ends

    //Create follow data starts
    @Test
    public void testFollowBoard() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/following/boards?board=test%252Ftest"))).andExpect(method(HttpMethod.POST))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("board"), MediaType.APPLICATION_JSON));
        Board board = pinterest.getUserOperations().followBoard("test", "test");
        Assert.assertThat(board.getName(), containsString("Tech"));
    }

    @Test
    public void testFollowUser() throws Exception {
        mockServer.expect(requestTo(pinUrl("me/following/users?user=test"))).andExpect(method(HttpMethod.POST))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("user"), MediaType.APPLICATION_JSON));
        User user = pinterest.getUserOperations().followUser("test");
        Assert.assertThat(user.getFirstName(), containsString("Rajaboopathy"));
    }
    // Create follow data ends

    //Remove follow data
    @Test
    public void testUnfollowUser() throws Exception {

        mockServer.expect(requestTo(pinUrl("me/following/users/test"))).andExpect(method(HttpMethod.DELETE))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess());
        pinterest.getUserOperations().unFollowUser("test");
    }

    @Test
    public void testUnfollowBoard() throws Exception {

        mockServer.expect(requestTo(pinUrl("me/following/boards/test/test"))).andExpect(method(HttpMethod.DELETE))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess());
        pinterest.getUserOperations().unFollowBoard("test", "test");

    }
    //Remove follow data ends
}