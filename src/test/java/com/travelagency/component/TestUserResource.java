package com.travelagency.component;

import com.travelagency.model.Authority;
import com.travelagency.model.AuthorityName;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.UserCredentials;
import com.travelagency.rest.DataTranfersObjects.UserDTO;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.ResponseBody;
import org.junit.*;

import java.util.*;

public class TestUserResource {

    private static final int StatusCodeOK = 200;
    private static final int StatusCodeUnauthorized = 401;
    private static final int StatusCodeForbidden = 403;


    private static final String expectedFirstname = "userFirstName";
    private static final String expectedLastname = "userLastName";
    private static final String expectedEmailAddress = "user@travelagency.com";
    private static final boolean expectedEnabled = true;
    private static final HashMap<String, String> expectedAuthorities = new HashMap<>();

    private static final String UserLogin = "user";
    private static final String UserPassword = "user";
    private static final String AdminLogin = "admin";
    private static final String AdminPassword = "admin";

    private Header header;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;

        expectedAuthorities.put("name", AuthorityName.ROLE_USER.toString());
    }

    @Test
    public void testGetUserFromTokenWithInvalidToken() {
        Header header = new Header("Authorization", "Bearer " + "fakjshgfioureyfguliaehysuh");
        RestAssured.given().contentType("application/json").header(header).get("/users/user").then().statusCode(StatusCodeUnauthorized);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetUserFromTokenWhenLoggedIn() {
        login(UserLogin, UserPassword);
        ResponseBody responseBody = RestAssured.given().contentType("application/json").header(header).get("/users/user").getBody();

        Assert.assertEquals(expectedFirstname, responseBody.jsonPath().get("firstName"));
        Assert.assertEquals(expectedLastname, responseBody.jsonPath().get("lastName"));
        Assert.assertEquals(expectedEmailAddress, responseBody.jsonPath().get("emailAddress"));
        Assert.assertEquals(expectedEnabled, responseBody.jsonPath().get("enabled"));
        Assert.assertEquals(expectedAuthorities.get("name"), ((HashMap<String, String>)responseBody.jsonPath().getList("authorities").get(0)).get("name"));
    }

    @Test
    public void testGetAllUsersWithAdminAccount() {
        login(AdminLogin, AdminPassword);
        int numberOfUsers = RestAssured.given().contentType("application/json").header(header).get("/users/all").jsonPath().getList("").size();
        Assert.assertTrue(numberOfUsers > 0);
    }

    @Test
    public void testGetAllUsersWithUserAccount() {
        login(UserLogin, UserPassword);
        int numberOfUsers = RestAssured.given().contentType("application/json").header(header).get("/users/all").jsonPath().getList("").size();
        Assert.assertTrue(numberOfUsers > 0);
    }

    @Test
    public void testCreateUserWithUserAccount() {
        login(UserLogin, UserPassword);
        RestAssured.given().contentType("application/json").when().post("/users/create").then().statusCode(StatusCodeUnauthorized);
    }

    @Test
    public void testCreateUserWithAdminAccount() {
        login(AdminLogin, AdminPassword);
        List<Authority> authorities = Collections.singletonList(new Authority(AuthorityName.ROLE_USER));
        List<TravelGroup> travelGroups = new ArrayList<>();
        UserDTO user = new UserDTO("Username", "Password", "FirstName", "LastName", "EmailAddress", "avatar-08.png", true, authorities, travelGroups);
        RestAssured.given().contentType("application/json").header(header).body(user).when().post("/users/create").then().statusCode(StatusCodeOK);
    }

    private void login(String username, String password) {
        UserCredentials userCredentials = new UserCredentials(username, password);
        String token = RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(StatusCodeOK).extract().path("token");
        header = new Header("Authorization", "Bearer " + token);
    }
}
