package com.example.User;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import java.io.IOException;

public class WebsocketEndpoint extends Endpoint {
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        System.out.println("WebSocket session opened");
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("WebSocket session closed");
    }

    @Override
    public void onError(Session session, Throwable t) {
        System.err.println("WebSocket error: " + t.getMessage());
    }

}