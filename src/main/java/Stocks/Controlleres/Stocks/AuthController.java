package Stocks.Controlleres.Stocks;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password, @FormParam("verificationCode") int verificationCode) {


        try {
            User u = authenticate(username, password);
            // Issue a token for the user
            String token = issueToken(u);

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

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response RegisterUser(@FormParam("Name") String name,
                                 @FormParam("password") String password, @FormParam("twofactor") String twofactor) {

        User u = new User(name,password,Roles.USER);

        u.setTwofactor(twofactor);
        try {
//            String username = credentials.getUsername();
//            String password = credentials.getPassword();

            // Authenticate the user using the credentials provided
            userService.createUser(u);
            User user = authenticate(u.getName(), password);

            String token = issueToken(user);
            // Return the token on the response


            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    private User authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        User user = userService.GetUser(username,password);
        if(user != null){
            return user;
        }
        else {
            throw new Exception();
        }
    }

    private String issueToken(User user) throws Exception {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("Bart")
                    .withClaim("User",user.getName())
                    .withClaim("ID",user.getID())
                    .withClaim("Roles" , String.valueOf(user.getRole()))
                    .sign(algorithm);
            return token;


        }catch (Exception e){
            throw new Exception();

        }

    }
}