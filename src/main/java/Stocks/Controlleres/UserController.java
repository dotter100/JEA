package Stocks.Controlleres;




import Stocks.Logic.Validator;
import Stocks.Models.User;
import Stocks.Services.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("StockUser")
@RolesAllowed("USER")
public class UserController {
   @Inject
   public Validator validator;

    @Inject
    private UserService userService;

    @GET
    @Produces("application/json")
    public List<User> getUsers(){
        List<User> list = new ArrayList<User>();
        User test = new User("test", "asd");
        list.add(test);
        list.addAll(userService.GetUsers());
        validator.validator(test);


        return list;
    }


    @POST
    @Produces("application/json")
    public User CreateUser(){

        User test = new User("test", "test");

        userService.createUser(test);
        return test;
    }

}