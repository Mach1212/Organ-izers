package User;

public class Util {

    public static double getDistance(Hospital one, Hospital two) {
        double latDif = one.getLatitude() - two.getLatitude();
        double longDif = one.getLongitude() - two.getLongitude();

        return Math.sqrt((latDif * latDif) + (longDif * longDif));
    }

    public static Hospital closestValidHospital(Hospital[] list, Hospital hospital, int idx, int amount) {
        Hospital closestValid = null;
        double minDist = Integer.MAX_VALUE;
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

        if (minDist == Integer.MAX_VALUE) {
            //No hospital has spares! YIKES
            return null;
        }

        return closestValid;
    }
}