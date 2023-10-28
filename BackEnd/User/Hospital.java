/**
 * Stores information about a Hospital and some methods to interact with it
 */
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
    private int[4]inventory;
    /** Each index represents the same organs, values represent how many the hospital should have */
    private int[4]requiredInventory;


    public Hospital() {
        name = "";
        latitude = 0;
        longitude = 0;

        for (int i = 0; i < 4; i++) {
            inventory[i] = 0;
            requiredInventory[i] = 0;
        }
    }

    /**
     * Returns the amount of organs needed after an amount are used
     * @param idx The index, representing the organ type
     * @param amount The amount of organs used
     * @return The amount of organs neccessary to meet standards
     */

    int use(int idx, int amount) {
        if (inventory[idx] == 0) {
            throw IllegalArgumentException("No organ to use here");
        }
        inventory[idx] -= amount;
        if (inventory[idx] < requiredInventory[idx]) {
            return inventory[idx] - requiredInventory[idx];
        }
        return 0;
    }

}
//+ int getNeeded(int idx)
//    Compares amounts in both inventories, returns difference
//}