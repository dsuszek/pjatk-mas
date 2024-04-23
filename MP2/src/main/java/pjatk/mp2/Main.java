package pjatk.mp2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Wszystkie nazwy:
        //  - zmiennych,
        //  - metod,
        //  - klas,
        //  - ostrzeżeń o błędach w danych wejściowych
        //  zostały przygotowane w języku angielskim ze względu na brak problemów z odmianą słów.

        // Elementy ocenanie w MP1:
        // Asocjacja binarna
        // Asocjacja z atrybutem
        // Asocjacja kwalifikowana
        // Kompozycja

        // Każda z asocjacji musi mieć liczność co najmniej 1-* oraz automatyczne tworzenie połączenia zwrotnego.


        // Najpierw tworzone są obiekty, które będą później wykorzystywane do przedstawiania sposobu działania programu.
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

        Client client1 = new Client("Marc", "Jacobs");
        Client client2 = new Client("Jane", "Bright");
        Client client3 = new Client("John", "Lemon");

        Rental rental1 = new Rental(LocalDate.of(2024, 2, 2), LocalDate.of(2024, 2, 15), 530.0d, car4, client1);
        Rental rental2 = new Rental(LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 20), 1412.5d, car2, client1);
        Rental rental3 = new Rental(LocalDate.of(2024, 5, 24), LocalDate.of(2024, 6, 12), 98.0d, car2, client3);
        Rental rental4 = new Rental(LocalDate.of(2024, 2, 5), LocalDate.of(2024, 3, 1), 1412.5d, car2, client2);
        Rental rental5 = new Rental(LocalDate.of(2024, 7, 12), LocalDate.of(2024, 7, 13), 34.0d, car3, client1);

        Region region1 = new Region("Southern");
        Region region2 = new Region("Eastern");
        CompanyBranch companyBranch1 = new CompanyBranch("Auto-East", new Address("West George Street", (short) 191, (short) 10, "Glasgow", "G1 1DN"));

        // Asocjacja pomiędzy klasami Car oraz Rental - implementacja za pomocą natywnych referencji (liczność 1 do *).
        // Implementacja obejmuje:
        // 1. w klasie Rental - pojedynczą referencję do typu Car,
        // 2. w klasie Car - kontener przechowujący referencje do typu Rental (umożliwia pokazanie na wiele instancji - licznosć *).
        System.out.println();
        System.out.println("Association");
        System.out.println("-------------------");
        System.out.println("Rentals assigned to car2:\n");
        System.out.println(car2.getRentals());
        System.out.println();
        System.out.println("Car assigned to rental2:");
        System.out.println(rental1.getCar());

        // Asocjacja z atrybutem pomiędzy klasami Car oraz Insurer.
        // Szczegóły implementacji: zamieniono asocjację z atrybutem na inną konstrukcję UML (asocjację z klasą pośredniczącą CarInsurance).
        // Poprzez taką modyfikację uzyskano dwie "zwykłe" asocjacje, które zostały zaimplementowane w sposób podobny do wyżej opisanej asocjacji binarnej.
        System.out.println();
        System.out.println("Association with attribute");
        System.out.println("-------------------");
        Insurer insurer1 = new Insurer("Alians", "504680690", "alians@contact.com");
        CarInsurance carInsurance1 = new CarInsurance(LocalDate.of(2023, 1, 1), LocalDate.of(2024, 12, 31), 4500.0, "Third party liability insurance with additional glass insurance.", car2, insurer1);
        //@TODO dodac pozostale metody


        // Asocjacja kwalifikowana pomiędzy klasami CompanyBranch oraz Car.
        // Szczegóły implementacji: do klasy CompanyBranch dodano metodę, która na podstawie unikatowego identyfikatora zwraca obiekt klasy Car.
        System.out.println();
        System.out.println("Qualified association");
        System.out.println("-------------------");
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
            e.printStackTrace();
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

    }
}
