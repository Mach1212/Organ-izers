package User;

import java.util.Arrays;

public class Hospital {
    private final String name;
    private final double latitude;
    private final double longitude;
    /**
     * Each index is a different organ
     * 0: Heart
     * 1: Liver
     * 2: Kidney
     * 3: "Units" of Blood
     */
    private int[] inventory;
    /**
     * Each index represents the same organs, values represent how many the hospital should have
     */
    private int[] requiredInventory;


    public Hospital() {
        name = "";
        latitude = 0;
        longitude = 0;
        inventory = new int[4];
        requiredInventory = new int[4];

    }

    public Hospital(String name, double latitude, double longitude, int[] inventory, int[] requiredInventory) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.inventory = inventory;
        this.requiredInventory = requiredInventory;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the amount of organs needed after an amount are used
     *
     * @param idx    The index, representing the organ type
     * @param amount The amount of organs used
     * @return The amount of organs necessary to meet standards
     */

    public int use(int idx, int amount) {
        if (inventory[idx] - amount < 0) {
            throw new IllegalArgumentException("Not enough organs to use here");
        }

        inventory[idx] -= amount;
        if (inventory[idx] < requiredInventory[idx]) {
            return requiredInventory[idx] - inventory[idx];
        }
        return 0;
    }

    public void addStock(int[] payload) {
        for (int i = 0; i < payload.length; i++) {
            inventory[i] += payload[i];
        }
    }

    public int[] getSurplus() {
        int[] list = {0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            list[i] = inventory[i] - requiredInventory[i];
        }

        return list;
    }

    @Override
    public String toString() {
        return name + " Lat: " + latitude + " Long: " + longitude + " Inv: " + Arrays.toString(inventory) +
                " Req. Inv: " + Arrays.toString(requiredInventory);
    }
}