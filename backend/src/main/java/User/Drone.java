package User;

//@@ -1,32 +1,130 @@

public class Drone {
    String name;
    /**
     * Current location data
     */
    double latitude;
    double longitude;
    /**
     * Stored as degrees
     */
    double direction;
    /**
     * Destination location data
     */
    double destLatitude;
    double destLongitude;

    /**
     * Drone will fly at 80 mph, 117.33 feet per second: Calculations are in feet per second
     */
    static double speed = 117.33;
    /**
     * The displacement of the drone's longitude in degrees/second
     */
    double longDisplacement;
    /**
     * The displacement of the drone's latitude in degrees/second
     */
    double latDisplacement;
    /**
     * An array where the index's represent the organs and the values represent the number of each
     */
    int[] payload;

    /**
     * Generic Constructor
     */
    public Drone() {
        name = "";
        latitude = 0;
        longitude = 0;
        payload = new int[]{0, 0, 0, 0};
        latDisplacement = 0;
        longDisplacement = 0;
    }

    /**
     * Emulates a drone that is now flying towards a hospital
     *
     * @param name        Drone's name
     * @param base        The drone's home hospital
     * @param destination The drone's destination hospital
     * @param payload     The list of payloads
     */

    public Drone(String name, Hospital base, Hospital destination, int[] payload) {
        this.name = name;
        this.latitude = base.getLatitude();
        this.longitude = base.getLongitude();
        this.direction = direction;
        this.destLatitude = destination.getLatitude();
        this.destLongitude = destination.getLongitude();
        this.payload = payload;

        //Calculate direction
        double direction = Math.atan((destLatitude - latitude) / (destLongitude - longitude));

        //     degrees/sec   = ft/s  *       proportion    /  ft/deg  : deg/sec  correct!
        this.latDisplacement = speed * Math.sin(direction) / 364000;
        this.longDisplacement = speed * Math.cos(direction) / 288200;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Moves/updates the drone's location
     *
     * @return if the drone has reached its destination
     */
    public boolean droneMove() {
        // One degree of latitude is == 364,000 feet
        // One degree of longitude is == 288,200 feet
        // Speed is 117.33 feet per second in XX direction
        // Updates will occur every second: therefore we move a maximum of
        // 0.0003223362 degrees latitude or 0.000407 degrees of longitude

        //Drone isn't going anywhere!
        if (latDisplacement == 0 && longDisplacement == 0)
            return true;

        latitude += latDisplacement;
        longitude += longDisplacement;

        //Will represent if the Drone has "passed" the destination this update
        boolean landedFlag = false;
        //Divide into 4 directional quadrants
        if (latDisplacement < 0) {
            if (longDisplacement < 0) {
                //Moving bottom left
                if (latitude <= destLatitude && longitude <= destLongitude) {
                    //Overshot the obj, drone would've made it
                    landedFlag = true;
                }
            } else {
                //Moving bottom right
                if (latitude <= destLatitude && longitude >= destLongitude) {
                    //Overshot the obj, drone would've made it
                    landedFlag = true;
                }
            }
        } else {
            if (longDisplacement < 0) {
                //Moving top left
                if (latitude >= destLatitude && longitude <= destLongitude) {
                    //Overshot the obj, drone would've made it
                    landedFlag = true;
                }
            } else {
                //Moving top right
                if (latitude >= destLatitude && longitude >= destLongitude) {
                    //Overshot the obj, drone would've made it
                    landedFlag = true;
                }
            }
        }

        if (landedFlag) {
            latitude = destLatitude;
            longitude = destLongitude;
            return true;
        }

        //We haven't overshot/landed at the location yet
        return false;
    }
}