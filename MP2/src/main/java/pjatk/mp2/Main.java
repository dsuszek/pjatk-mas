package pjatk.mp2;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Tworzę obiekty, które będą później wykorzystywane do przedstawiania sposobu działania programu
        Brand brand1 = new Brand(1, "Mercedes", "Germany", 1880);
        Brand brand2 = new Brand(2, "Honda", "Japan");
        Brand brand3 = new Brand(3, "Opel", 1862);
        Brand brand4 = new Brand(4, "Suzuki");

        Rental rental1 = new Rental(1, LocalDate.of(2024, 2, 2), LocalDate.of(2024, 3, 15), 530d);
        Rental rental2 = new Rental(2, LocalDate.of(2024, 1, 4), LocalDate.of(2023, 1, 20), 1412.5d);
        Client client1 = new Client(1, "Marc", "Jacobs");
        Client client2 = new Client(2, "Jane", "Bright");
        Region region1 = new Region("Northern");
        Address address1 = new Address("Square Street", "10", "2", "Sopot", "90-222");
        Branch branch1 = Branch.createBranch(region1,"Auto - North", address1);

        Car car1 = new Car("GL", "SUV", 3.0);
        Car car2 = new Car("CLA", "Sedan", 2.4);
        Car car3 = new Car("S500", "Sedan", 1.3);
        Car car4 = new Car("CL600", "Sedan", 2.0);

        // -------------------------------------------------------------------------
        System.out.println();
        System.out.println("------------------");
        System.out.println("Association");
        System.out.println();
        // jeden samochód może mieć jedną markę
        // jedna marka może mieć wiele samochodów
        car1.setBrand(brand1);
        car2.setBrand(brand1);
        car3.setBrand(brand1);
        car4.setBrand(brand1);
        System.out.println(brand1);


        // -------------------------------------------------------------------------
        System.out.println();
        System.out.println("------------------");
        System.out.println("Association with attribute");
        System.out.println();
        // klasa asocjacyjna Rental - pomiędzy klasami Car oraz Client
        rental1.setCar(car2);
        rental1.setClient(client1);
        System.out.println(rental1);

        System.out.println("Change the car for rental: ");
        rental1.setCar(car1);
        System.out.println(rental1);


        // -------------------------------------------------------------------------
        System.out.println();
        System.out.println("------------------");
        System.out.println("Qualified association");
        System.out.println();
        // car --> branch
        branch1.addCarQualified(car1);
        branch1.addCarQualified(car2);
        System.out.println(branch1);


        // -------------------------------------------------------------------------
        System.out.println();
        System.out.println("------------------");
        System.out.println("Composition"); // region zawiera wiele oddziałów
        System.out.println();
        // bez oddziałów region nie może istnieć
        // Region 1 ---------- Branch [1..*]
        region1.addBranch(branch1);
        System.out.println(region1);

        // usuwanie całości
        Address address2 = new Address("Peak Street", "43", "1", "Poznań", "00-222");
        Region region2 = new Region("Western");
        Branch branch2 = Branch.createBranch(region2, "Auto - Western", address2);
        branch2.addCarQualified(car3);
        branch2.addCarQualified(car4);

        System.out.println(region2);
        System.out.println("List of all regions: ");
        List<Region> regions = Region.getExtentForClass(Region.class);

        for (Region region : regions) {
            System.out.println(region);
        }

        System.out.println("List of all branches: ");
        List<Branch> branches = Branch.getExtentForClass(Branch.class);

        for(Branch branch : branches) {
            System.out.println(branch);
        }

        region2.deleteRegion();
        System.out.println("The decision about removing region2 has been taken.\n");

        System.out.println("List of all regions after modification: ");
        for (Region region : regions) {
            System.out.println(region);
        }

        System.out.println("List of all branches after modification: ");
        for(Branch branch : branches) {
            System.out.println(branch);
        }

        System.out.println("List of all cars: ");
        List<Car> cars = Car.getExtentForClass(Car.class);
        for(Car car : cars) {
            System.out.println(car);
        }
    }
}
