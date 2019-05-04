package Stocks.Tests;

import Stocks.Models.Company;
import Stocks.Models.Stocks;
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
import org.junit.Rule;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.junit.WireMockRule;


public class StocksTest {



    @Test
    public void GetStocks(){

        Response response  = given()
                .port(8080)
                .when().get("/JEAORM/API/Stocks").then()
                .statusCode(200).extract().response();
        response.getBody().print();



    }


    @Test
    public void GetStocksName(){


        Response response  = given()
                .port(8080)
                .when().get("/JEAORM/API/Stocks/Apple inc").then()
                .statusCode(200).extract().response();

        response.getBody().print();

        Response response2  = given()
                .port(8080)
                .when().get("/JEAORM/API/Stocks/Microsoft").then()
                .statusCode(200).extract().response();
        response2.getBody().print();





        Response response3  = given()
                .port(8080)
                .when().get("/JEAORM/API/Stocks/Facebook21313").then()
                .statusCode(204).extract().response();

        response3.getBody().print();




    }



//    @Test
//    public void validatortest(){
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        User user = new User("test","");
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//
//        for (ConstraintViolation<User> violation : violations) {
//            //log.error(violation.getMessage());
//            System.out.println(violation.getMessage());
//        }
//    }
}
