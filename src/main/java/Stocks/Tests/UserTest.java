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
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Test
    public void CreateUser(){
        User u = new User("TestName", "TestPassword");

        WireMock wiremock = new WireMock(8888);

        wiremock.register(put(urlEqualTo("/JEAORM/CreateUser"))
                .withHeader("Content-Type", containing("json"))
                .withRequestBody(containing("TestName"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("")));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        given()
                .port(8888)
                .contentType("application/json")
                .body(u)
                .when().put("/JEAORM/CreateUser").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.putRequestedFor(urlEqualTo("/JEAORM/CreateUser")));



    }

    @Test
    public void Login() throws UnsupportedEncodingException {
        User u = new User("TestName2", "TestPassword2");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("Bart")
                .sign(algorithm);


        WireMock wiremock = new WireMock(8888);

        wiremock.register(post(urlEqualTo("/JEAORM/User/login"))
                .withHeader("Content-Type", containing("json"))
                .withRequestBody(containing("TestPassword2"))
                .withRequestBody(containing("TestName2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(token)));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        Response response = given()
                .port(8888)
                .contentType("application/json")
                .body(u)
                .when().post("/JEAORM/User/login").then()
                .statusCode(200).extract().response();


        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Bart")
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);

        assertNotNull(jwt);
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/JEAORM/User/login")));

    }

    @Test
    public void GetUser(){
        User u = new User("TestName2", "TestPassword2");

        Gson gson = new GsonBuilder().create();

        WireMock wiremock = new WireMock(8888);

        wiremock.register(get(urlEqualTo("/JEAORM/User/0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("content-type", "application/json")
                        .withBody(gson.toJson(u))));

        Response response = given().pathParam("ID", 0)
                .port(8888)
                .headers("Content-Type", "application/json", "Accept", "application/json")
                .when().get("/JEAORM/User/{ID}").then()
                .contentType("application/json").extract().response();

        User user = gson.fromJson(response.getBody().print(),User.class);


        assertEquals(u.toString(), user.toString());
        wiremock.verifyThat(WireMock.getRequestedFor(urlEqualTo("/JEAORM/User/0")));

    }


    @Test
    public void validatortest(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User("test","");
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        for (ConstraintViolation<User> violation : violations) {
            //log.error(violation.getMessage());
            System.out.println(violation.getMessage());
        }
    }
}
