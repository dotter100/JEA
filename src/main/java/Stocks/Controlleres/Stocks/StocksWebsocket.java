package Stocks.Controlleres.Stocks;


import Stocks.Logic.Validator;
import Stocks.Models.Stocks;
import Stocks.Services.StockService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minidev.json.JSONObject;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.*;

@ApplicationScoped
@ServerEndpoint("/Socket/{client-id}") //ws://localhost:8080/JEAORM/Socket/1
public class StocksWebsocket {

    @Inject
    private StockService stockService;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet());
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @OnMessage
    public String onMessage(String message, Session session) {

        try {
            session.getBasicRemote().sendObject(GSON.toJson(getstockprice()));
        } catch (IOException e) {
        } catch (EncodeException e) {
            e.printStackTrace();
        }

        return "message was received by socket mediator and processed: " + message;
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("client-id") String clientId) {
        System.out.println("mediator: opened websocket channel for client ");

        peers.add(session);

        try {
            session.getBasicRemote().sendObject(GSON.toJson(getstockprice()));
        } catch (IOException e) {
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }


    private List<Stocks> getstockprice(){
        Random rand = new Random();
        List<Stocks> stocks = stockService.GetStocks();

        for (Stocks s: stocks){
            int n = rand.nextInt(50);
            s.setPrice(s.getPrice()  + n);
        }
        return stocks;
    }


    @OnClose
    public void onClose(Session session) {
        System.out.println("mediator: closed websocket channel for client " );
        peers.remove(session);
    }

}