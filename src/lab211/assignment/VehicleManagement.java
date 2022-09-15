package lab211.assignment;

public class VehicleManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        // add menu here
        menu.addItem("Add new vehicle");
        menu.addItem("Update vehicle");
        menu.addItem("Delete vehicle");
        menu.addItem("Search vehicle");
        menu.addItem("Show vehicle list");
        menu.addItem("Store data to file");
        menu.addItem("Quit");
        //adding menu
        I_Menu addMenu = new Menu();
        addMenu.addItem("Add car");
        addMenu.addItem("Add motorbike");
        //searching menu
        I_Menu searchMenu = new Menu();
        searchMenu.addItem("Search by Name");
        searchMenu.addItem("Search by id");
        //showing menu
        I_Menu showMenu = new Menu();
        showMenu.addItem("Show all");
        showMenu.addItem("Show all( descending by price)");

        int choice;
        boolean cont = false;
        VehicleList vehicles = new VehicleList();
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    vehicles.addVehicle(addMenu);
                    break;
                case 2:
                    vehicles.updateVehicle();
                    break;
                case 3:
                    vehicles.deleteVehicle();
                    break;
                case 4:
                    vehicles.searchVehhicle(searchMenu);
                    break;
                case 5:
                    vehicles.showVehicle(showMenu);
                    break;
                case 6:
                    vehicles.storeData();
                    break;
                case 7:
                    cont = menu.confirmYesNo("Do you want to quit?(Y/N)");
                    break;
            }
        } while (!cont);
    }
}
