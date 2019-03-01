package com.travelagency.component;

import com.travelagency.model.AuthorityName;
import com.travelagency.model.UserCredentials;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.ResponseBody;
import org.junit.*;

import java.util.HashMap;

public class TestUserResource {

    private static final String expectedFirstname = "userFirstName";
    private static final String expectedLastname = "userLastName";
    private static final String expectedEmailAddress = "user@travelagency.com";
    private static final boolean expectedEnabled = true;
    private static final HashMap<String, String> expectedAuthorities = new HashMap<>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;

        expectedAuthorities.put("authority", AuthorityName.ROLE_USER.toString());
    }

    @Test
    public void testGetUserFromTokenWithInvalidToken() {
        Header header = new Header("Authorization", "Bearer " + "fakjshgfioureyfguliaehysuh");
        RestAssured.given().contentType("application/json").header(header).get("/users/user").then().statusCode(401);
    }

    @Test
    public void testGetUserFromTokenWhenLoggedIn() {

        String token = login("user", "user");
        Header header = new Header("Authorization", "Bearer " + token);
        ResponseBody responseBody = RestAssured.given().contentType("application/json").header(header).get("/users/user").getBody();

        Assert.assertEquals(expectedFirstname, responseBody.jsonPath().get("firstname"));
        Assert.assertEquals(expectedLastname, responseBody.jsonPath().get("lastname"));
        Assert.assertEquals(expectedEmailAddress, responseBody.jsonPath().get("emailAddress"));
        Assert.assertEquals(expectedEnabled, responseBody.jsonPath().get("enabled"));
        Assert.assertEquals(expectedAuthorities, responseBody.jsonPath().getList("authorities").get(0));
    }

    @Test
    public void testGetAllUsersWithAdminAccount() {
        String token = login("admin", "admin");
        Header header = new Header("Authorization", "Bearer " + token);

        int numberOfUsers = RestAssured.given().contentType("application/json").header(header).get("/users/all").jsonPath().getList("").size();
        Assert.assertTrue(numberOfUsers > 0);
    }

    @Test
    public void testGetAllUsersWithUserAccount() {
        String token = login("user", "user");
        Header header = new Header("Authorization", "Bearer " + token);

        RestAssured.given().contentType("application/json").header(header).get("/users/all").then().statusCode(403);
    }

    private String login(String username, String password) {
        UserCredentials userCredentials = new UserCredentials(username, password);
        return RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(200).extract().path("token");
    }
}
