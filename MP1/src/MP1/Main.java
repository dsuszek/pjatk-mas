package MP1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Brand brand1 = new Brand(1, "Mercedes", "Germany", 1880);
        Brand brand2 = new Brand(1, "Mercedes", "Germany", 1880);
        Car car1 = new Car(1, brand1, "GL", "SUV", 3.0);
        System.out.println(car1);

        car1.addDamage("Broken window");
        System.out.println("MP1.Car with ID " + car1.getId() + " has the following damages registered: " + car1.getDamages());

        System.out.println("Fixing car with ID " + car1.getId());
        System.out.println("...");
        System.out.println("..");
        System.out.println(".");

        car1.removeDamage("Broken window");
        System.out.println("MP1.Car with ID " + car1.getId() + " was fixed. Now it has the following damages: " + car1.getDamages());

        System.out.println("------");
        Rental rental1 = new Rental(1, 10, 14.0, 400d);
        System.out.println(rental1);

        System.out.println("------");
        Rental rental2 = new Rental(2, 1, 0);
        System.out.println(rental2);

        System.out.println("------");
        Client client1 = new Client(1, "Marc", "Jacobs");
        System.out.println(client1);

        Client client2 = new Client(2, "Jane", "Bright");
        System.out.println(client2);

        try {
            ObjectPlus.saveExtent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
