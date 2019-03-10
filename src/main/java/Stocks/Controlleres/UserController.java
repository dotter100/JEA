package Stocks.Controlleres;




import Stocks.Logic.Validator;
import Stocks.Models.User;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("StockUser")
public class UserController {
   @Inject
   public Validator validator;

    @GET
    @Produces("application/json")
    public List<User> getUsers(){
        List<User> list = new ArrayList<User>();
        User test = new User("test", "");
        list.add(test);
        validator.validator(test);


        return list;
    }

}