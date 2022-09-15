package lab211.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class VehicleList extends ArrayList<Vehicle> implements I_List {

    //Read the file before doing other function
    {
        ReadFile readline = line -> {
            StringTokenizer st = new StringTokenizer(line, "|");
            List<String> token = new ArrayList<>();
            while (st.hasMoreElements()) {
                token.add(st.nextToken());
            }

            String objType = token.get(0);
            String id = token.get(1);
            String name = token.get(2);
            String color = token.get(3);
            double price = Double.parseDouble(token.get(4));
            String brand = token.get(5);
            if (objType.equals("C")) {
                String type = token.get(6);
                int yom = Integer.parseInt(token.get(7));
                Car c = new Car(id, name, color, price, brand, type, yom);
                this.add(c);
            } else if (objType.equals("M")) {
                double speed = Double.parseDouble(token.get(6));
                boolean lisence = Boolean.parseBoolean(token.get(7));
                Motorbike m = new Motorbike(id, name, color, price, brand, speed, lisence);
                this.add(m);
            }

        };
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream("src\\file\\vehicles.txt"), "UTF-8");
            BufferedReader buffReader = new BufferedReader(reader);
            String line;
            while ((line = buffReader.readLine()) != null) {
                readline.createObject(line);
            }
            buffReader.close();
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addVehicle(I_Menu menu) {
        String id;
        int choice;
        boolean status;
        boolean exit;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            id = Utils.getString("Enter vehicle id: ");
            if (searchVehicleByID(id) != null) {
                System.out.println("id's already existed!");
                status = false;
            } else {
                status = true;
            }
            if (status) {
                String name = Utils.getString("Enter name: ");
                String color = Utils.getString("Enter color: ");
                double price = Utils.getDouble("Enter price: ", 0, 9999);
                String brand = Utils.getString("Enter brand: ");

                switch (choice) {
                    case 1:
                        status = addCar(id, name, color, price, brand);
                        break;
                    case 2:
                        status = addMotorbike(id, name, color, price, brand);
                        break;
                }
            }

            if (status) {
                System.out.println("Add successfully!");
            }
            exit = Utils.confirmYesNo("Do you want to continue adding?(Y/N)"); // ask to continue adding
        } while (exit);
    }

    @Override
    public void updateVehicle() {
        boolean flag;
        String id;
        id = Utils.getString("Enter vehicle id to update: ");
        Vehicle vehicle = this.searchVehicleByID(id);
        if (vehicle == null) {
            System.out.println("Vehicle does not exist!");
            flag = false;
        } else {

            String name = Utils.updateString("Enter new name: ", vehicle.getName());
            vehicle.setName(name);
            String color = Utils.updateString("Enter new color: ", vehicle.getColor());
            vehicle.setColor(color);
            double price = Utils.updateDouble("Enter new price: ", 0, 9999, vehicle.getPrice());
            vehicle.setPrice(price);
            String brand = Utils.updateString("Enter new brand:", vehicle.getBrand());
            vehicle.setBrand(brand);
            if (vehicle instanceof Car) {
                String type = Utils.updateString("Enter new type: ", ((Car) vehicle).getType());
                ((Car) vehicle).setType(type);
                int year = Utils.updateInt("Enter new year of manufacture: ", 0, 2022, ((Car) vehicle).getYearOfManufacture());
                ((Car) vehicle).setYearOfManufacture(year);
                flag = true;
            } else {
                double speed = Utils.updateDouble("Enter new speed: ", 0, 9999, ((Motorbike) vehicle).getSpeed());
                ((Motorbike) vehicle).setSpeed(speed);
                boolean license = Utils.updateBoolean("License Requirement: (Y/N)", ((Motorbike) vehicle).isLicenseRequirement());
                ((Motorbike) vehicle).setLicenseRequirement(license);
                flag = true;
            }
        }
        if (flag) {
            System.out.print("After updating: ");
            vehicle.showInformation();
        }

    }

    @Override
    public void deleteVehicle() {
        boolean check;
        String id = Utils.getString("Enter id to delete: ");
        Vehicle vehicle = searchVehicleByID(id);
        if (vehicle == null) {
            System.out.println("Vehicle does not exist!");
            check = false;
        } else {
            check = Utils.confirmYesNo("Are you sure? (Y/N)");
        }
        if (check == true) {
            this.remove(vehicle);
            System.out.println("Delete successfully!");
        }
    }

    @Override
    public Vehicle searchVehicleByID(String id) {
        Vehicle result = null;
        for (Vehicle v : this) {
            if (v.getId().equals(id)) {
                result = v;
            }
        }
        return result;
    }

    @Override
    public ArrayList<Vehicle> searchByName(String name) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : this) {
            if (vehicle.getName().contains(name)) {
                result.add(vehicle);
            }

        }
        return result;
    }

    @Override
    public void searchVehhicle(I_Menu menu) {
        int choice;
        menu.showMenu();
        choice = menu.getChoice();
        if (choice == 1) {
            String name = Utils.getString("Enter the name text: ");
            ArrayList<Vehicle> searchByNameVehicle = searchByName(name);
            //sort-by-name
            Collections.sort(searchByNameVehicle, (a, b) -> {
                return -1 * (a.getName().compareTo(b.getName()));
            });
            for (Vehicle vehicle : searchByNameVehicle) {
                vehicle.showInformation();
            }
        } else {
            String id;
            id = Utils.getString("Enter id to search: ");
            Vehicle vehicleSearchByID = searchVehicleByID(id);
            if (vehicleSearchByID != null) {
                vehicleSearchByID.showInformation();
            } else {
                System.out.println("Not found!");
            }
        }

    }

    @Override
    public void showVehicle(I_Menu menu) {
        int choice;
        menu.showMenu();
        choice = menu.getChoice();

        System.out.printf("%-9s\t%-10s\t%-20s\t%-10s\t%-15s\t%-10s\t%-12s\t%-12s\n", "Car/Motor", "ID", "Name", "Color", "Price", "Brand",
                "Type/Speed", "YOM/License");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        switch (choice) {
            case 1:
                for (Vehicle vehicle : this) {
                    vehicle.showInformation();
                }
                break;
            case 2:
                Vehicle[] vehicles;
                vehicles = this.toArray(new Vehicle[0]);
                Arrays.sort(vehicles, (a, b) -> {
                    if (a.getPrice() > b.getPrice()) {
                        return -1;
                    } else if (a.getPrice() == b.getPrice()) {
                        return 0;
                    } else {
                        return 1;
                    }
                });
                for (Vehicle vehicle : vehicles) {
                    vehicle.showInformation();
                    if (vehicle instanceof Motorbike) {
                        ((Motorbike) vehicle).makeSound();
                    }
                }
                break;

        }
    }

    @Override
    public void storeData() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("src\\file\\vehicles.txt"), "UTF-8");

            BufferedWriter buffWiter = new BufferedWriter(writer);
            for (Vehicle vehicle : this) {
                buffWiter.write(vehicle.toString());
                buffWiter.newLine();
            }
            buffWiter.close();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Store successfully!");
    }

    @Override
    public boolean addCar(String id, String name, String color, double price, String brand) {
        String type = Utils.getString("Enter type: ");
        int year = Utils.getInt("Enter year of manufacture: ", 0, 2022);
        Car car = new Car(id, name, color, price, brand, type, year);
        this.add(car);
        return true;
    }

    @Override
    public boolean addMotorbike(String id, String name, String color, double price, String brand) {
        double speed = Utils.getDouble("Enter speed: ", 0, 9999);
        boolean license = Utils.confirmYesNo("License Requirement: (Y/N)");
        Motorbike motor = new Motorbike(id, name, color, price, brand, speed, license);
        this.add(motor);
        return true;
    }

}
