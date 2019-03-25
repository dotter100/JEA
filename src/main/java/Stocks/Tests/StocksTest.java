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

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Test
    public void GetStocks(){
        List<Stocks> stocks = new ArrayList<>();
        Stocks stock1 = new Stocks(10,"Stock1",new Company("apple"));
        Stocks stock2 = new Stocks(102,"Stock2",new Company("windows"));
        Stocks stock3 = new Stocks(20,"Stock3",new Company("microsoft"));
        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock3);

        Gson gson = new GsonBuilder().create();
        WireMock wiremock = new WireMock(8888);

        wiremock.register(get(urlEqualTo("/JEAORM/Stocks"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(gson.toJson(stocks))));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        given()
                .port(8888)
                .when().get("/JEAORM/Stocks").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.getRequestedFor(urlEqualTo("/JEAORM/Stocks")));



    }


    @Test
    public void GetStocksName(){
        List<Stocks> stocks = new ArrayList<>();
        Stocks stock1 = new Stocks(10,"Stock1",new Company("apple"));
        Stocks stock2 = new Stocks(102,"Stock2",new Company("windows"));
        Stocks stock3 = new Stocks(20,"Stock3",new Company("microsoft"));
        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock3);

        Gson gson = new GsonBuilder().create();
        WireMock wiremock = new WireMock(8888);

        wiremock.register(get(urlEqualTo("/JEAORM/Stocks/Stock1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(gson.toJson(stock1))));


        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        Response response  = given()
                .port(8888)
                .when().get("/JEAORM/Stocks/Stock1").then()
                .statusCode(200).extract().response();

        String t = response.getBody().print();
        assertEquals(t, gson.toJson(stock1));

        wiremock.verifyThat(WireMock.getRequestedFor(urlEqualTo("/JEAORM/Stocks/Stock1")));



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
