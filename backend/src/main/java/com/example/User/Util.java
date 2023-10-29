package com.example.User;

public class Util {

    /**
     * Returns distance between the hospitals in feet
     *
     * @param one The first hospital
     * @param two The second hospital
     * @return The distance between the hospitals in feet
     */
    public static double getDistance(Hospital one, Hospital two) {
        double latDif = (one.getLatitude() - two.getLatitude()) * 364000;
        double longDif = (one.getLongitude() - two.getLongitude()) * 288200;

        return Math.sqrt((latDif * latDif) + (longDif * longDif));
    }

    public static Hospital closestValidHospital(Hospital[] list, Hospital hospital, int idx, int amount) {
        Hospital closestValid = null;
        double minDist = Double.MAX_VALUE;
        for (Hospital value : list) {
            if (value.getSurplus()[idx] >= amount) {
                //The hospital has a surplus of the designated organ!
                double dist = getDistance(hospital, value);
                if (dist < minDist) {
                    minDist = dist;
                    closestValid = value;
                }
            }
        }

        if (minDist == Double.MAX_VALUE) {
            //No hospital has spares! YIKES
            return null;
        }

        return closestValid;
    }
}