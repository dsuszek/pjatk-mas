package pjatk.mp2;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
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
        Brand brand1 = new Brand("Mercedes", "Germany", 1880);
        Brand brand2 = new Brand("Honda", "Japan");
        Client client1 = new Client("Marc", "Jacobs");
        Client client2 = new Client("Jane", "Bright");
        Client client3 = new Client("John", "Lemon");


        // Asocjacja pomiędzy klasami Car oraz Rental - implementacja za pomocą natywnych referencji (liczność 1 do *).
        // Implementacja obejmuje:
        // 1. w klasie Rental - pojedynczą referencję do obiektu klasy Car,
        // 2. w klasie Car - kontener przechowujący referencje do obiektów klasy Rental.
        Car car1 = new Car(brand1, "GL", "SUV", 3.0);
        Car car2 = new Car(brand1, "CLA", "Sedan", 2.4);
        Car car3 = new Car(brand2, "S500", "Sedan", 1.3);
        Car car4 = new Car(brand1, "CL600", "Sedan", 2.0);
        Rental rental1 = new Rental(LocalDate.of(2024, 2, 2), LocalDate.of(2024, 2, 15), 530.0d, car4, client1);
        Rental rental2 = new Rental(LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 20), 1412.5d, car2, client1);
        Rental rental3 = new Rental(LocalDate.of(2024, 5, 24), LocalDate.of(2024, 6, 12), 98.0d, car2, client3);
        Rental rental4 = new Rental(LocalDate.of(2024, 2, 5), LocalDate.of(2024, 3, 1), 1412.5d, car2, client2);
        Rental rental5 = new Rental(LocalDate.of(2024, 7, 12), LocalDate.of(2024, 7, 13), 34.0d, car4, client1);

        System.out.println();
        System.out.println("Association");
        System.out.println("-------------------");
        System.out.println("Rentals assigned to car2:\n");
        System.out.println(car2.getRentals());
        System.out.println();
        System.out.println("Car assigned to rental2:");
        System.out.println(rental2.getCar());

        System.out.println("Rentals assigned to car4:\n");
        System.out.println(car4.getRentals());
        System.out.println();
        System.out.println("Car assigned to rental5:");
        System.out.println(rental5.getCar());




        // Asocjacja z atrybutem pomiędzy klasami Car oraz Insurer.
        // Szczegóły implementacji: zamieniono asocjację z atrybutem na inną konstrukcję UML (asocjację z klasą pośredniczącą CarInsurance).
        // Poprzez taką modyfikację uzyskano dwie "zwykłe" asocjacje, które zostały zaimplementowane w sposób podobny do wyżej opisanej asocjacji binarnej.
        System.out.println();
        System.out.println("Association with attribute");
        System.out.println("-------------------");
        Insurer insurer1 = new Insurer("Alians", "504680690", "alians@contact.com");
        InsurancePolicy insurancePolicy1 = new InsurancePolicy(LocalDate.of(2023, 1, 1), LocalDate.of(2024, 12, 31), 4500.0, "Third party liability insurance with additional glass insurance.", car2, insurer1);
        System.out.println(car2.getInsurancePolicies());
        try {
            car2.removeInsurancePolicy(insurancePolicy1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(car2.getInsurancePolicies());




        // Asocjacja kwalifikowana pomiędzy klasami CompanyBranch oraz Car.
        // Szczegóły implementacji: do klasy CompanyBranch dodano metodę, która na podstawie unikatowego identyfikatora zwraca obiekt klasy Car.
        System.out.println();
        System.out.println("Qualified association");
        System.out.println("-------------------");
        // Utworzenie obiektów klasy Region oraz CompanyBranch
        Region region1 = new Region("Southern");
        CompanyBranch companyBranch1 = CompanyBranch.createCompanyBranch("Auto-South", new Address("West George Street", (short) 191, (short) 10, "Glasgow", "G1 1DN"), region1);

        // Przypisywanie samochodów do danego oddziału firmy.
        companyBranch1.addCarQualified(car2);
        companyBranch1.addCarQualified(car3);

        System.out.println("Company branch of car2: " + car2.getCompanyBranch());
        System.out.println("Company branch of car3: " + car3.getCompanyBranch());

        System.out.println("Number of cars assigned to company branch Auto-East: " + companyBranch1.getCars().size());

        // Wyszukiwanie po identyfikatorze samochodów w danym oddziale firmy.
        try {
            System.out.println("Looking for car assigned to company branch using ID:\n" + companyBranch1.findCarQualified(car2.getId()));
            System.out.println("Looking for car assigned to company branch using ID:\n" + companyBranch1.findCarQualified(car4.getId()));
        } catch (IllegalArgumentException e) {
            e.getSuppressed();
        }

        // Usuwanie samochodu, który został wcześniej przypisany do danego oddziału firmy.
        System.out.println("-- Removing car3 from company branch --");
        companyBranch1.removeCarQualified(car3);

        // Próba usunięcia samochodu, który wcale nie został wcześniej przypisany do danego oddziału firmy.
        // W tym przypadku brak zwracanego błędu, działanie metody kończy się na return.
        companyBranch1.removeCarQualified(car1);

        System.out.println("\nNumber of cars assigned to company branch Auto-East: " + companyBranch1.getCars().size());
        System.out.println("\nAll the cars assigned to company branch Auto-East:\n" + companyBranch1.getCars());




        // Kompozycja pomiędzy klasami CompanyBranch oraz Region.
        // Szczegóły implementacji:

        System.out.println();
        System.out.println("Composition");
        System.out.println("-------------------");
        Region region2 = new Region("Eastern");
        CompanyBranch companyBranch2 = CompanyBranch.createCompanyBranch("Auto-East", new Address("Oxford Street", (short) 96, (short) 2, "London", "G5 1MM"), region2);
        // Próba usunięcia oddziału firmy, który nie jest przypisany do danego regionu - działanie metody kończy się na return.
        region2.removeCompanyBranch(companyBranch1);
        // Próba usunięcia nieistniejącego oddziału firmy.
        try {
            region2.removeCompanyBranch(null);
        } catch (IllegalArgumentException e) {
            e.getSuppressed();
        }

        System.out.println(region2);
        System.out.println(companyBranch2.getRegion());
        // Usunięcie całego regionu.
        region2.removeRegion();
        System.out.println(companyBranch2);

        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
