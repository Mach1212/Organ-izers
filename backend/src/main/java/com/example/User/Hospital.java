package com.example.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hospital {
    String id;
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
    private Map<String, Integer> inventory;
    /**
     * Each index represents the same organs, values represent how many the hospital should have
     */
    private Map<String, Integer> requiredInventory;


    public Hospital() {
        name = "";
        latitude = 0;
        longitude = 0;


    }

    public Hospital(String name, double latitude, double longitude, Map<String, Integer> inventory, Map<String, Integer> requiredInventory) {
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
     * @param key    The index, representing the organ type
     * @param amount The amount of organs used
     * @return The amount of organs necessary to meet standards
     */

    public int use(String key, int amount) {
        if (inventory.get(key) - amount < 0) {
            throw new IllegalArgumentException("Not enough organs to use here");
        }

        inventory.replace(key, inventory.get(key) - amount);
        if (inventory.get(key) < requiredInventory.get(key)) {
            return requiredInventory.get(key) - inventory.get(key);
        }
        return 0;
    }

    public void addStock(Map<String, Integer> payload) {
        Iterable<Map.Entry<String, Integer>> it = payload.entrySet();
        for (Map.Entry<String, Integer> entry : it) {
            inventory.put(entry.getKey(), entry.getValue() + inventory.get(entry.getKey()));
        }
    }

    public Map<String, Integer> getSurplus() {

        Map<String, Integer> map = new HashMap<>();
        for (String entry : inventory.keySet()){
            map.put(entry, inventory.get(entry) - requiredInventory.get(entry));
        }

        return map;
    }

    @Override
    public String toString() {
        return name + " Lat: " + latitude + " Long: " + longitude + " inv: " + inventory.toString() + " Req. inv: " + requiredInventory.toString();
    }
    public Map<String, Integer> getInventory() {
        return this.inventory;
    }
}