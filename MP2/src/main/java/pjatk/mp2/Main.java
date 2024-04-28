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
        Brand brand3 = new Brand("Opel", 1862);
        Brand brand4 = new Brand("Suzuki");

        Car car1 = new Car(brand1, "GL", "SUV", 3.0);
        Car car2 = new Car(brand1, "CLA", "Sedan", 2.4);
        Car car3 = new Car(brand2, "S500", "Sedan", 1.3);
        Car car4 = new Car(brand1, "CL600", "Sedan", 2.0);
        Car car5 = new Car(brand3, "Mokka", "SUV", 1.6);
        Car car6 = new Car(brand4, "Vitara", "SUV", 2.0);
        Car car7 = new Car(brand4, "Vitara", "SUV", 2.5);
        Car car8 = new Car(brand1, "S500", "Sedan", 2.0);
        Car car9 = new Car(brand1, "GL", "SUV", 2.5);

        Client client1 = new Client("Marc", "Jacobs");
        Client client2 = new Client("Jane", "Bright");
        Client client3 = new Client("John", "Lemon");

        Rental rental1 = new Rental(LocalDate.of(2024, 2, 2), LocalDate.of(2024, 2, 15), 530.0d, car4, client1);
        Rental rental2 = new Rental(LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 20), 1412.5d, car2, client1);
        Rental rental3 = new Rental(LocalDate.of(2024, 5, 24), LocalDate.of(2024, 6, 12), 98.0d, car2, client3);
        Rental rental4 = new Rental(LocalDate.of(2024, 2, 5), LocalDate.of(2024, 3, 1), 1412.5d, car2, client2);
        Rental rental5 = new Rental(LocalDate.of(2024, 7, 12), LocalDate.of(2024, 7, 13), 34.0d, car3, client1);

        Region region1 = new Region("Southern");
        CompanyBranch companyBranch1 = CompanyBranch.createCompanyBranch("Moto Tech", new Address("Old Street", (short) 191, (short) 10, "Glasgow", "G1 1DN"), region1);

        // Asocjacja pomiędzy klasami Car oraz Rental - implementacja za pomocą natywnych referencji (liczność 1 do *).
        // Implementacja obejmuje:
        // 1. w klasie Rental - pojedynczą referencję do obiektu klasy Car,
        // 2. w klasie Car - kontener przechowujący referencje do typu Rental (umożliwia pokazanie na wiele instancji - licznoćć *).
        System.out.println();
        System.out.println("Association");
        System.out.println("-------------------");
        System.out.println("Rentals assigned to car2:");
        Utils.printInfoAboutObjects(car2.getRentals());

        System.out.println();
        System.out.println("Car assigned to rental2 (rental with ID " + rental2.getId() + "):");
        System.out.println(rental2.getCar());
        System.out.println();
        System.out.println("Details of car2 - should be the same as above.");
        System.out.println();
        System.out.println(car2);

        // Asocjacja z atrybutem pomiędzy klasami Car oraz Insurer.
        // Szczegóły implementacji: zamieniono asocjację z atrybutem na inną konstrukcję UML (asocjację z klasą pośredniczącą CarInsurance).
        // Poprzez taką modyfikację uzyskano dwie "zwykłe" asocjacje, które zostały zaimplementowane w sposób podobny do wyżej opisanej asocjacji binarnej.
        System.out.println();
        System.out.println("Association with attribute");
        System.out.println("-------------------");
        Insurer insurer1 = new Insurer("Atlas", "504680690", "atlas@contact.com");
        CarInsurance carInsurance1 = new CarInsurance(LocalDate.of(2023, 1, 1), LocalDate.of(2024, 12, 31), 4500.0, "Third party liability insurance with additional glass insurance.", car2, insurer1);
        CarInsurance carInsurance2 = new CarInsurance(LocalDate.of(2022, 6, 1), LocalDate.of(2025, 12, 31), 18000.0, "Additional insurance against scratches including anytime towing service.", car2, insurer1);

        // Wszystkie polisy przypisane do samochodu car2.
        System.out.println("-- All insurance policies assigned to car2: --");
        Utils.printInfoAboutObjects(car2.getCarInsurances());

        // Wszystkie polisy przypisane do ubezpieczyciela insurer1.
        System.out.println("-- All insurance policies assigned to insurer1: --");
        Utils.printInfoAboutObjects(insurer1.getCarInsurances());

        System.out.println("-- Number of car insurance policies registered in the system: " + ObjectPlus.getExtent(CarInsurance.class).size() + " --");
        System.out.println();

        // Usunięcie polisy ubezpieczeniowej i wszystkich powiązań z innymi obiektami.
        carInsurance1.removeCarInsurance();

        // Wszystkie polisy przypisane do samochodu car2 (po usunięciu polisy carInsurance1).
        System.out.println("-- All insurance policies assigned to car2 after deleting carInsurance1: --");
        Utils.printInfoAboutObjects(car2.getCarInsurances());

        // Wszystkie polisy przypisane do ubezpieczyciela insurer1 (po usunięciu polisy carInsurance1).
        System.out.println("-- All the insurance policies assigned to insurer1 after deleting carInsurance1: --");
        Utils.printInfoAboutObjects(insurer1.getCarInsurances());

        System.out.println("-- Number of car insurance policies registered in the system: " + ObjectPlus.getExtent(CarInsurance.class).size() + " --");


        // Asocjacja kwalifikowana pomiędzy klasami CompanyBranch oraz Car.
        // Szczegóły implementacji: do klasy CompanyBranch dodano metodę, która na podstawie unikatowego identyfikatora zwraca obiekt klasy Car.
        System.out.println();
        System.out.println("Qualified association");
        System.out.println("-------------------");
        // Przypisywanie samochodów do danego oddziału firmy.
        companyBranch1.addCarQualified(car2);
        companyBranch1.addCarQualified(car3);

        System.out.println("-- Company branch of car2: --");
        System.out.println(car2.getCompanyBranch());
        System.out.println("-- Company branch of car3: --");
        System.out.println(car3.getCompanyBranch());

        System.out.println();
        System.out.println("-- Number of cars assigned to company branch Moto Tech: " + companyBranch1.getCars().size() + " --");
        System.out.println();

        // Wyszukiwanie po identyfikatorze samochodów w danym oddziale firmy.
        try {
            System.out.println("Looking for car assigned to company branch using ID:");
            System.out.println(companyBranch1.findCarQualified(car2.getId()));
            System.out.println();
            System.out.println("Looking for car assigned to company branch using ID:");
            System.out.println(companyBranch1.findCarQualified(car4.getId()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Usuwanie samochodu, który został wcześniej przypisany do danego oddziału firmy.
        System.out.println("-- Removing car3 from company branch " + companyBranch1.getName() + " --");
        companyBranch1.removeCarQualified(car3);

        // Próba usunięcia samochodu, który wcale nie został wcześniej przypisany do danego oddziału firmy.
        // W tym przypadku brak zwracanego błędu, działanie metody kończy się na return.
        companyBranch1.removeCarQualified(car1);

        System.out.println("\nNumber of cars assigned to company branch Moto Tech: " + companyBranch1.getCars().size());
        System.out.println();
        System.out.println("All the cars assigned to company branch Moto Tech:");
        Utils.printInfoAboutObjects(companyBranch1.getCars());

        // Kompozycja pomiędzy klasami CompanyBranch oraz Region.
        // Szczegóły implementacji: wykorzystano podejście związane z modyfikacją rozwiązania z natywnymi referencjami.
        // Konstruktor klasy jest prywatny, dzięki czemu zablokowane zostało samodzielne tworzenie części.
        // Obiekt klasy będącej "częścią" (w tym przypadku CompanyBranch) można połączyć z obiektem będącym "całością" (Region) poprzez
        // wywołanie metody tworzącej część w oparciu o przekazaną całość - CompanyBranch.createCompanyBranch().
        // Cechy tego rozwiązania:
        // 1. "Część" (obiekt klasy CompanyBranch) zawsze musi należeć do jakiejś "całości" (obiektu klasy Region).
        // 2. Przy usuwaniu obiektu będącego "całością" powinny zostać usunięte wszystkie połączenia z "częściami".
        // 3. Jeśli "części" mają dalsze powiązania z obiektami pozostałych klas, również należy je usunąć.
        // 4. Samodzielne tworzenie "części" jest zablokowane.
        // 5. "Części" nie mogą być współdzielone przez wiele "całości".
        System.out.println();
        System.out.println("Composition");
        System.out.println("-------------------");
        Region region2 = new Region("Eastern");
        CompanyBranch companyBranch2 = CompanyBranch.createCompanyBranch("Fast Company", new Address("St John", (short) 5, (short) 1, "London", "K2 FN2"), region2);
        CompanyBranch companyBranch3 = CompanyBranch.createCompanyBranch("Happy Cars", new Address("St George", (short) 2, (short) 2, "London", "K7 HB2"), region2);
        CompanyBranch companyBranch4 = CompanyBranch.createCompanyBranch("Lend Me", new Address("High Hill", (short) 4, (short) 1, "London", "P2 KL7"), region2);
        companyBranch3.addCarQualified(car5);
        companyBranch3.addCarQualified(car6);
        companyBranch4.addCarQualified(car7);
        companyBranch4.addCarQualified(car8);
        companyBranch4.addCarQualified(car9);

        // Próba dodania oddziału companyBranch2 (który już jest zajęty) do region1
        System.out.println("-- Attempt to add companyBranch2 to region1: --");
        try {
            region1.addCompanyBranch(companyBranch2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("-- All the company branches assigned to region2: --");

        Utils.printInfoAboutObjects(region2.getCompanyBranches());

        System.out.println("-- Details of objects car5 and car7: --");
        System.out.println(car5);
        System.out.println();
        System.out.println(car7);
        System.out.println();

        // Usunięcie obiektu companyBranch3
        System.out.println("-- Removing companyBranch3 (" + companyBranch3.getName() + ") --");
        region2.removeCompanyBranch(companyBranch3);

        System.out.println("-- All the company branches assigned to region2 after modification: --");
        Utils.printInfoAboutObjects(region2.getCompanyBranches());

        System.out.println("-- Details of objects car5 and car6 (not assigned to any of the company branches): --");
        System.out.println();
        System.out.println(car5);
        System.out.println();
        System.out.println(car6);

        // Usunięcie obiektu będącego "całością" - region2
        System.out.println("-- Number of all company branches before deleting region2 (" + region2.getName() + "): " + ObjectPlus.getExtent(CompanyBranch.class).size() + " --");
        Utils.printInfoAboutObjects(ObjectPlus.getExtent(CompanyBranch.class));

        region2.removeRegion();
        System.out.println("-- Region2 has been deleted. --");
        System.out.println("-- Number of all company branches after deleting region2 (" + region2.getName() + "): " + ObjectPlus.getExtent(CompanyBranch.class).size() + " --");
        Utils.printInfoAboutObjects(ObjectPlus.getExtent(CompanyBranch.class));

        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
