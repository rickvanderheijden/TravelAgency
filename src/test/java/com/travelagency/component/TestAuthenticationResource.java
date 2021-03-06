package com.travelagency.component;

import com.travelagency.model.UserCredentials;
import com.travelagency.rest.DataTranfersObjects.UserDTO;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.*;

public class TestAuthenticationResource {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    public void testLoginInvalidUserNameAndValidPath() {

        UserCredentials userCredentials = new UserCredentials("invalid", "user");
        RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(401);
    }

    @Test
    public void testLoginInvalidPasswordAndValidPath() {

        UserCredentials userCredentials = new UserCredentials("user", "invalid");
        RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(401);
    }

    @Test
    public void testLoginValidCredentialsAndValidPath() {

        UserCredentials userCredentials = new UserCredentials("user", "user");
        String token = RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(200).extract().path("token");
        Assert.assertNotNull(token);
    }

    @Test
    public void testRefreshWithLoggedInUserAndInvalidToken() {
        UserCredentials userCredentials = new UserCredentials("user", "user");
        RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(200).extract().path("token");

        Header header = new Header("Authorization", "Bearer " + "invalidToken");
        RestAssured.given().contentType("application/json").header(header).get("/auth/refresh").then().statusCode(401);
    }

    @Test
    public void testRefreshWithLoggedInUserAndValidToken() {
        UserCredentials userCredentials = new UserCredentials("user", "user");
        String token = RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(200).extract().path("token");
        Assert.assertNotNull(token);

        Header header = new Header("Authorization", "Bearer " + token);
        String refreshedToken = RestAssured.given().contentType("application/json").header(header).get("/auth/refresh").then().statusCode(200).extract().path("token");

        System.out.println(refreshedToken);
    }

    @Test
    public void testRegisterUser() {
        UserDTO user = new UserDTO(
                null,
                "UsernameRegisterTest",
                "Password",
                "FirstName",
                "LastName",
                "EmailAddress",
                "avatar-01.png",
                true,
                null,
                null);
        RestAssured.given().contentType("application/json").body(user).when().post("/auth/register").then().statusCode(200);
    }

    //TODO: Move to a more suitable place (too generic)
    @Test
    public void testLoginValidCredentialsAndInvalidPath() {

        UserCredentials userCredentials = new UserCredentials("user", "user");
        RestAssured.given().contentType("application/json").body(userCredentials).when().post("/doesNotExist").then().statusCode(401);
    }
}
