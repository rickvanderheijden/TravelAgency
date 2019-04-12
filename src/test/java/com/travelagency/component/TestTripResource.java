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
        Integer expectedId = 8;
        String expectedName = "14-DAAGSE RONDREIS HAWAIIAN SPLENDORS";
        String expectedUrl = "https://media.tuicontent.nl/2E3/2E3BCC2E2A88DA5D714748D39B92E24A.jpg";
        Integer expectedPrice = 1599;
        Integer expectedDiscount = 0;

        ResponseBody responseBody = RestAssured.given().contentType("application/json").get("/trips/" + expectedId).getBody();
        Assert.assertEquals(expectedId, responseBody.jsonPath().get("id"));
        Assert.assertEquals(expectedName, responseBody.jsonPath().get("name"));
        Assert.assertEquals(expectedUrl, responseBody.jsonPath().get("imageUrl"));
        Assert.assertEquals(expectedPrice, responseBody.jsonPath().get("totalPrice"));
        Assert.assertEquals(expectedDiscount, responseBody.jsonPath().get("discount"));
    }
}
