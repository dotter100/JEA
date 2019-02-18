package Controlleres;

import Models.User;
import services.UserService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("users")
public class UserController {
    @Inject
    private UserService  userService;

    @GET
    @Produces("application/json")
    public List<User> getUsers(){
        JsonArrayBuilder builder = Json.createArrayBuilder();

        for(User u : userService.GetUsers()){
            builder.add(Json.createObjectBuilder().add("name", u.getName()));
        }
        return userService.GetUsers();
    }

}
