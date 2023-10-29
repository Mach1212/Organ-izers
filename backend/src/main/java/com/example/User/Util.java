package com.example.User;

import java.util.List;

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

    public static Hospital closestValidHospital(List<Hospital> list, Hospital hospital, String idx, int amount) {
        Hospital closestValid = null;
        double minDist = Double.MAX_VALUE;
        for (Hospital value : list) {
            if (value.getSurplus().get(idx) >= amount) {
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

    public static int[] createPayload(int idx, int amt) {
        int[] payload = new int[4];
        for (int i = 0; i < idx; i++)
            payload[i] = 0;
        payload[idx] = amt;
        for (int i = idx + 1; i < 4; i++)
            payload[i] = 0;

        return payload;
    }
}