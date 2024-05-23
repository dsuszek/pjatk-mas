package pjatk.mp4;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
        Brand brand2 = new Brand("Honda", "Japan");
        Region region1 = new Region("West");

        // 1. Ograniczenie atrybutów
        // Szczegóły implementacji: przed zaimplementowaniem ograniczeń dotyczących atrybutów, przygotowano klasy w następujący sposób:
        // 1. atrybuty w klasach są ukryte (najczęściej wykorzystano ograniczenie widoczności private, w szczególnych przypadkach wykorzystano protected),
        // 2. wszelkie operacje na atrybutach odbywają się za pośrednictwem dedykowanych metod - "getterów" i "setterów",
        // 3. powyższą metodą zaimplementowano również dla metod znajdujących się wewnątrz tej samej klasy
        // Poniżej pokazano przykład ograniczenia nałożonego na atrybuty currentDrivingLicenceIssueDate oraz currentDrivingLicenceExpirationDate z klasy Customer
        System.out.println("Attribute constraint");
        System.out.println("-------------------");
        Customer customer1 = new Customer("John", "Michael", LocalDate.of(1963, 3, 4), LocalDate.of(2020, 3, 31),LocalDate.of(2030, 3, 31));
        System.out.println("Details about the first customer:");
        System.out.println(customer1);
        System.out.println("Details about the second customer (who has incorrect driving license issue date):");

        try {
            Customer customer2 = new Customer("John", "Michael", LocalDate.of(1963, 3, 4), LocalDate.of(2020, 3, 31),LocalDate.of(2019, 3, 31));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println();

        // 2. Ograniczenie unique
        // Szczegóły implementacji: to ograniczenie zostało nałożone na atrybut vehicleRegistrationNumber w klasie Vehicle, dla którego wymagana jest unikalność.
        // W celu spełnienia wymagań dotyczących unikalności, wykorzystano metodę checkIfValueIsUniqueAndAddToSet() z klasy Utils.
        System.out.println("Unique constraint");
        System.out.println("-------------------");
        CompanyBranch companyBranch1 = CompanyBranch.createCompanyBranch("Moto-West", new Address("High Street", (short) 1, (short) 44, "Glasgow", "GK4 24F"), region1);
        Car car1 = new Car(brand1, "CLS350", "SF 607AC", companyBranch1, 2.8, EnumSet.of(CarTypes.SPORT_CAR, CarTypes.PREMIUM_CAR));
        System.out.println(car1);
        System.out.println();

        try {
            System.out.println("Details of car2 which has the same registration number as car:");
            Car car2 = new Car(brand2, "S500", "SF 607AC", companyBranch1, 1.3, EnumSet.of(CarTypes.CAR));
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(car2);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Print information about all cars - please note that car2 with incorrect registration number was not assigned to the company branch:");
        for (Vehicle vehicle : companyBranch1.getVehicles()) {
            System.out.println(vehicle);
        }

        System.out.println();
        System.out.println("The car with incorrect number was not added to the extent:");
        System.out.println("The extent of all cars:");
        for (Car car : ObjectPlus.getExtent(Car.class)) {
            System.out.println(car);
        }
        System.out.println();



        // 4. Ograniczenie ordered
        // Szczegóły implementacji:





        // 5. Ograniczenie BAG
        // Szczegóły implementacji:





        // 6. Ograniczenie XOR
        // Szczegóły implementacji: w celu zaimplementowania tego ograniczenia dodano klasę ObjectPlus4, która zawiera pojemnik, w którym przechowywane są nazwy ról,
        // których dotyczy ograniczenie.
        // Klasa ObjecPlus4 zawiera również metodę, która umożliwia dodawanie nowych ról.
        // Aby sprawdzić, czy asocjacja w ramach danej roli jest objęta ograniczeniem, wywoływania jest dedykowana metoda.
        // Jeśli dana rola jest objęta ograniczeniem, wywoływana jest metoda isXorLink(), która sprawdza czy istnieje jakieś powiązanie.
        // Jeśli powiązań nie ma, zostaną one utworzone. W przeciwnym wypadku zostanie zwrócony wyjątek.
        // Poniżej zaprezentowano przykład wykorzystania tego ograniczenia na asocjacji pomiędzy klasami SalesAgent a klasami CompanyBranch oraz InsuranceCompany
        // W systemie mogą być przechowywane dane o wielu agentach sprzedaży, ale każda z tych osób może pracować albo w jednym z oddziałów firmy, albo w jednej z firm ubezpieczeniowych
        System.out.println("XOR constraint");
        System.out.println("-------------------");
        InsuranceCompany insuranceCompany = new InsuranceCompany();
        SalesAgent salesAgent = new SalesAgent("John", "Diaz", LocalDate.of(1990, 2, 4), Set.of("Time management"), LocalDate.of(2030,5,1), 0.02d, companyBranch1);
        System.out.println(salesAgent);

        salesAgent.addLinkXor("EmployeeOfInsuranceCompany", "EmployerInsuranceCompany", insuranceCompany);
        System.out.println(Arrays.toString(salesAgent.getLinks("EmployeeOfInsuranceCompany")));


        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
