package User;
public class Hospital {
    private String name;
    private double latitude;
    private double longitude;
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

    /**
     * Returns the amount of organs needed after an amount are used
     *
     * @param idx    The index, representing the organ type
     * @param amount The amount of organs used
     * @return The amount of organs neccessary to meet standards
     */

    int use(int idx, int amount) {
        if (inventory[idx] == 0) {
            throw new IllegalArgumentException("No organ to use here");
        }
        inventory[idx] -= amount;
        if (inventory[idx] < requiredInventory[idx]) {
            return inventory[idx] - requiredInventory[idx];
        }
        return 0;
    }
}
