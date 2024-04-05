package pjatk.mp2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        // Tworzę obiekty, które będą później wykorzystywane do przedstawiania sposobu działania programu
        Brand brand1 = new Brand(1, "Mercedes", "Germany", 1880);
        Brand brand2 = new Brand(2, "Honda", "Japan");
        Brand brand3 = new Brand(3, "Opel", 1862);
        Brand brand4 = new Brand(4, "Suzuki");

        Car car1 = new Car(1, brand1, "GL", "SUV", 3.0);
        Car car2 = new Car(2, brand1, "CLA", "Sedan", 2.4);
        Car car3 = new Car(3, brand2, "S500", "Sedan", 1.3);
        Car car4 = new Car(4, brand1, "CL600", "Sedan", 2.0);
        Car car5 = new Car(5, brand3, "Mokka", "SUV", 1.6);
        Car car6 = new Car(6, brand4, "Vitara", "SUV", 2.0);

        Client client1 = new Client(1, "Marc", "Jacobs");
        Client client2 = new Client(2, "Jane", "Bright");
        Client client3 = new Client(3, "John", "Lemon");

        Rental rental1 = new Rental(1, LocalDate.of(2024, 2, 2), LocalDate.of(2024, 2, 15), 530.0d, car1, client1);
        Rental rental2 = new Rental(2, LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 20), 1412.5d, car2, client1);
        Rental rental3 = new Rental(3, LocalDate.of(2024, 5, 24), LocalDate.of(2024, 6, 12), 98.0d, car1, client3);
        Rental rental4 = new Rental(4, LocalDate.of(2024, 2, 5), LocalDate.of(2024, 3, 1), 1412.5d, car4, client2);
        Rental rental5 = new Rental(5, LocalDate.of(2024, 7, 12), LocalDate.of(2024, 7, 13), 34.0d, car1, client1);

        Region region1 = new Region("Southern");
        Region region2 = new Region("Northern");
        Unit unit1 = new Unit(1, "Auto-East");
        Unit unit2 = new Unit(2, "Auto-South");
        Unit unit3 = new Unit(3, "Auto-North");
        Unit unit4 = new Unit(4, "Auto-North");

        System.out.println();
        System.out.println("Association");
        System.out.println("-------------------");
        rental1.addLink("car", "rental", car2);
        rental1.showLinks("car", System.out);

        System.out.println();
        System.out.println("Association with attribute");
        System.out.println("-------------------");


        System.out.println();
        System.out.println("Qualified association");
        System.out.println("-------------------");



        System.out.println();
        System.out.println("Composition");
        System.out.println("-------------------");
        region1.addPart("region", "unit", unit1);
        region1.showLinks("unit", System.out);

        Address address1 = new Address(1, "West George Street", (short) 191, (short) 10, "Glasgow", "G1 1DN");

        unit1.addLink("car", "branch", car1);
        unit1.addLink("car", "branch", car2);
        unit2.addLink("car", "branch", car3);
        unit3.addLink("car", "branch", car4);
        unit4.addLink("car", "branch", car5);
        unit4.addLink("car", "branch", car6);
        region1.addPart("part", "whole", unit1);
        region1.addPart("part", "whole", unit2);
        region1.addPart("part", "whole", unit3);
        region1.addPart("part", "whole", unit4);
        region1.showLinks("part", System.out);
//        region2.addPart("part", "whole", branch1);
    }
}
