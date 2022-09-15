package lab211.assignment;

public class Motorbike extends Vehicle {

    private double speed;
    private boolean licenseRequirement;

    public Motorbike() {
    }

    public Motorbike(String id, String name, String color, double price, String brand, double speed, boolean licenseRequirement) {
        super(id, name, color, price, brand);
        this.speed = speed;
        this.licenseRequirement = licenseRequirement;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isLicenseRequirement() {
        return licenseRequirement;
    }

    public void setLicenseRequirement(boolean licenseRequirement) {
        this.licenseRequirement = licenseRequirement;
    }

    public void makeSound() {
        System.out.println("Tin tin tin");
    }
//id: %10s|name: %20s|color: %10s|price %5f|brand: %10s|type: %10s|yob: %8d|

    @Override
    public void showInformation() {
        System.out.printf("%-9s\t%-10s\t%-20s\t%-10s\t%-5f\t%-10s\t%-10.2f\t%-12b\n",
                "Motorbike", getId(), getName(), getColor(), getPrice(), getBrand(), getSpeed(), isLicenseRequirement());
    }

    @Override
    public String toString() {
        return "M" + '|' + getId() + '|' + getName() + '|' + getColor() + '|' + getPrice() + '|' + getBrand() + '|' + getSpeed() + '|' + isLicenseRequirement();
    }

}
