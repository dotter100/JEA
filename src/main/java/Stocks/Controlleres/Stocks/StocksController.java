package Stocks.Controlleres.Stocks;




import Stocks.JWT.JWT;
import Stocks.Logic.Validator;
import Stocks.Models.Company;
import Stocks.Models.Stocks;
import Stocks.Models.User;
import Stocks.Services.StockService;
import Stocks.Services.UserService;

import javax.inject.Inject;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("Stocks")
//@RolesAllowed("USER")

public class StocksController {
   @Inject
   public Validator validator;

    @Inject
    private StockService stockService;

    @GET
    @Produces("application/json")
    public Response GetStocks(@Context UriInfo uriInfo){

        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();

        Link name = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()
                .path("name"))
                .rel("StockName").build();

        return Response.ok(stockService.GetStocks()).links(self,name).build();

    }

    @Path("{name}")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response Getstockname(@PathParam("name") String Stockname, @Context UriInfo uriInfo){
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();
        return Response.ok(stockService.GetStock(Stockname)).links(self).build();
    }

}