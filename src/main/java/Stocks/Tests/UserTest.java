package Stocks.Tests;

import Stocks.Models.User;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class UserTest {

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Test
    public void CreateUser(){
        User u = new User("TestName", "TestPassword");

        WireMock wiremock = new WireMock(8888);

        wiremock.register(put(urlEqualTo("/JEAORM/CreateUser"))
                .withRequestBody(containing("TestName"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("")));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        given()
                .port(8888)
               // .contentType("application/json")
                .body(u)
                .when().put("/JEAORM/CreateUser").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.putRequestedFor(urlEqualTo("/JEAORM/CreateUser")));



    }

    @Test
    public void Login(){
        User u = new User("TestName2", "TestPassword2");


        WireMock wiremock = new WireMock(8888);

        wiremock.register(post(urlEqualTo("/JEAORM/User/login"))
                .withRequestBody(containing("TestPassword2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("")));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        given()
                .port(8888)
                .contentType("application/json")
                .body(u)
                .when().post("/JEAORM/User/login").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/JEAORM/User/login")));

    }

    @Test
    public void GetUser(){
        User u = new User("TestName2", "TestPassword2");

        Gson gson = new GsonBuilder().create();




        WireMock wiremock = new WireMock(8888);

        wiremock.register(get(urlEqualTo("/JEAORM/User"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(u))));

        Response response = given()
                .port(8888)
                .headers("Content-Type", "application/json", "Accept", "application/json")
                .when().get("/JEAORM/User").then()
                .contentType("application/json").extract().response();

        User user = gson.fromJson(response.getBody().print(),User.class);


        Assert.assertEquals(u.toString(), user.toString());
        wiremock.verifyThat(WireMock.getRequestedFor(urlEqualTo("/JEAORM/User")));

    }
}
