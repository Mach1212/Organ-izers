package User;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;

import java.util.List;

public class Main {
    public static void main( String[] args )
    {
        SurrealWebSocketConnection conn = new SurrealWebSocketConnection("localhost", 8082, false);
        conn.connect(5);
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn("hacknc", "a_totally_secure_password_is_really_long");
        driver.use("namespace-name", "database-name");
        String tableName = "hospital";
        driver.delete(tableName);
        Hospital unc = new Hospital();
        Hospital duke = new Hospital(tableName, 5, 6, new int[]{4, 4, 4, 4}, new int[]{3, 3, 3, 3,});
        Hospital UNC = driver.create(tableName, unc);
        Hospital UNCKids = driver.create(tableName, duke);
        List<Hospital> allHospitals = driver.select(tableName, Hospital.class);
        System.out.printf("All users = %s", allHospitals);
        conn.disconnect();
    }
}
