package com.example.User;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



//public class WebsocketEndpoint() {
//    private final  Gson gson = new Gson();
//    private static final String SERVER_URI = "ws://your-server-host/your-webapp-context-path/websocket";
//
//    public WebsocketEndpoint() {
//
//        try {
//        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//        Session session = container.connectToServer(YourClientEndpoint.class, new URI("localhost:8082"));
//
//        // Create and send a JSON-converted Java object
//        Message message = new Message();
//        String jsonInString = gson.toJson(hospital);
//        session.getBasicRemote().sendText(jsonInString);
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
//    }
//}

//public class WebsocketEndpoint extends Endpoint {
//    public void connect() throws URISyntaxException, DeploymentException, IOException {
//        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//        ClientEndpointConfig config = ClientEndpointConfig.Builder.create()
//                .configurator(new ClientEndpointConfig.Configurator()).build();
//
//        container.connectToServer(this, config, new URI("localhost:8082"));
//    }
//
//    public void drone(Drone drone) {
//        System.out.println("I'm a drone I guess");
//        boolean flag = true;
//        while (flag) {
//            flag = !drone.droneMove();
//            System.out.println("Hes a chugging");
//        }
//    }
//
//    public void hospital(WebSocket websocket, Hospital hospital){
//        System.out.println("I am a hospital");
//        Message message = new Message();
//        Gson gson = new Gson();
//        String jsonInString = gson.toJson(hospital);
//        websocket.send(jsonInString);
//    }
//
//
//    public void onOpen(Session session, EndpointConfig config) {
//        System.out.println("WebSocket session opened");
//    }
//
//    @Override
//    public void onClose(Session session, CloseReason closeReason) {
//        System.out.println("WebSocket session closed");
//    }
//
//    @Override
//    public void onError(Session session, Throwable t) {
//        System.err.println("WebSocket error: " + t.getMessage());
//    }
//
//}