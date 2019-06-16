package com.travelagency.component;

import com.travelagency.model.*;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings({"SameParameterValue", "SpellCheckingInspection"})
public class TestTripItemResource {

    private static final int StatusCodeOK = 200;
    private static final int StatusCodeNotFound = 404;
    private Header header;

    private static final String AdminLogin = "admin";
    private static final String AdminPassword = "admin";

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    public void testCreateTripItemWithKnownProperties() {
        login(AdminLogin, AdminPassword);
        Continent continent = new Continent("continent");
        Country country = new Country("Nederland", continent);
        City city = new City("Rotterdam",country);
        Address address = new Address("Address", "1900AA", city);
        TripItem tripItem = new TripItem(TripItemType.OUTING, "TripItem", "Description", null, address,10, "2019-01-01", "2019-12-21");
        RestAssured.given().contentType("application/json").header(header).body(tripItem).when().post("/tripItems/createTripItem").then().statusCode(StatusCodeOK);
    }

    @Test
    public void testCreateTripItemWithUnknownProperties() {
        login(AdminLogin, AdminPassword);
        Continent continent = new Continent("GeenContinent");
        Country country = new Country("GeenLand", continent);
        City city = new City("BestaatNiet",country);
        Address address = new Address("Address", "1900AA", city);
        TripItem tripItem = new TripItem(TripItemType.OUTING, "TripItem", "Description", null, address,10, "2019-01-01", "2019-12-21");
        RestAssured.given().contentType("application/json").header(header).body(tripItem).when().post("/tripItems/createTripItem").then().statusCode(StatusCodeNotFound);
    }

    @Test
    public void testGetAll() {
        int numberOfTripItems = RestAssured.given().contentType("application/json").get("/tripItems/all").jsonPath().getList("").size();
        Assert.assertTrue(numberOfTripItems > 0);
    }

    private void login(String username, String password) {
        UserCredentials userCredentials = new UserCredentials(username, password);
        String token = RestAssured.given().contentType("application/json").body(userCredentials).when().post("/auth/login").then().statusCode(StatusCodeOK).extract().path("token");
        header = new Header("Authorization", "Bearer " + token);
    }
}
