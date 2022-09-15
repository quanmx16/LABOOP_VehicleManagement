package lab211.assignment;

public class Car extends Vehicle {

    private String type;
    private int yearOfManufacture;

    public Car() {
    }

    public Car(String id, String name, String color, double price, String brand, String type, int yearOfManufacture) {
        super(id, name, color, price, brand);
        this.type = type;
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String toString() {
        return "C" + '|' + getId() + '|' + getName() + '|' + getColor() + '|' + getPrice() + '|' + getBrand() + '|' + getType() + '|' + getYearOfManufacture();
    }

    @Override
    public void showInformation() {
        System.out.printf("%-9s\t%-10s\t%-20s\t%-10s\t%-5f\t%-10s\t%-10s\t%-12d\n",
                "Car", getId(), getName(), getColor(), getPrice(), getBrand(), getType(), getYearOfManufacture());
    }

}
