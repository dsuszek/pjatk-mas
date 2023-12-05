import abstract_class.Motorcycle;
import abstract_class.Truck;
import dynamic.Person;
import dynamic.RegularDriver;
import dynamic.YoungDriver;
import multi_aspect.RentalDoorToDoor;
import multi_aspect.RentalLength;
import multi_inheritance.GarageManager;
import overlapping.Car;
import overlapping.CarType;
import MP3.ObjectPlus;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // MP 3
        // Klasa abstrakcyjna i polimorfizm
        System.out.println("Klasa abstrakcyjne i polimorfizm: ");
        System.out.println("Klasy Motorcycle i Truck dziedziczą po klasie abstrakcyjnej Vehicle.");
        System.out.println("Obie klasy implementują metodę abstrakcyjną calculateRentalPricePerDay().");
        System.out.println("W zależności od rodzaju pojazdu, w algorytmie wyznaczającym cenę wynajmu brana jest pod uwagę ładowność pojazdu - ciężarówka, albo przyspieszenie (w sekundach do 100km/h) - motocykl.");
        System.out.println();

        Motorcycle motorcycle1 = new Motorcycle("BMW", "R 1250GS", "GD42902", 4.0);
        System.out.println(motorcycle1);
        System.out.println();

        Truck truck1 = new Truck("MAN", "MV125", "GD4200A", 12.0);
        System.out.println(truck1);
        System.out.println();


        // Dziedziczenie overlapping
        System.out.println("-------------------------------");
        System.out.println("Dziedziczenie overlapping: ");
        Car car1 = new Car("BMW", "M4 GT4", 550, 7.5, null, EnumSet.of(CarType.SportCar));
        System.out.println(car1);
        car1.boostEngine(15);
        car1.lowerSuspension(2);
        System.out.println();
        System.out.println("Parameters after tuning: ");
        System.out.println();
        System.out.println(car1);

//        car1.addLuxuryDesignElement("LED lightning"); // niemożliwe dla samochodu klasy SportCar
//        car1.getLuxuryDesignElements();
        System.out.println();


        // Wielodziedziczenie
        System.out.println("-------------------------------");
        System.out.println("Wielodziedziczenie: ");
        GarageManager garageManager = new GarageManager("John", "Tall", Arrays.asList("teamwork"), true, Arrays.asList("camshaft replacement", "servicing"));
        System.out.println(garageManager);
        System.out.println();


        // Dziedziczenie wieloaspektowe
        System.out.println("-------------------------------");
        System.out.println("Dziedziczenie wieloaspektowe:");

        RentalDoorToDoor shortRentalDoorToDoor = new RentalDoorToDoor(LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 12), RentalLength.ShortTermRental);
        System.out.println(shortRentalDoorToDoor);
        System.out.println();


        // Dziedziczenie dynamiczne
        System.out.println("-------------------------------");
        System.out.println("Dziedziczenie dynamiczne:");
        Person person1 = new Person("Joe", "Black", LocalDate.of(1995, 4, 30));

        List<Person> personList = ObjectPlus.getExtentForClass(Person.class);
        System.out.println();
        System.out.println("List of all object of class Person:");
        System.out.println(personList);

        YoungDriver youngDriver1 = new YoungDriver(person1, true);
        System.out.println();

        System.out.println("List of all young drivers:");
        List<YoungDriver> youngDrivers = ObjectPlus.getExtentForClass(YoungDriver.class);
        System.out.println(youngDrivers);
        System.out.println();

        System.out.println("Young driver is now treated as normal driver:");
        youngDriver1.promoteToRegularDriver(10);
        System.out.println();

        System.out.println("List of all young drivers:");
        System.out.println(youngDrivers);
        System.out.println();

        System.out.println("List of all regular drivers:");
        List<RegularDriver> regularDrivers = ObjectPlus.getExtentForClass(RegularDriver.class);
        System.out.println(regularDrivers);
    }
}
