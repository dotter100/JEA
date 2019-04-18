package Stocks.Controlleres.Stocks;




import Stocks.JWT.JWT;
import Stocks.Logic.Validator;
import Stocks.Models.Roles;
import Stocks.Models.User;
import Stocks.Services.UserService;

import javax.inject.Inject;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("StockUser")
//@RolesAllowed("USER")
public class UserController {
   @Inject
   public Validator validator;

    @Inject
    private UserService userService;
    @Path("")
    @GET
    @JWT(Permissions = Roles.DEFAULT)
    @Produces("application/json")
    public List<User> getUsers(){
        return userService.GetUsers();
    }

    @Path("")
    @POST
    @Produces("application/json")
    public User CreateUser(){
        User test = new User("test", "test");
        userService.createUser(test);
        return test;
    }

}