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
        // @TODO dokonczyc dziedziczenie rozłączne
        // Szczegóły implementacji: klasa
        System.out.println("Disjoint inheritance");
        System.out.println("-------------------");




        // Klasa abstrakcyjna
        // Szczegóły implementacji: Klasy Car i Truck dziedziczą po klasie abstrakcyjnej Vehicle.
        // Klasa Vehicle nie może mieć bezpośrednich wystąpień, jest ona jedynie wykorzystywana do implementacji hierarchii dziedziczenia.
        Vehicle car1 = new Car(brand2, "Civic", "GW4200A", 1.6);
        Truck truck1 = new Truck(brand4, "MV125", "GD4FG0A", 12.0);
        System.out.println(car1);





        // Polimorficzne wołanie metody
        // Szczegóły implementacji: wyżej opisane klasy Car i Truck posiadają implementację metody abstrakcyjnej calculateRentalPricePerDay.
        // Metoda ta bierze pod uwagę różne parametry w zależności od rodzaju pojazdu.
        // W przypadku ciężarówki istotna jest ładowność pojazdu, a dla samochodu osobowego pojemność silnika.
        System.out.println();
        System.out.println("Polymorphic method calling");
        System.out.println("-------------------");
        System.out.println("Obie klasy Car oraz Truck posiadają implementację metody abstrakcyjnej calculateRentalPricePerDay().");
        System.out.println(car1.calculateRentalPricePerKilometer());
        System.out.println(truck1.calculateRentalPricePerKilometer());





        // Dziedziczenie overlapping
        // @TODO dokonczyc dziedziczenie overlapping
        // Szczegóły implementacji: zastąpiono całą hierarchię dziedzicenia jedną klasą (Car).
        // Ten rodzaj dziedziczenia dodano dla rozróżnienia różnych rodzajów samochodów.
        // W ofercie wypożyczalni są dostępne: samochody sportowe, samochody elektryczne, samochody rodzinne oraz samochody luksusowe.
        // Dany samochód może jednocześnie należeć do jednej, dwóch lub nawet większej liczby kategorii.
        // Do każdej z metod charakterystycznych dla danego rodzaju samochodu dodano warunki sprawdzające, czy wywołanie metody jest uzasadnione.

        System.out.println();
        System.out.println("Overlapping inheritance");
        System.out.println("-------------------------------");
//        Car car1 = new Car("BMW", "M4 GT4", 550, 7.5, null, EnumSet.of(CarType.SportCar));
//        System.out.println(car1);
//        car1.boostEngine(15);
//        car1.lowerSuspension(2);
//        System.out.println();
//        System.out.println("Parameters after tuning: ");
//        System.out.println();
//        System.out.println(car1);

//        car1.addLuxuryDesignElement("LED lightning"); // niemożliwe dla samochodu klasy SportCar
//        car1.getLuxuryDesignElements();
        System.out.println();






        // Wielodziedziczenie
        // @TODO dokonczyc wielodziedziczenie
        System.out.println();
        System.out.println("Multi-inheritance:");
        System.out.println("-------------------------------");
//        GarageManager garageManager = new GarageManager("John", "Tall", Arrays.asList("teamwork"), true, Arrays.asList("camshaft replacement", "servicing"));
//        System.out.println(garageManager);
//        System.out.println();





        // Dziedziczenie wieloaspektowe
        // @TODO dokonczyc dziedziczenie wieloaspektowe
        System.out.println();
        System.out.println("Dziedziczenie wieloaspektowe:");
        System.out.println("-------------------------------");
//        RentalDoorToDoor shortRentalDoorToDoor = new RentalDoorToDoor(LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 12), RentalLength.ShortTermRental);
//        System.out.println(shortRentalDoorToDoor);
//        System.out.println();





        // Dziedziczenie dynamiczne
        // @TODO dokonczyc dziedziczenie dynamiczne
        // Szczegóły implementacji: dziedziczenie dynamiczne pomiędzy klasami Person, YoungDriver, oraz RegularDriver
        System.out.println();
        System.out.println("Dziedziczenie dynamiczne:");
        System.out.println("-------------------------------");
//        Person person1 = new Person("Joe", "Black", LocalDate.of(1995, 4, 30));
//
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
