package pjatk.finalproject.model;

import java.io.IOException;
import java.time.LocalDate;


public class DataGenerator {
    public static void main(String[] args) throws Exception {

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
        Car car18 = new Car(brand2, "C300", "IJ 1234", companyBranch3, 2.0);
        Car car19 = new Car(brand3, "Astra", "JK 5678", companyBranch4, 1.4);
        Car car20 = new Car(brand4, "Baleno", "LM 9101", companyBranch2, 1.8);
        Car car21 = new Car(brand5, "RAV4", "NO 1122", companyBranch5, 2.5);
        Car car22 = new Car(brand6, "Explorer", "PQ 3344", companyBranch6, 3.0);
        Car car23 = new Car(brand7, "X3", "RS 5566", companyBranch7, 2.0);
        Car car24 = new Car(brand8, "Cruze", "TU 7788", companyBranch1, 1.6);
        Car car25 = new Car(brand2, "E350", "VW 9900", companyBranch3, 3.5);
        Car car26 = new Car(brand3, "Insignia", "XY 1234", companyBranch4, 2.0);
        Car car27 = new Car(brand4, "Swift", "YZ 5678", companyBranch2, 1.2);
        Car car28 = new Car(brand5, "Highlander", "AB 9101", companyBranch5, 3.5);
        Car car29 = new Car(brand6, "Fiesta", "CD 1122", companyBranch6, 1.0);
        Car car30 = new Car(brand7, "Z4", "EF 3344", companyBranch7, 2.5);
        Car car31 = new Car(brand8, "Traverse", "GH 5566", companyBranch1, 3.6);
        Car car32 = new Car(brand2, "A-Class", "IJ 7788", companyBranch3, 1.3);
        Car car33 = new Car(brand3, "Meriva", "KL 9910", companyBranch4, 1.6);
        Car car34 = new Car(brand3, "Meriva", "KL 9956", companyBranch4, 1.7);
        Car car35 = new Car(brand3, "Meriva", "KL 9900", companyBranch4, 1.8);
        Car car36 = new Car(brand3, "Meriva", "KV RF00", companyBranch4, 1.9);
        Car car37 = new Car(brand3, "Meriva", "KL 95H0", companyBranch4, 1.5);
        Car car38 = new Car(brand3, "Meriva", "KG G9B0", companyBranch4, 1.6);
        Car car39 = new Car(brand4, "Ertiga", "MN 1234", companyBranch2, 1.5);
        Car car40 = new Car(brand5, "Prius", "OP 5678", companyBranch5, 1.8);
        Car car41 = new Car(brand6, "Escape", "QR 9101", companyBranch6, 2.0);
        Car car42 = new Car(brand7, "7 Series", "ST 1122", companyBranch7, 3.0);
        Car car43 = new Car(brand8, "Tahoe", "UV 3344", companyBranch1, 5.3);
        Car car44 = new Car(brand2, "GLA", "WX 5566", companyBranch3, 2.1);
        Car car45 = new Car(brand3, "Corsa", "YZ 7788", companyBranch4, 1.0);
        Car car46 = new Car(brand3, "Corsa", "BZ 7788", companyBranch4, 1.2);
        Car car47 = new Car(brand3, "Corsa", "MZ 7718", companyBranch4, 1.2);
        Car car48 = new Car(brand3, "Corsa", "YZ 7238", companyBranch4, 1.2);
        Car car49 = new Car(brand3, "Corsa", "YZ 5548", companyBranch4, 1.2);
        Truck truck1 = new Truck(brand1, "Actros", "LL F343", companyBranch1, 6.0);

        Customer customer1 = new Customer("Marc", "Jacobs", LocalDate.of(1989, 2, 4), LocalDate.of(2020, 1, 1), LocalDate.of(2030,1,1));

        RentalDoorToDoor rentalDoorToDoor = new RentalDoorToDoor(
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 4),
                15.0,
                car5,
                customer1,
                RentalLengthTypes.SHORT_TERM_RENTAL,
                0,
                new Address("St. Mark's", (short)4, (short)1, "London", "34-434"));
        RentalOnPremises rentalOnPremises = new RentalOnPremises
                (LocalDate.of(2024, 5, 1),
                        LocalDate.of(2024, 5, 14),
                        100.0,
                        car6,
                        customer1,
                        RentalLengthTypes.LONG_TERM_RENTAL,
                        true);
        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}