package com.example.User;

import com.google.gson.Gson;
import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException, DeploymentException, URISyntaxException, IOException {

        SurrealWebSocketConnection conn = new SurrealWebSocketConnection("db", 8082, false);
        conn.connect(5);
        String tableName = "Hospital";
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn("hacknc", "a_totally_secure_password_is_really_long");
        driver.use("namespace-name", "database-name");

        Map<String, Integer> map = new HashMap<>();
        map.put("Heart", 0);
        map.put("Liver", 0);
        map.put("Kidney", 0);
        map.put("Blood", 0);

        Hospital unc = driver.create(tableName, new Hospital("UNC Hospitals", 35.903953, -79.050705,
                map, map));
        Hospital duke = driver.create(tableName, new Hospital("Duke University Hospital", 36.007359, -78.937439,
                map, map));
        Hospital uncSurg = driver.create(tableName, new Hospital("UNC Surgery Clinic", 35.90420, -79.011630,
                map, map));
        Hospital central = driver.create(tableName, new Hospital("Central Regional Hospital", 36.145481, -78.771851,
                map, map));
        Hospital uncHills = driver.create(tableName, new Hospital("UNC Hospitals Hillsborough Campus", 36.039909, -79.092922,
                map, map));
        Hospital durham = driver.create(tableName, new Hospital("Durham VA Healthcare and Emergency Room", 36.009312, -78.938248,
                map, map));
        List<Hospital> allHospitals = driver.select(tableName, Hospital.class);
        //driver.select(unc.id, Hospital.class).get(0).use(1, 2) Gets individual hospital

        Gson gson = new Gson();
        String message = gson.toJson(new Message());
        conn.send(message);

        Thread thread = new Thread(() -> new receiveThread(conn));

        thread.start();





        conn.disconnect();
    }

}