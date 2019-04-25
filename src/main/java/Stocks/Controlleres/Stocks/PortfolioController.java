package Stocks.Controlleres.Stocks;




import Stocks.JWT.Authenticated.AuthenticatedUser;
import Stocks.JWT.JWT;
import Stocks.Logic.Validator;
import Stocks.Models.Portfolio;
import Stocks.Models.Roles;
import Stocks.Models.Stocks;
import Stocks.Models.User;
import Stocks.Services.PortfolioService;
import Stocks.Services.StockService;

import javax.inject.Inject;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.*;
import java.util.List;

@Path("Portfolio")

@JWT(Permissions = Roles.DEFAULT)
public class PortfolioController {
   @Inject
   public Validator validator;

    @Inject
    private PortfolioService portfolioService;

    @Inject
    @AuthenticatedUser
    User user;

    @JWT
    @GET
    @Produces("application/json")
    public List<Portfolio> GetPortfolios(){
     List<Portfolio> list = user.getPortfolios();
        return list;
    }
    @JWT
    @Path("name/{id}")
    @POST
    @Produces("application/json")
    public List<Portfolio> GetPortfolioName(@PathParam("id") String portfolioname){

        return portfolioService.GetPortfolio(portfolioname);
    }
    @JWT
    @Path("/{id}")
    @POST
    //@Consumes("application/json")
    @Produces("application/json")
    public List<Portfolio> GetPortfolioID(@PathParam("id") int id){
        return portfolioService.GetPortfolio(id);
    }
    @JWT
    @Path("Update")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public boolean UpdatePortfolio(Portfolio portfolio){
        return portfolioService.UpdatePortfolio(portfolio);
    }

 @JWT(Permissions = Roles.DEFAULT)
 @Path("/add")
 @POST
 @Consumes("application/json")
 public boolean AddPortfolio(Portfolio portfolio){
     return portfolioService.AddPortfolio(portfolio,user.getID());
 }

}