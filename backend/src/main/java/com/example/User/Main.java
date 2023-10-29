package com.example.User;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;

public class Main {
    public static void main( String[] args ) throws InterruptedException {
        //Thread.sleep(2000);
        System.out.println("jsi");
        SurrealWebSocketConnection conn = new SurrealWebSocketConnection("db", 8082, false);
        conn.connect(5);
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn("hacknc", "a_totally_secure_password_is_really_long");
        driver.use("namespace-name", "database-name");
        driver.create("Hospital", new Hospital("UNC Hospitals", 35.903953, -79.050705,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}));
        Hospital duke = new Hospital("Duke University Hospital", 36.007359, -78.937439,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4});
        Hospital uncMulti = new Hospital("UNC Surgery Clinic", 35.90420, -79.011630,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4});
        Hospital centralRegional = new Hospital("Central Regional Hospital", 36.145481, -78.771851,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4});
        Hospital uncHills = new Hospital("UNC Hospitals Hillsborough Campus", 36.039909, -79.092922,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4});
        Hospital durhamVA = new Hospital("Durham VA Healthcare and Emergency Room", 36.009312, -78.938248,
                new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4});
        //List<Hospital> allHospitals = driver.select(tableName, Hospital.class);
        System.out.println(driver.select("Hospital", Hospital.class));
        conn.disconnect();
    }
}