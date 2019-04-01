package Stocks.Controlleres.Stocks;




import Stocks.JWT.JWT;
import Stocks.Logic.Validator;
import Stocks.Models.Stocks;
import Stocks.Models.User;
import Stocks.Services.StockService;
import Stocks.Services.UserService;

import javax.inject.Inject;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("Stocks")
//@RolesAllowed("USER")
@ServletSecurity
public class StocksController {
   @Inject
   public Validator validator;

    @Inject
    private StockService stockService;

    @GET
    @Produces("application/json")
    public List<Stocks> GetStocks(){
        return stockService.GetStocks();
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public List<Stocks> Getstockname(String Stockname){
        return stockService.GetStock(Stockname);
    }

}