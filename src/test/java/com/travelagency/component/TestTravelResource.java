package com.travelagency.component;

import com.travelagency.model.UserCredentials;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTravelResource {

    private static final int StatusCodeOK = 200;
    private static final int StatusCodeUnauthorized = 401;

    private static final String UserLogin = "user";
    private static final String UserPassword = "user";

    private Header header;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    public void testGetAllNotLoggedIn() {
        RestAssured.given().contentType("application/json").when().get("/travels/all").then().statusCode(StatusCodeUnauthorized);
    }

    @Test
    public void testGetAllLoggedIn() {
        int limit = 1;
        login();
        int numberOfTravels = RestAssured.given().contentType("application/json").header(header).get("/travels/all/" + limit).jsonPath().getList("").size();
        Assert.assertEquals(1, numberOfTravels);
    }

    private void login() {
        UserCredentials userCredentials = new UserCredentials(UserLogin, UserPassword);
        String token = RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(StatusCodeOK).extract().path("token");
        header = new Header("Authorization", "Bearer " + token);
    }
}
