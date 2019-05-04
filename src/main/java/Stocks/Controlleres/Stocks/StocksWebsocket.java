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
@ServerEndpoint("/Socket") //ws://localhost:8080/JEAORM/Socket/1
public class StocksWebsocket {

    @Inject
    private StockService stockService;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet());
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();



    public StocksWebsocket() {

        //sendstocks(10);
    }

    @OnMessage
    public String onMessage(String message, Session session) {

        try {
            session.getBasicRemote().sendObject(GSON.toJson(getstocks(message)));
        } catch (IOException e) {
        } catch (EncodeException e) {
            e.printStackTrace();
        }

        return "message was received by socket mediator and processed: " + message ;
    }


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("mediator: opened websocket channel for client ");

        peers.add(session);

        sendstocks(10,session);

    }

    private void sendstocks(int time,Session s){


        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        try {
                            s.getBasicRemote().sendObject(GSON.toJson(getstockprice()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (EncodeException e) {
                            e.printStackTrace();
                        }
                        //sendmessage();

                    }
                },
                0,
                1000 * time
        );
    }

    private void sendmessage(){
        for(Session s : peers){
            try {
                s.getBasicRemote().sendObject(GSON.toJson(getstockprice()));
            } catch (IOException e) {
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }


    private List<Stocks> getstockprice(){
        Random rand = new Random();
        List<Stocks> stocksList = stockService.GetStocks();
        for (Stocks s: stocksList){
            int n = rand.nextInt(50);
            s.setPrice(s.getPrice()  + n);
        }
        return stocksList;
    }


    private List<Stocks> getstocks(String name){
        Random rand = new Random();

        List<Stocks> stocksList = stockService.GetStocks();
        List<Stocks> stockreturn = new ArrayList<>();
        for (Stocks s: stocksList){
            if(s.getName().contains(name)) {
                int n = rand.nextInt(50);
                s.setPrice(s.getPrice() + n);
                stockreturn.add(s);
            }
        }
        return stockreturn;
    }


    @OnClose
    public void onClose(Session session) {
        System.out.println("mediator: closed websocket channel for client " );
        peers.remove(session);
    }

}