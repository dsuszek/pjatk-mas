package pjatk.finalproject.model;

import java.io.IOException;
import java.time.LocalDate;


public class DataGenerator {
    public static void main(String[] args) throws Exception {

        // Wszystkie nazwy:
        //  - zmiennych,
        //  - metod,
        //  - klas,
        //  - ostrzeżeń o błędach w danych wejściowych
        //  zostały przygotowane w języku angielskim ze względu na brak problemów z odmianą słów.

        // Elementy ocenanie w MP2:
        // Asocjacja binarna
        // Asocjacja z atrybutem
        // Asocjacja kwalifikowana
        // Kompozycja

        // Każda z asocjacji musi mieć liczność co najmniej 1-* oraz automatyczne tworzenie połączenia zwrotnego.


        // Najpierw tworzone są obiekty, które będą niezbędne do przedstawiania sposobu działania programu.
        Region region1 = new Region("Southern");
        Region region2 = new Region("Eastern");
        Region region3 = new Region("Western");
        Region region4 = new Region("Northern");

        CompanyBranch companyBranch1 = CompanyBranch.createCompanyBranch("Moto Tech", new Address("Old Street", (short) 191, (short) 10, "Glasgow", "G1 1DN"), region1);
        CompanyBranch companyBranch2 = CompanyBranch.createCompanyBranch("Fast Company", new Address("St John", (short) 5, (short) 1, "London", "K2 FN2"), region2);
        CompanyBranch companyBranch3 = CompanyBranch.createCompanyBranch("Happy Cars", new Address("St George", (short) 2, (short) 2, "London", "K7 HB2"), region2);
        CompanyBranch companyBranch4 = CompanyBranch.createCompanyBranch("Lend Me", new Address("High Hill", (short) 4, (short) 1, "London", "P2 KL7"), region2);
        CompanyBranch companyBranch5 = CompanyBranch.createCompanyBranch("Speedy Rentals", new Address("Main St", (short) 45, (short) 5, "Bristol", "BR1 3AB"), region3);
        CompanyBranch companyBranch6 = CompanyBranch.createCompanyBranch("Luxury Rides", new Address("King Rd", (short) 12, (short) 3, "Edinburgh", "EH1 1DE"), region4);
        CompanyBranch companyBranch7 = CompanyBranch.createCompanyBranch("Budget Cars", new Address("Queen St", (short) 22, (short) 2, "Manchester", "M1 1LN"), region3);

        Brand brand1 = new Brand("Mercedes", "Germany", 1880);
        Brand brand2 = new Brand("Honda", "Japan");
        Brand brand3 = new Brand("Opel", 1862);
        Brand brand4 = new Brand("Suzuki");
        Brand brand5 = new Brand("Toyota", "Japan", 1937);
        Brand brand6 = new Brand("Ford", "USA", 1903);
        Brand brand7 = new Brand("BMW", "Germany", 1916);
        Brand brand8 = new Brand("Chevrolet", "USA", 1911);


        Car car1 = new Car(brand1, "GL", "KL 43D", companyBranch2, 3.0);
        Car car2 = new Car(brand1, "CLA", "SD 43FF", companyBranch2, 2.4);
        Car car3 = new Car(brand2, "S500", "KK M434", companyBranch1, 1.3);
        Car car4 = new Car(brand1, "CL600", "OP 430D", companyBranch3, 2.0);
        Car car5 = new Car(brand3, "Mokka", "SVF 54F", companyBranch4, 1.6);
        Car car6 = new Car(brand4, "Vitara", "SP 43DF", companyBranch4, 2.0);
        Car car7 = new Car(brand4, "Vitara", "OG 450", companyBranch4, 2.5);
        Car car8 = new Car(brand1, "S500", "SK 3F0F", companyBranch1, 2.0);
        Car car9 = new Car(brand1, "GL", "SMM 43F", companyBranch1, 2.5);
        Car car10 = new Car(brand5, "Corolla", "AA 1234", companyBranch5, 1.8);
        Car car11 = new Car(brand5, "Camry", "BB 5678", companyBranch5, 2.5);
        Car car12 = new Car(brand6, "Mustang", "CC 9101", companyBranch6, 5.0);
        Car car13 = new Car(brand7, "X5", "DD 1122", companyBranch6, 3.0);
        Car car14 = new Car(brand8, "Malibu", "EE 3344", companyBranch7, 2.0);
        Car car15 = new Car(brand8, "Impala", "FF 5566", companyBranch7, 3.6);
        Car car16 = new Car(brand7, "3 Series", "GG 7788", companyBranch1, 2.0);
        Car car17 = new Car(brand6, "Focus", "HH 9900", companyBranch2, 1.5);

        Customer client1 = new Customer("Marc", "Jacobs", LocalDate.of(1989, 2, 4), LocalDate.of(2020, 1, 1), LocalDate.of(2030,1,1));
        Customer client2 = new Customer("Jane", "Bright", LocalDate.of(1994, 6, 5), LocalDate.of(2018, 1, 1), LocalDate.of(2028,1,1));
        Customer client3 = new Customer("John", "Lemon", LocalDate.of(1999, 2, 14), LocalDate.of(2022, 1, 1), LocalDate.of(2032,1,1));
        Customer client4 = new Customer("Alice", "Wonderland", LocalDate.of(1985, 8, 15), LocalDate.of(2019, 1, 1), LocalDate.of(2029, 1, 1));
        Customer client5 = new Customer("Bob", "Builder", LocalDate.of(1990, 3, 10), LocalDate.of(2017, 1, 1), LocalDate.of(2027, 1, 1));
        Customer client6 = new Customer("Charlie", "Chaplin", LocalDate.of(1975, 12, 25), LocalDate.of(2015, 1, 1), LocalDate.of(2025, 1, 1));
        Customer client7 = new Customer("Diana", "Prince", LocalDate.of(1988, 11, 30), LocalDate.of(2021, 1, 1), LocalDate.of(2031, 1, 1));

        Rental rental1 = new Rental(LocalDate.of(2024, 2, 2), LocalDate.of(2024, 2, 15), 530.0d, car4, client1, RentalLengthTypes.LONG_TERM_RENTAL);
        Rental rental2 = new Rental(LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 20), 1412.5d, car2, client1,RentalLengthTypes.LONG_TERM_RENTAL);
        Rental rental3 = new Rental(LocalDate.of(2024, 5, 24), LocalDate.of(2024, 6, 12), 98.0d, car2, client3,RentalLengthTypes.LONG_TERM_RENTAL);
        Rental rental4 = new Rental(LocalDate.of(2024, 2, 5), LocalDate.of(2024, 3, 1), 1412.5d, car2, client2, RentalLengthTypes.LONG_TERM_RENTAL);
        Rental rental5 = new Rental(LocalDate.of(2024, 7, 12), LocalDate.of(2024, 7, 13), 34.0d, car3, client1, RentalLengthTypes.SHORT_TERM_RENTAL);
        Rental rental6 = new Rental(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 10), 500.0d, car10, client4, RentalLengthTypes.SHORT_TERM_RENTAL);
        Rental rental7 = new Rental(LocalDate.of(2024, 4, 15), LocalDate.of(2024, 4, 25), 750.0d, car11, client5, RentalLengthTypes.SHORT_TERM_RENTAL);
        Rental rental8 = new Rental(LocalDate.of(2024, 5, 5), LocalDate.of(2024, 5, 20), 1000.0d, car12, client6, RentalLengthTypes.LONG_TERM_RENTAL);
        Rental rental9 = new Rental(LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 30), 1250.0d, car13, client7, RentalLengthTypes.LONG_TERM_RENTAL);
        Rental rental10 = new Rental(LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 15), 600.0d, car14, client4, RentalLengthTypes.SHORT_TERM_RENTAL);
        Rental rental11 = new Rental(LocalDate.of(2024, 8, 20), LocalDate.of(2024, 9, 5), 800.0d, car15, client5, RentalLengthTypes.LONG_TERM_RENTAL);
        Rental rental12 = new Rental(LocalDate.of(2024, 10, 5), LocalDate.of(2024, 10, 15), 650.0d, car16, client6, RentalLengthTypes.SHORT_TERM_RENTAL);
        Rental rental13 = new Rental(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 20), 900.0d, car17, client7, RentalLengthTypes.LONG_TERM_RENTAL);

        companyBranch1.addVehicleQualified(car1);
        companyBranch1.addVehicleQualified(car2);
        companyBranch1.addVehicleQualified(car3);
        companyBranch3.addVehicleQualified(car5);
        companyBranch3.addVehicleQualified(car6);
        companyBranch3.addVehicleQualified(car7);
        companyBranch4.addVehicleQualified(car8);
        companyBranch4.addVehicleQualified(car9);
        companyBranch3.addVehicleQualified(car5);
        companyBranch3.addVehicleQualified(car7);
        companyBranch4.addVehicleQualified(car8);
        companyBranch4.addVehicleQualified(car9);
        companyBranch5.addVehicleQualified(car10);
        companyBranch5.addVehicleQualified(car11);
        companyBranch6.addVehicleQualified(car12);
        companyBranch6.addVehicleQualified(car13);
        companyBranch7.addVehicleQualified(car14);
        companyBranch7.addVehicleQualified(car15);

        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}