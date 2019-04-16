package com.travelagency.component;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTripResource {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    public void testGetAll() {
        int numberOfTrips = RestAssured.given().contentType("application/json").get("/trips/all").jsonPath().getList("").size();
        Assert.assertEquals(2, numberOfTrips);
    }
}
