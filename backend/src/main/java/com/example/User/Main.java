package com.example.User;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, DeploymentException, URISyntaxException, IOException {
        //Thread.sleep(2000);
        System.out.println("jsi");
        SurrealWebSocketConnection conn = new SurrealWebSocketConnection("db", 8082, false);
        conn.connect(5);
        String tableName = "Hospital";
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn("hacknc", "a_totally_secure_password_is_really_long");
        driver.use("namespace-name", "database-name");

        Hospital unc = driver.create(tableName, new Hospital("UNC Hospitals", 35.903953, -79.050705,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}));
        Hospital duke = driver.create(tableName, new Hospital("Duke University Hospital", 36.007359, -78.937439,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}));
        Hospital uncSurg = driver.create(tableName, new Hospital("UNC Surgery Clinic", 35.90420, -79.011630,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}));
        Hospital central = driver.create(tableName, new Hospital("Central Regional Hospital", 36.145481, -78.771851,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}));
        Hospital uncHills = driver.create(tableName, new Hospital("UNC Hospitals Hillsborough Campus", 36.039909, -79.092922,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}));
        Hospital durham = driver.create(tableName, new Hospital("Durham VA Healthcare and Emergency Room", 36.009312, -78.938248,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}));
        //List<Hospital> allHospitals = driver.s, elect(tableName, Hospital.class); List of all idk what it would be useful for.
        //System.out.println(driver.select(unc.id, Hospital.class).get(0).use(1, 2)); Gets individual hospital
//        surreal.live("sticky", (StickyAction action, StickyResult result) -> {
//            switch (action) {
//                case CREATE:
//                case UPDATE:
//                    store.mergeStickies(Collections.singletonList(Sticky.parse(result)));
//                    break;
//                case DELETE:
//                    store.deleteSticky(result.getId());
//                    break;
//            }
//        });
        int idx = 1; //Liver
        int amt = 2;
        Hospital h1 = driver.select(unc.id, Hospital.class).get(0);
        int needed = h1.use(idx, amt);
        //Configure payload
        int[] payload = Util.createPayload(idx, amt);
        List<Hospital> hey = driver.select(tableName, Hospital.class);
        Hospital donor = Util.closestValidHospital(driver.select(tableName, Hospital.class), h1, idx, amt);
        //Send drone!
        assert donor != null;
        Drone drone = new Drone("1", h1, donor, payload);

        WebsocketEndpoint socket = new WebsocketEndpoint();
        socket.connect();

        socket.drone(drone);


        conn.disconnect();
    }
}