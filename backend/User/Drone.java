
public class Drone {
    String name;
    double latitude;
    double longitude;
    /** Drone will fly at 80 mph */
    const double speed = 80;
    /** Stored as degrees*/
    double direction;
    /** An array where the index's represent the organs and the values represent the number of each */
    int[4] payload;

    public Drone() {
        name = "";
        latitude = 0;
        longitude = 0;
        payload = {0, 0, 0, 0};
    }


    public Drone(String name, double latitude, double longitude) {
        this(name, latitude, longitude, null, {0, 0, 0, 0});
    }

    public Drone(String name, double latitude, double longitude, double direction, int[4] payload) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
        this.payload = payload;
    }
}