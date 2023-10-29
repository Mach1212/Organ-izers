package com.example.User;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebsocketEndpoint extends Endpoint {
    public void connect() throws URISyntaxException, DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create()
                .configurator(new ClientEndpointConfig.Configurator()).build();

        container.connectToServer(this, config, new URI("localhost:8082"));
    }

    public void drone(Drone drone) {
        System.out.println("I'm a drone I guess");
        boolean flag = true;
        while (flag)
            flag = !drone.droneMove();
    }

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