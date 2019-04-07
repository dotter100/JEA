package Stocks.Controlleres.Stocks;




import Stocks.JWT.Authenticated.AuthenticatedUser;
import Stocks.JWT.JWT;
import Stocks.Logic.Validator;
import Stocks.Models.Portfolio;
import Stocks.Models.Stocks;
import Stocks.Models.User;
import Stocks.Services.PortfolioService;
import Stocks.Services.StockService;

import javax.inject.Inject;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.*;
import java.util.List;

@Path("Portfolio")

@JWT
public class PortfolioController {
   @Inject
   public Validator validator;

    @Inject
    private PortfolioService portfolioService;

    @Inject
    @AuthenticatedUser
    User user;

    @GET
    @Produces("application/json")
    public List<Portfolio> GetPortfolios(){
        return user.getPortfolios();

    }

    @Path("name")
    @POST
    @Produces("application/json")
    public List<Portfolio> GetPortfolioName(String portfolioname){
        return portfolioService.GetPortfolio(portfolioname);
    }
    @Path("ID")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public List<Portfolio> GetPortfolioID(int id){
        return portfolioService.GetPortfolio(id);
    }
    @Path("Update")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public boolean UpdatePortfolio(Portfolio portfolio){
        return portfolioService.UpdatePortfolio(portfolio);
    }

}