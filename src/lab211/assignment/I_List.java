package lab211.assignment;

import java.util.ArrayList;

/* Interface for a group of objects
 */
public interface I_List {
    // your code here

    void addVehicle(I_Menu menu);
//    private String id;
//    private String name;
//    private String color;
//    private double price;
//    private String brand;

    boolean addCar(String id, String name, String color, double price, String brand);

    boolean addMotorbike(String id, String name, String color, double price, String brand);

    Vehicle searchVehicleByID(String id);
    void updateVehicle();
    void deleteVehicle();
    void searchVehhicle(I_Menu menu);
    ArrayList<Vehicle> searchByName(String name);
    void showVehicle(I_Menu menu);
    void storeData();
    
}
