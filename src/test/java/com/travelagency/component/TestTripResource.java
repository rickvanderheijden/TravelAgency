package com.travelagency.component;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
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
    public void testGetTripById() {
        Integer expectedId = 3;
        String expectedName = "14-DAAGSE RONDREIS HAWAIIAN SPLENDORS";

        ResponseBody responseBody = RestAssured.given().contentType("application/json").get("/trips/" + expectedId).getBody();
        Assert.assertEquals(expectedId, responseBody.jsonPath().get("id"));
        Assert.assertEquals(expectedName, responseBody.jsonPath().get("name"));
    }
}
