package Stocks.Tests;

import Stocks.Models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.junit.WireMockRule;


public class Portfolio {

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Test
    public void GetPortfolios() {
//        Gson gson = new GsonBuilder().create();
//
//        User u = new User("TestName", "TestPassword");
//
//        u.AddPortfolios(new Stocks.Models.Portfolio("test"));
//
//        WireMock wiremock = new WireMock(8888);
//
//        wiremock.register(get(urlEqualTo("/JEAORM/Portfolio"))
//                .withHeader("Authorization", containing("Bearer"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBody(gson.toJson(u.getPortfolios()))));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        given().baseUri("http://desktop-354os6s")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VyIjoicXJjb2RlIiwiaXNzIjoiQmFydCIsIlJvbGVzIjoiVVNFUiIsIklEIjo0fQ.ATfFPRbHqhdiSfd0nWvtM14prlCxnlXyhHAdzRvFY_I"))
                .when().get("/JEAORM/API/Portfolio").then()
                .statusCode(200);

        //wiremock.verifyThat(WireMock.getRequestedFor(urlEqualTo("/JEAORM/Portfolio")));


    }


    @Test
    public void ADDPortfolios() {


        Response response = given()
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VyIjoicXJjb2RlIiwiaXNzIjoiQmFydCIsIlJvbGVzIjoiVVNFUiIsIklEIjo0fQ.ATfFPRbHqhdiSfd0nWvtM14prlCxnlXyhHAdzRvFY_I"))
                .body(new Stocks.Models.Portfolio("Test"))
                .when().post("/JEAORM/API/Portfolio/add").then()
                .statusCode(200).extract().response();

        assertEquals("True", response.getBody().print());

    }


    @Test
    public void updatePortfolios() {
        Gson gson = new GsonBuilder().create();

        User u = new User("TestName", "TestPassword");

        u.AddPortfolios(new Stocks.Models.Portfolio("test"));
        u.AddPortfolios(new Stocks.Models.Portfolio("beta"));

        WireMock wiremock = new WireMock(8888);

        wiremock.register(post(urlEqualTo("/JEAORM/Portfolio"))
                .withHeader("Authorization", containing("Bearer"))
                .withRequestBody(containing("beta"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(gson.toJson(u.getPortfolios()))));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        given()
                .port(8888)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer"))
                .body(new Stocks.Models.Portfolio("beta"))
                .when().post("/JEAORM/Portfolio").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/JEAORM/Portfolio")));


    }


}