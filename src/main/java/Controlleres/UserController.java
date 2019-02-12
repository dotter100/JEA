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

@Path("/users")
public class UserController {
    @Inject
    private UserService  userService;

    @Path("/all")
    @GET
    @Produces("application/json")
    public JsonArray getUsers(){
        JsonArrayBuilder builder = Json.createArrayBuilder();

        for(User u : userService.GetUsers()){
            builder.add(Json.createObjectBuilder().add("name", u.getName()));
        }
        return builder.build();
    }

}
