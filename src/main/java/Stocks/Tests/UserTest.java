package Stocks.Tests;

import Stocks.Controlleres.Stocks.TimeBasedOneTimePasswordUtil;
import Stocks.JWT.JWTLogic;
import Stocks.Models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
    private String Name = "TestUser";
    private String Password = "Testpassword";
    private String twofactor = "NVQTEKSXG5IVUI22JEQX2VZWIU2XOQCH";

    private static String jwttoken;

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Test
    public void TestACreateUser(){

        given()
                .port(8080)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("Name", Name)
                .formParam("password", Password).request()
                .formParam("twofactor",twofactor)
                .when().post("JEAORM/API/authentication/register").then()
                .statusCode(200);

    }

    @Test
    public void TestBLogin() throws Exception {

        //given().when().get("JEAORM/CreateUser").then().statusCode(200);
        Response response = given()
                .port(8080)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("username", Name)
                .formParam("password", Password).request()
                .formParam("verificationCode",TimeBasedOneTimePasswordUtil.generateCurrentNumber(twofactor))
                .when().post("/JEAORM/API/authentication").then()
                .statusCode(200).extract().response();



        DecodedJWT jwt = JWTLogic.validateToken(response.getBody().print());

        jwttoken = response.getBody().print();
        assertNotNull(jwt);
    }

    @Test
    public void TestCGetUser(){

        Response response = given()
                .port(8080)
                .header(new Header("Authorization", "Bearer " + jwttoken))
                .when().get("/JEAORM/API/StockUser").then().statusCode(200)
                .contentType("application/json").extract().response();
        response.getBody().print();


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
