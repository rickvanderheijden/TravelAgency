package com.travelagency.component;

import com.travelagency.model.*;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class TestTripServiceResource {

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
    public void testCreateTripService() {
        login(AdminLogin, AdminPassword);
        Continent continent = new Continent("continent");
        Country country = new Country("Nederland", continent);
        City city = new City("IJmuiden",country);
        Address address = new Address("Address",city,"1900AA");
        TripService tripService = new TripService(ServiceType.HOTEL, "Service", "Description", "/image.png", address,10,new Date());
        RestAssured.given().contentType("application/json").header(header).body(tripService).when().post("/services/createService").then().statusCode(StatusCodeOK);
    }

    @Test
    public void testGetAll() {
        int numberOfTripServices = RestAssured.given().contentType("application/json").get("/services/all").jsonPath().getList("").size();
        Assert.assertEquals(2, numberOfTripServices);
    }

    private void login(String username, String password) {
        UserCredentials userCredentials = new UserCredentials(username, password);
        String token = RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(StatusCodeOK).extract().path("token");
        header = new Header("Authorization", "Bearer " + token);
    }
}
