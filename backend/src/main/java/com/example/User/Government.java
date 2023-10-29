package com.example.User;

import java.util.*;

/**
 * This is to watch over the place, send drones and such
 * Also the test class?
 * IDK probably replacable
 */
public class Government {

    public static void main(String[] args) {
        Map<String, Integer> req = new HashMap<String, Integer>();
        req.put("Heart", 2);
        req.put("Liver", 2);
        req.put("Blood", 2);
        Map<String, Integer> unc = new HashMap<String, Integer>();
        unc.put("Heart", 3);
        unc.put("Liver", 3);
        unc.put("Blood", 3);
        Map<String, Integer> duke = new HashMap<String, Integer>();
        duke.put("Heart", 3);
        duke.put("Liver", 3);
        duke.put("Blood", 3);
        Map<String, Integer> big = new HashMap<String, Integer>();
        big.put("Heart", 2);
        big.put("Liver", 6);
        big.put("Blood", 6);
        Hospital h1 = new Hospital("UNC", 78.0, 78.0, unc, req);
        Hospital h2 = new Hospital("Duke", 80.0, 80.0, duke, req);
        Hospital h3 = new Hospital("Wake", 76.0, 76.0, big, req);

        System.out.println("Distance: (928558.54 ft) " + Util.getDistance(h1, h2));
        System.out.println("Distance: (928558.54 ft) " + Util.getDistance(h1, h3));
        System.out.println();

        System.out.println("UNC Surplus: (1.1.1) " + h1.getSurplus().toString());
        System.out.println("Duke Surplus: (1.1.1) " + h2.getSurplus().toString());
        System.out.println("Wake Surplus: (4.4.4) " + h3.getSurplus().toString());
        System.out.println();

        System.out.println("UNC using 2, Livers: (1) " + h1.use("Liver", 2));


        Map<String, Integer> restock = new HashMap<String, Integer>();
        restock.put("Liver", 2);
        h1.addStock(restock);
        restock.put("Heart", 3);

        List<Hospital> list = new ArrayList<Hospital>();
        list.add(h1);
        list.add(h2);
        System.out.println("Closest To UNC Using 2 Livers: (Duke) " + Util.closestValidHospital(list, h1, "Liver", h1.use("Liver", 2)));
        System.out.println("UNC Surplus: (1.-1.1: Needs drone) " + h1.getSurplus().toString());
        System.out.println();

        System.out.println("Closest To UNC Using 3 Hearts: (None have enough) " + Util.closestValidHospital(list, h1, "Heart", h1.use("Heart", 3)));

        h1.addStock(restock);
        Hospital h4 = new Hospital("UNC", 50.0, 60.0, unc, req);
        Hospital h5 = new Hospital("Duke", 59.0, 61.3, duke, req);
        Hospital h6 = new Hospital("Wake", 49.2, 58.2, big, req);
        List<Hospital> database = new ArrayList<Hospital>();
        database.add(h4);
        database.add(h5);
        database.add(h6);

        System.out.println();
        //Demo:
        //If using makes us need an organ
        String key = "Liver";
        int amt = 2;
        //Use operation
        int needed = database.get(0).use(key, amt);
        //Check!
        if (needed != 0) {
            //Find nearest valid hospital
            Hospital donor = Util.closestValidHospital(database, h1, key, amt);
            if (donor != null) {
                //Hospitals already been checked that this will not exceed stocks
                //MAKE SURE THIS IS A DATABASE SEARCH FOR THE HOSPITAL
                donor.use(key, amt);

                //Configure payload
                Map<String, Integer> payload = Util.createPayload(key, amt);


                //Send drone!
                Drone drone = new Drone("1", h1, donor, payload);
                boolean flag = true;
                while (flag) {
                    flag = !drone.droneMove();
                }
                //Drone has made it
                //Restock hospital  MAKE SURE THIS IS A DATABASE SEARCH FOR THE HOSPITAL
                database.get(0).addStock(drone.getPayload());
            } else {
                System.out.println("Hospitals screwed, nobody has spares");
            }

            System.out.println(database.get(0));
            System.out.println(database.get(1));
            System.out.println(database.get(2));
        }
    }


}