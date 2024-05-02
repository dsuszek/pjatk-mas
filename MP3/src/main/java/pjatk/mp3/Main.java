package pjatk.mp3;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // Wszystkie nazwy:
        //  - zmiennych,
        //  - metod,
        //  - klas,
        //  - ostrzeżeń o błędach w danych wejściowych
        //  zostały przygotowane w języku angielskim ze względu na brak problemów z odmianą słów.

        // Elementy ocenanie w MP2:
        // Dziedziczenie rozłączne
        // Klasa abstrakcyjna
        // Polimorficzne wołanie metody
        // Dziedziczenie overlapping
        // Wielodziedziczenie
        // Dziedziczenie wieloaspektowe
        // Dziedziczenie dynamiczne

        // Każda z asocjacji musi mieć liczność co najmniej 1-* oraz automatyczne tworzenie połączenia zwrotnego.

        // Najpierw tworzone są obiekty, które będą niezbędne do przedstawiania sposobu działania programu.
        Brand brand1 = new Brand("Mercedes", "Germany", 1880);
        Brand brand2 = new Brand("Honda", "Japan");
        Brand brand3 = new Brand("Opel", 1862);
        Brand brand4 = new Brand("MAN", "Germany", 1898);


        // Dziedziczenie rozłączne
        // Szczegóły implementacji: klasy MarketingSpecialist oraz SalesAgent dziedziczą po klasie OfficeWorker.
        // Każda z nich ma charakterystyczne atrybuty - dla klasy SalesAgent jest to prowizja, którą pracownik dostaje od każdego wynajmu.
        // Dla klasy MarketingSpecialist jest to słowny opis portfolio oraz poprzednich kampanii marketingowych.
        System.out.println("Disjoint inheritance");
        System.out.println("-------------------");
        MarketingSpecialist marketingSpecialist = new MarketingSpecialist("James", "Hart", LocalDate.of(2000, 1, 10), Set.of("knowledge certificate - Adobe Photoshop", "knowledge certificate - Adobe Illustrator"), LocalDate.of(2026,12,31));
        SalesAgent salesAgent1 = new SalesAgent("Bob", "Dolan", LocalDate.of(1998, 4, 6), Set.of("advanced English", "advanced Spanish"), LocalDate.of(2024,6, 30), 0.01d);
        System.out.println("Details about marketing specialist James Hart:");
        System.out.println(marketingSpecialist);
        System.out.println();
        System.out.println("Details about sales agent Bob Dolan:");
        System.out.println(salesAgent1);
        System.out.println();


        // Klasa abstrakcyjna
        // Szczegóły implementacji: Klasy Car i Truck dziedziczą po klasie abstrakcyjnej Vehicle.
        // Klasa Vehicle nie może mieć bezpośrednich wystąpień, jest ona jedynie wykorzystywana do implementacji hierarchii dziedziczenia.
        System.out.println("Abstract class");
        System.out.println("-------------------");
        Vehicle car1 = new Car(brand2, "Civic", "GW4200A", 1.6);
        Truck truck1 = new Truck(brand4, "MV125", "GD4FG0A", 11.0);
        System.out.println(car1);





        // Polimorficzne wołanie metody
        // Szczegóły implementacji: wyżej opisane klasy Car i Truck posiadają implementację metody abstrakcyjnej calculateRentalPricePerDay.
        // Metoda ta bierze pod uwagę różne parametry w zależności od rodzaju pojazdu.
        // W przypadku ciężarówki istotna jest ładowność pojazdu, a dla samochodu osobowego pojemność silnika.
        System.out.println();
        System.out.println("Polymorphic method calling");
        System.out.println("-------------------");
        System.out.println("Obie klasy Car oraz Truck posiadają implementację metody abstrakcyjnej calculateRentalPricePerDay().");
        System.out.println("Poniżej przedstawiono wyniki wywołania metody calculateRentalPricePerKilometer bezpośrednio na obiektach klasy Car oraz Truck.");
        System.out.println("Rental price per kilometer for car1: " + car1.calculateRentalPricePerKilometer());
        System.out.println("Rental price per kilometer for truck1: " + truck1.calculateRentalPricePerKilometer());





        // Dziedziczenie overlapping
        // @TODO dokonczyc dziedziczenie overlapping
        // Szczegóły implementacji: zastąpiono całą hierarchię dziedzicenia jedną klasą (Car).
        // Innymi słowy, zgrupowano wszystkie klasy z hierarchii overlapping w ramach jednej klasy Car.
        // Ten rodzaj dziedziczenia dodano dla rozróżnienia różnych rodzajów samochodów.
        // W ofercie wypożyczalni są dostępne: samochody sportowe, samochody elektryczne, samochody rodzinne oraz samochody luksusowe.
        // Dany samochód może jednocześnie należeć do jednej, dwóch lub nawet większej liczby kategorii.
        // Do każdej z metod charakterystycznych dla danego rodzaju samochodu dodano warunki sprawdzające, czy wywołanie metody jest uzasadnione.

        System.out.println();
        System.out.println("Overlapping inheritance");
        System.out.println("-------------------------------");
        Car car2 = new Car(brand3, "Astra", "GJ 8920", 0.0d, EnumSet.of(CarTypes.SPORT_CAR, CarTypes.ELECTRIC_CAR));
        car2.setSuspensionHeight(10.0);
        System.out.println("Suspension height of " + car2.getBrand() + " " + car2.getModel() + " with ID " + car2.getId() + ": " + car2.getSuspensionHeight());
        System.out.println(car2);
        try {
            System.out.println(car2.getLuxuryDesignElements());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();






        // Wielodziedziczenie
        // Szczegóły implementacji: w aplikacji wyróżniono różne rodzaje pracowników.
        // Główny podział uwzględnia pracowników biurowych, oraz pracowników fizycznych.
        // Dodano klasę Office Worker, która dziedziczy po klasie Employee.
        // Dla pracowników biurowych szczególnie istotnymi informacjami są:
        // 1. dane dotyczące ich umiejętności miękkich oraz związanych z pracą biurową,
        // 2. data ważności ostatniego badania wzroku.
        // Dla pracowników fizycznych należy przechowywać inne informacje:
        // 1. dane dotyczące ich umiejętności technicznych związanych np. z pracą w warsztacie,
        // 2. data ważności ostatniego szkolenia BHP.
        System.out.println();
        System.out.println("Multi-inheritance");
        System.out.println("-------------------------------");
        ManagerOfCarServiceStation managerOfCarServiceStation = new ManagerOfCarServiceStation("Mark", "Walker", LocalDate.of(1980, 2, 12), Set.of("team management"), LocalDate.of(2025, 12,31), Set.of("painting the cars"), LocalDate.of(2024, 12, 31));
        System.out.println(managerOfCarServiceStation);
        System.out.println();
        SalesAgent salesAgent2 = new SalesAgent("John", "McGinn", LocalDate.of(1992, 1, 4), Set.of("accounting", "negotiations", "creating the relationships with clients"), LocalDate.of(2026, 3, 1), 0.04);
        System.out.println(salesAgent2);




        // Dziedziczenie wieloaspektowe
        // Szczegóły implementacji:

        // @TODO dokonczyc dziedziczenie wieloaspektowe
        System.out.println();
        System.out.println("Multi-aspect inheritance");
        System.out.println("-------------------------------");
        Customer customer1 = new Customer("John", "Smith", LocalDate.of(1988, 4, 2), LocalDate.of(2010, 10, 20), LocalDate.of(2025, 10, 20));
        RentalDoorToDoor shortRentalDoorToDoor = new RentalDoorToDoor(LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 12), 145d, car2, customer1, RentalLengthTypes.SHORT_TERM_RENTAL, 50d, new Address("Canal Street", (short) 4, (short) 2, "Manchester", "4043 FD"));
        System.out.println(shortRentalDoorToDoor);
        System.out.println();






        // Dziedziczenie dynamiczne
        // @TODO dokonczyc dziedziczenie dynamiczne
        // Szczegóły implementacji: dziedziczenie dynamiczne pomiędzy klasami Person, YoungDriver, oraz RegularDriver
        System.out.println();
        System.out.println("Dynamic inheritance");
        System.out.println("-------------------------------");
        Person regularCustomer1 = new Customer("Michael", "Newman",LocalDate.of(1959,4,2), LocalDate.of(2010, 10, 2), LocalDate.of(2030, 10, 2));
        regularCustomer1 = new RegularCustomer(regularCustomer1, 0.1d);
        System.out.println(regularCustomer1);
//        List<Person> personList = ObjectPlus.getExtentForClass(Person.class);
//        System.out.println();
//        System.out.println("List of all object of class Person:");
//        System.out.println(personList);
//
//        YoungDriver youngDriver1 = new YoungDriver(person1, true);
//        System.out.println();
//
//        System.out.println("List of all young drivers:");
//        List<YoungDriver> youngDrivers = ObjectPlus.getExtentForClass(YoungDriver.class);
//        System.out.println(youngDrivers);
//        System.out.println();
//
//        System.out.println("Young driver is now treated as normal driver:");
//        youngDriver1.promoteToRegularDriver(10);
//        System.out.println();
//
//        System.out.println("List of all young drivers:");
//        System.out.println(youngDrivers);
//        System.out.println();
//
//        System.out.println("List of all regular drivers:");
//        List<RegularDriver> regularDrivers = ObjectPlus.getExtentForClass(RegularDriver.class);
//        System.out.println(regularDrivers);
    }
}
