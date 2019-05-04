package Stocks.Controlleres.Stocks;

import Stocks.JWT.JWTLogic;
import Stocks.Models.Roles;
import Stocks.Models.User;
import Stocks.Services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;




import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;

@Path("/authentication")
public class AuthController {
    @Inject
    private UserService userService;

    //User login
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password, @FormParam("verificationCode") int verificationCode) {


        try {
            User u = authenticate(username, password);
            // Issue a token for the user
            String token = JWTLogic.issueToken(u);

            TimeBasedOneTimePasswordUtil t = new TimeBasedOneTimePasswordUtil();
            if (!t.validateCurrentNumber(u.getTwofactor(),verificationCode,3000)) {
                throw new Exception("Invalid verfication code");
            }
            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


    //Register a new user with 2FA
    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response RegisterUser(@FormParam("Name") String name,
                                 @FormParam("password") String password, @FormParam("twofactor") String twofactor) {

        User u = new User(name,password,Roles.USER);

        u.setTwofactor(twofactor);
        try {

            // Create user based on the given input
            userService.createUser(u);

            //user authenticate
            User user = authenticate(u.getName(), password);

            String token = JWTLogic.issueToken(user);
            // Return the token on the response


            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    // Authenticate against a database
    // Throw an Exception if the credentials are invalid or user object from data is null
    private User authenticate(String username, String password) throws Exception {

        User user = userService.GetUser(username,password);
        if(user != null){
            return user;
        }
        else {
            throw new Exception();
        }
    }

}