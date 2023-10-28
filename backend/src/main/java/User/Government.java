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


    }


}