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
import java.util.Scanner;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws InterruptedException, DeploymentException, URISyntaxException, IOException {

        SurrealWebSocketConnection conn = new SurrealWebSocketConnection("db", 8082, false);
        conn.connect(10);
        String tableName = "Hospital";
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn("hacknc", "a_totally_secure_password_is_really_long");
        driver.use("namespace-name", "database-name");

        Map<String, Integer> req = new HashMap<>();
        req.put("Heart", 2);
        req.put("Liver", 2);
        req.put("Kidney", 2);
        req.put("Blood", 2);

        Map<String, Integer> uncMap = new HashMap<>();
        uncMap.put("Heart", 4);
        uncMap.put("Liver", 4);
        uncMap.put("Kidney", 3);
        uncMap.put("Blood", 4);

        Map<String, Integer> dukeMap = new HashMap<>();
        dukeMap.put("Heart", 4);
        dukeMap.put("Liver", 4);
        dukeMap.put("Kidney", 4);
        dukeMap.put("Blood", 4);

        Map<String, Integer> uncSurgMap = new HashMap<>();
        uncSurgMap.put("Heart", 3);
        uncSurgMap.put("Liver", 3);
        uncSurgMap.put("Kidney", 4);
        uncSurgMap.put("Blood", 3);

        Map<String, Integer> centralMap = new HashMap<>();
        centralMap.put("Heart", 2);
        centralMap.put("Liver", 3);
        centralMap.put("Kidney", 6);
        centralMap.put("Blood", 7);

        Map<String, Integer> uncHillsMap = new HashMap<>();
        uncHillsMap.put("Heart", 2);
        uncHillsMap.put("Liver", 2);
        uncHillsMap.put("Kidney", 2);
        uncHillsMap.put("Blood", 2);

        Map<String, Integer> durhamMap = new HashMap<>();
        durhamMap.put("Heart", 5);
        durhamMap.put("Liver", 5);
        durhamMap.put("Kidney", 7);
        durhamMap.put("Blood", 5);

        Hospital unc = driver.create(tableName, new Hospital("UNC Hospitals", 35.903953, -79.050705,
                uncMap, req));
        Hospital duke = driver.create(tableName, new Hospital("Duke University Hospital", 36.007359, -78.937439,
                dukeMap, req));
        Hospital uncSurg = driver.create(tableName, new Hospital("UNC Surgery Clinic", 35.90420, -79.011630,
                uncSurgMap, req));
        Hospital central = driver.create(tableName, new Hospital("Central Regional Hospital", 36.145481, -78.771851,
                centralMap, req));
        Hospital uncHills = driver.create(tableName, new Hospital("UNC Hospitals Hillsborough Campus", 36.039909, -79.092922,
                uncHillsMap, req));
        Hospital durham = driver.create(tableName, new Hospital("Durham VA Healthcare and Emergency Room", 36.009312, -78.938248,
                durhamMap, req));
        List<Hospital> allHospitals = driver.select(tableName, Hospital.class);
        //driver.select(unc.id, Hospital.class).get(0).use(1, 2) Gets individual hospital


        Socket socket = conn.getSocket();
        Scanner scanner = new Scanner(socket.getInputStream());
        String line;
        while((line = scanner.nextLine()) != null) {
            Gson gson = new Gson();
            String message = gson.toJson(new Message());
            conn.send(message);
        }

//        Thread thread = new Thread(() -> new receiveThread(conn));
//        thread.start();
//        conn.disconnect();
    }

}