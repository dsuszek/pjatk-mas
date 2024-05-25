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

        System.out.println();
        System.out.println("Print information about all cars - please note that car2 with incorrect registration number was not assigned to the company branch:");
        for (Vehicle vehicle : companyBranch1.getVehicles()) {
            System.out.println(vehicle);
        }

        System.out.println();
        System.out.println("The car with incorrect number was not added to the extent:");
        System.out.println("The extent of all cars:");
        System.out.println();
        for (Car car : ObjectPlus.getExtent(Car.class)) {
            System.out.println(car);
        }
        System.out.println();



        // 4. Ograniczenie ordered
        // Szczegóły implementacji: ograniczenie ordered zastosowano dla asocjacji pomiędzy klasami Vehicle oraz InsuranceCompany (VehicleInsurance).
        // Zapewniono, aby kolejność przechowywanych elementów (w tym przypadku polis ubezpieczeniowych) nie uległa zmianie.
        // Do tego celu wykorzystano odpowiedni kontener (List), który przechowuje powiązania.
        // Takie rozwiązanie pozwala sprawdzić, w jakiej kolejności zostały zawierane kolejne polisy.
        // Jest to istotne zarówno z punktu widzenia firmy wypożyczającej pojazdy, jak i zakładu ubezpieczeń, aby zachować spójność.
        System.out.println("Ordered constraint");
        System.out.println("-------------------");
        InsuranceCompany insuranceCompany1 = new InsuranceCompany("Ins-Tech", "503 334 334", "ins-tech@contact.com");
        Car car3 = new Car(brand2, "R400", "GF 4902", companyBranch1, 1.8, EnumSet.of(CarTypes.CAR));
        VehicleInsurance vehicleInsurance1 = new VehicleInsurance(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 12, 31), 450.0, "Mandatory liability insurance", car3, insuranceCompany1);
        VehicleInsurance vehicleInsurance2 = new VehicleInsurance(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), 464, "Mandatory liability insurance", car3, insuranceCompany1);
        VehicleInsurance vehicleInsurance3 = new VehicleInsurance(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), 480.0, "Mandatory liability insurance", car3, insuranceCompany1);

        System.out.println("Details about all of the vehicle insurances assigned to car3 (they are in a chronological order):");
        System.out.println();
        for (VehicleInsurance vehicleInsurance : car3.getVehicleInsurances()) {
            System.out.println(vehicleInsurance);
        }
        System.out.println();

        // 5. Ograniczenie BAG
        // Szczegóły implementacji: ograniczenie BAG zastosowano w postaci asocjacji z atrybutem pomiędzy klasami Vehicle oraz Mechanic.
        // Takie rozwiązanie pozwala sprawdzić ile razy dany pojazd został naprawiony przez wybranego mechanika,
        System.out.println("BAG constraint");
        System.out.println("-------------------");
        Mechanic mechanic1 = new Mechanic("Ian", "McKellen", LocalDate.of(1967, 12, 4), Set.of("suspension", "gearbox"), LocalDate.of(2025,1,1));
        Car car4 = new Car(brand2, "Jazz", "GG 534G", companyBranch1, 1.2, EnumSet.of(CarTypes.CAR));
        VehicleRepair vehicleRepair1 = new VehicleRepair(LocalDate.of(2023, 10, 23), LocalDate.of(2023,10,30), 4300.0, "shock absorber replaced, gearbox replaced", car4, mechanic1);
        VehicleRepair vehicleRepair2 = new VehicleRepair(LocalDate.of(2024, 2, 4), LocalDate.of(2024,2,4), 100.0, "oil changed", car4, mechanic1);
        VehicleRepair vehicleRepair3 = new VehicleRepair(LocalDate.of(2024,4,12), LocalDate.of(2024,4,15), 135.0, "rear light bulbs replaced", car4, mechanic1);

        System.out.println("How many times mechanic Ian McKellen repaired car4?: " +
                mechanic1.getVehicleRepairs().stream().filter(vehicleRepair -> vehicleRepair.getVehicle().equals(car4)).count());
        System.out.println("And all the details about each one of the repairs:");
        System.out.println();
        for (VehicleRepair vehicleRepair : mechanic1.getVehicleRepairs().stream().filter(vehicleRepair -> vehicleRepair.getVehicle().equals(car4)).toList()) {
            System.out.println(vehicleRepair);
            System.out.println();
        }


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
        InsuranceCompany insuranceCompany2 = new InsuranceCompany("Safety Place", "506 664 334", "sp@contact.com");
        SalesAgent salesAgent = new SalesAgent("John", "Diaz", LocalDate.of(1990, 2, 4), Set.of("Time management"), LocalDate.of(2030,5,1), 0.02d, companyBranch1);
        System.out.println(salesAgent);

        System.out.println(Arrays.toString(salesAgent.getLinks("Employee")));

        try {
            System.out.println("Details about salesAgent who is now assigned as an employee of insurance company:");
            TimeUnit.MILLISECONDS.sleep(100);
            salesAgent.addLinkXor("Employee", "Employer", insuranceCompany2);
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(salesAgent);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("One sales agent cannot work as an internal employee and employee of insurance company at the same time.");

        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
