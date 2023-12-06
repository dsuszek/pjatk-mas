package pjatk.mp1;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static pjatk.mp1.Rental.getAllRentals;

public class Main {
    public static void main(String[] args) {
        // Tworzę obiekty, które będą później wykorzystywane do przedstawiania sposobu działania programu
        Brand brand1 = new Brand(1, "Mercedes", "Germany", 1880);
        Brand brand2 = new Brand(2, "Honda", "Japan");
        Brand brand3 = new Brand(3, "Opel");
        Car car1 = new Car(1, brand1, "GL", "SUV", 3.0);
        Rental rental1 = new Rental(1, LocalDate.of(2024, 2, 2), LocalDate.of(2024, 3, 15),530d);
        Rental rental2 = new Rental(2, LocalDate.of(2024, 1, 4), LocalDate.of(2023, 1, 20), 1412.5d);
        Client client1 = new Client(1, "Marc", "Jacobs");
        Client client2 = new Client(2, "Jane", "Bright");

        // Atrybut opcjonalny - na przykładzie klasy Brand
        System.out.println("Optional attribute");
        System.out.println("-------------------");
        System.out.println("Example of a brand without an optional attribute: ");
        System.out.println(brand1);
        System.out.println();
        System.out.println("Example of a brand with one optional attribute: ");
        System.out.println(brand2);
        System.out.println();
        System.out.println("Example of a brand with two optional attributes: ");
        System.out.println(brand3);
        System.out.println();

        // Atrybut powtarzalny - na przykładzie klasy Car i atrybutu damages
        System.out.println();
        System.out.println("Repeating attribute");
        System.out.println("-------------------");
        System.out.println("At the beginning, no damages are registered (damages attribute values) - it is not a required attribute");
        System.out.println(car1.getDamages());
        System.out.println("Add the information about the first damage");
        car1.addDamage("Broken window");
        System.out.println(car1.getDamages());
        System.out.println("Then add the information about the second damage");
        car1.addDamage("Damaged gearbox");
        System.out.println(car1.getDamages());
        System.out.println("Car with ID " + car1.getId() + " has the following damages registered: " + car1.getDamages());
        car1.removeDamage("Broken window");
        System.out.println("The broken window was repaired, unfortunately the gearbox still is not working: " + car1.getDamages());

        // Atrybut klasowy
        System.out.println();
        System.out.println("Class attribute");
        System.out.println("-------------------");
        System.out.println("Example of a class attribute based on the Rental class and the kmPrice attribute:");
        System.out.println(Rental.getKmPrice());
        
        // Atrybut pochodny - na przykładzie klasy Rental i metody getCost()
        // koszt wynajmu zależy od trzech pozostałych atrybutów klasy Rental, tzn. od:
        // przejechanego dystansu, ceny za kilometr, oraz dodatkowych opłat
        System.out.println();
        System.out.println("Derived attribute");
        System.out.println("-------------------");
        System.out.print("Cost of rental with ID 1: " + rental1.getCost() + " consisting of: " + rental1.getDistance());
        System.out.println();
        System.out.print("Cost of rental with ID 2: " + rental2.getCost());

        // Atrybut złożony - na przykładzie klasy Branch oraz atrybutu Address
        System.out.println();
        System.out.println("Composite attribute");
        System.out.println("-------------------");
        Address address1 = new Address(1, "West George Street", (short) 191, (short) 10, "Glasgow", "G1 1DN");
        Branch branch1 = new Branch(1, "North-01", address1);
        System.out.println(branch1.toString());

        // Metoda klasowa
        System.out.println();
        System.out.println("Class method");
        System.out.println("-------------------");
        getAllRentals();

        // Przesłonięcie metody toString() dla klasy Car
        System.out.println();
        System.out.println("Overriding method");
        System.out.println("-------------------");
        System.out.println(car1);

        try {
            ObjectPlus.saveExtent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n");
        List rentals = ObjectPlus.getExtentForClass(Rental.class);
        for (int i = 0; i < rentals.size(); i++) {
            System.out.println(rentals.get(i));
        }
    }
}
