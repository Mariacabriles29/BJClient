package com.conection;

import java.io.IOException;
import java.net.URI;
import javax.websocket.*;
import com.gui.Screen;

@ClientEndpoint
public class WSClient{

    private static Object waitLock = new Object();
    private WebSocketContainer container;
    private Session session=null;
    private Screen screen;

    public static String serverURI = "ws://localhost:8080/examples/websocket/chat";;

    public WSClient(Screen screen){
        this.screen = screen;
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received msg: "+message);
    }

    private static void wait4TerminateSignal()
    {
        synchronized(waitLock)
        {
            try {
            System.out.println("inicio");
            waitLock.wait();
            System.out.println("termino");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startClient(){
        container=null;
        session=null;
        try{
            container = ContainerProvider.getWebSocketContainer();
            session=container.connectToServer(WSClient.class, URI.create(serverURI));
            wait4TerminateSignal();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(session!=null){
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String [] args){
        WebSocketContainer container=null;
        Session session=null;
        try{
            container = ContainerProvider.getWebSocketContainer();
            session=container.connectToServer(WSClient.class, URI.create(serverURI));
            session.getBasicRemote().sendText("Hola");
            wait4TerminateSignal();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}