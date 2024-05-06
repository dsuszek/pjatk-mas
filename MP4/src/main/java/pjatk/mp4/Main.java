package pjatk.mp4;

import java.io.IOException;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {

        // Wszystkie nazwy:
        //  - zmiennych,
        //  - metod,
        //  - klas,
        //  - ostrzeżeń o błędach w danych wejściowych
        //  zostały przygotowane w języku angielskim ze względu na brak problemów z odmianą słów.

        // Elementy ocenanie w MP4:
        // 1. Ograniczenie atrybutów
        // 2. Ograniczenie unique
        // 3. Ograniczenie subset
        // 4. Ograniczenie ordered
        // 5. Ograniczenie BAG
        // 6. Ograniczenie XOR
        // 7. Ograniczenie własne

        // Najpierw tworzone są obiekty, które będą niezbędne do przedstawiania sposobu działania programu.
        Brand brand1 = new Brand("Mercedes", "Germany", 1880);
        Region region1 = new Region("West");

        // 1. Ograniczenie atrybutów
        // Szczegóły implementacji: przed zaimplementowaniem ograniczeń dotyczących atrybutów, przygotowano klasy w następujący sposób:
        // 1. atrybuty w klasach są ukryte (najczęściej wykorzystano ograniczenie widoczności private, w szczególnych przypadkach wykorzystano protected),
        // 2. wszelkie operacje na atrybutach odbywają się za pośrednictwem dedykowanych metod - "getterów" i "setterów",
        // 3. powyższą metodą zaimplementowano również dla metod znajdujących się wewnątrz tej samej klasy
        // Poniżej pokazano przykład ograniczenia nałożonego na atrybuty currentDrivingLicenceIssueDate oraz currentDrivingLicenceExpirationDate z klasy Customer
        System.out.println("Attribute constraint");
        System.out.println("-------------------");
        Customer customer = new Customer("John", "Michael", LocalDate.of(1963, 3, 4), LocalDate.of(2020, 3, 31),LocalDate.of(2030, 3, 31));
        System.out.println(customer);
        System.out.println();

        try {
            Customer customer2 = new Customer("John", "Newman", LocalDate.of(1988, 1, 2), LocalDate.of(2030, 2, 1), LocalDate.of(2028, 2, 1));
            System.out.println(customer2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();



        // 2. Ograniczenie unique
        // Szczegóły implementacji: to ograniczenie zostało nałożone na atrybut ID w klasie Vehicle, dla którego wymagana jest unikalność.
        // W celu spełnienia wymagań dotyczących unikalności, wykorzystano klasę UUID.
        // Poniżej przedstawiono działanie aplikacji na podstawie asocjacji kwalifikowanej pomiędzy klasami CompanyBranch oraz Vehicle.
        System.out.println("Unique constraint");
        System.out.println("-------------------");
        CompanyBranch companyBranch1 = CompanyBranch.createCompanyBranch("Moto-West", new Address("High Street", (short) 1, (short) 44, "Glasgow", "GK4 24F"), region1);
        Car car1 = new Car(brand1, "CLS350", "SF 607AC", companyBranch1, 2.8, EnumSet.of(CarTypes.SPORT_CAR, CarTypes.PREMIUM_CAR));
        System.out.println(car1.getCompanyBranch());

        System.out.println(companyBranch1.findVehicleQualified(car1.getId()));

        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
