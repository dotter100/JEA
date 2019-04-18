package Stocks.Controlleres.Stocks;


import Stocks.Logic.Validator;
import Stocks.Services.StockService;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@ServerEndpoint("/Socket/{client-id}")
public class StocksWebsocket {
   @Inject
   public Validator validator;

    @Inject
    private StockService stockService;

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet());

    @OnMessage
    public String onMessage(String message, Session session) {
        try {
            System.out.println("received message from client ");
            for (Session s : peers) {
                try {
                    s.getBasicRemote().sendText(message);
                    System.out.println("send message to peer ");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "message was received by socket mediator and processed: " + message;
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("client-id") String clientId) {
        System.out.println("mediator: opened websocket channel for client ");
        peers.add(session);

        try {
            session.getBasicRemote().sendText("good to be in touch");
        } catch (IOException e) {
        }
    }


    @OnClose
    public void onClose(Session session) {
        System.out.println("mediator: closed websocket channel for client " );
        peers.remove(session);
    }

}