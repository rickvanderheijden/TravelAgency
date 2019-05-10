package com.travelagency.component;

import com.travelagency.model.Trip;
import com.travelagency.model.UserCredentials;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTripResource {

    private static final int StatusCodeOK = 200;
    private Header header;

    private static final String AdminLogin = "admin";
    private static final String AdminPassword = "admin";

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    public void testCreateTrip() {
        login(AdminLogin, AdminPassword);
        Trip trip = new Trip("Test","description","summary","/image.png",10,0);
        RestAssured.given().contentType("application/json").header(header).body(trip).when().post("/trips/createTrip").then().statusCode(StatusCodeOK);
    }

    @Test
    public void testGetAll() {
        int numberOfTrips = RestAssured.given().contentType("application/json").get("/trips/all").jsonPath().getList("").size();
        Assert.assertEquals(3, numberOfTrips);
    }

    private void login(String username, String password) {
        UserCredentials userCredentials = new UserCredentials(username, password);
        String token = RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(StatusCodeOK).extract().path("token");
        header = new Header("Authorization", "Bearer " + token);
    }
}
