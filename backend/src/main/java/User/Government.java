package User;

import java.util.Arrays;

/**
 * This is to watch over the place, send drones and such
 * Also the test class?
 * IDK probably replacable
 */
public class Government {

    public static void main(String[] args) {
        Hospital h1 = new Hospital("UNC", 50.0, 60.0, new int[]{2, 3, 2, 2}, new int[]{2, 2, 2, 2});
        Hospital h2 = new Hospital("Duke", 100.0, 100.0, new int[]{3, 3, 3, 3}, new int[]{2, 2, 2, 2});
        Hospital h3 = new Hospital("Wake", -50.0, -40.0, new int[]{4, 4, 4, 4}, new int[]{1, 1, 1, 1});

        System.out.println("Distance: (64.03) " + Util.getDistance(h1, h2));
        System.out.println("Distance: (141.42) " + Util.getDistance(h1, h3));
        System.out.println();

        System.out.println("UNC Surplus: (0.1.0.0) " + Arrays.toString(h1.getSurplus()));
        System.out.println("Duke Surplus: (1.1.1.1) " + Arrays.toString(h2.getSurplus()));
        System.out.println("Wake Surplus: (3.3.3.3) " + Arrays.toString(h3.getSurplus()));
        System.out.println();

        System.out.println("UNC using 2, 1's: (1) " + h1.use(1, 2));

        h1 = new Hospital("UNC", 50.0, 60.0, new int[]{2, 3, 2, 2}, new int[]{2, 2, 2, 2});
        Hospital[] list = {h1, h2, h3};
        System.out.println("Closest To UNC Using 2, 1's: (Duke) " + Util.closestValidHospital(list, h1, 1, h1.use(1, 2)));
        System.out.println("UNC Surplus: (0.-1.0.0: Needs drone) " + Arrays.toString(h1.getSurplus()));
        System.out.println();

        h1 = new Hospital("UNC", 50.0, 60.0, new int[]{2, 3, 2, 2}, new int[]{2, 2, 2, 2});
        System.out.println("Closest To UNC Using 3, 1's: (None have enough) " + Util.closestValidHospital(list, h1, 1, h1.use(1, 3)));


        h1 = new Hospital("UNC", 50.0, 60.0, new int[]{2, 3, 2, 2}, new int[]{2, 2, 2, 2});
        h2 = new Hospital("Duke", 59.0, 61.3, new int[]{3, 3, 3, 3}, new int[]{2, 2, 2, 2});
        h3 = new Hospital("Wake", 49.2, 58.2, new int[]{4, 4, 4, 4}, new int[]{1, 1, 1, 1});
        Hospital[] database = {h1, h2, h3};
        System.out.println();
        //Demo:
        //If using makes us need an organ
        int idx = 1; //Liver
        int amt = 2;
        //Use operation
        int needed = database[0].use(idx, amt);
        //Check!
        if (needed != 0) {
            //Find nearest valid hospital
            Hospital donor = Util.closestValidHospital(database, h1, idx, amt);
            if (donor != null) {
                //Hospitals already been checked that this will not exceed stocks
                //MAKE SURE THIS IS A DATABASE SEARCH FOR THE HOSPITAL
                database[2].use(idx, amt);

                //Configure payload
                int[] payload = new int[4];
                for (int i = 0; i < idx; i++)
                    payload[i] = 0;
                payload[idx] = needed;
                for (int i = idx + 1; i < 4; i++)
                    payload[i] = 0;


                //Send drone!
                Drone drone = new Drone("1", h1, donor, payload);
                boolean flag = true;
                while (flag) {
                    flag = !drone.droneMove();
                }
                //Drone has made it
                //Restock hospital  MAKE SURE THIS IS A DATABASE SEARCH FOR THE HOSPITAL
                database[0].addStock(drone.getPayload());
            } else {
                System.out.println("Hospitals screwed, nobody has spares");
            }

            System.out.println(database[0]);
            System.out.println(database[1]);
            System.out.println(database[2]);
        }
    }


}