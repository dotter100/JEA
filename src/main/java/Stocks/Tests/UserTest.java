package Stocks.Tests;

import Stocks.Models.User;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class UserTest {

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Test
    public void CreateUser(){
        User u = new User("TestName", "TestPassword");

        WireMock wiremock = new WireMock(8888);

        wiremock.register(post(urlEqualTo("/JEAORM/CreateUser"))
                .withRequestBody(containing("TestName"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("")));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        given()
                .port(8888)
               // .contentType("application/json")
                .body(u)
                .when().post("/JEAORM/CreateUser").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/JEAORM/CreateUser")));



    }
}
