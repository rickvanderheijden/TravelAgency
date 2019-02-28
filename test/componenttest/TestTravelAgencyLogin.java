package componenttest;

import com.travelagency.domain.UserCredentials;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTravelAgencyLogin {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {

        ResponseBody body = RestAssured.get("/test").getBody();
        System.out.println(body.asString());
    }

    @Test
    public void testLogin() {

        UserCredentials userCredentials = new UserCredentials("email@test.ikel", "password");
        ResponseBody responseBody = RestAssured.given().contentType("application/json").body(userCredentials).post("/login").getBody();
        System.out.println(responseBody.asString());
    }

}
