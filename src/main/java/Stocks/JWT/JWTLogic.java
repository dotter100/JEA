package Stocks.JWT;

import Stocks.Models.Roles;
import Stocks.Models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.http.client.utils.DateUtils;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

public class JWTLogic {


    private  static String secret = "secret";


    //JWT token issue function takes a user object to create the jwt and then returns a string
    public static String issueToken(User user) throws Exception {

        try {
            long nowMillis = System.currentTimeMillis();
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR,2);

            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("Bart")
                    .withIssuedAt(new Date(nowMillis))
                    .withExpiresAt(cal.getTime())
                    .withClaim("User",user.getName())
                    .withClaim("ID",user.getID())
                    .withClaim("Roles" , String.valueOf(user.getRole()))

                    .sign(algorithm);
            return token;


        }catch (Exception e){
            throw new Exception();

        }

    }


    public static DecodedJWT validateToken(String token) throws Exception {
        // Check if the token was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
                .withIssuer("Bart")
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);

        return jwt;

    }
}
