package pjatk.mp1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import static pjatk.mp1.Rental.getKmPrice;

public class Main {
    public static void main(String[] args) throws Exception {

        // Wszystkie nazwy:
        //  - zmiennych,
        //  - metod,
        //  - klas,
        //  - ostrzeżeń o błędach w danych wejściowych
        //  zostały przygotowane w języku angielskim ze względu na brak problemów z odmianą słów.

        // Elementy ocenanie w MP1:
        // Ekstensja
        // Ekstensja - trwałość
        // Atrybut złożony
        // Atrybut opcjonalny
        // Atrybut powtarzalny
        // Atrybut klasowy
        // Atrybut pochodny
        // Metoda klasowa
        // Przesłonięcie
        // Przeciążenie

        // W pierwszym kroku tworzone są obiekty, które będą później wykorzystywane do przedstawiania sposobu działania programu.
        Brand brand1 = new Brand(1, "Mercedes", "Germany", 1880);
        Brand brand2 = new Brand(2, "Honda", "Japan");
        Brand brand3 = new Brand(3, "Opel");
        Car car1 = new Car(1, brand1, "GL", "SUV", 3.0);

        Rental rental1 = new Rental(1, LocalDate.of(2024, Month.MARCH, 2), LocalDate.of(2024, Month.MARCH, 10), 530, 50d);
        Rental rental2 = new Rental(2, LocalDate.of(2024, Month.JANUARY, 4), LocalDate.of(2024, Month.JANUARY, 20), 1412.5, "miles", 20d);

        // Atrybut złożony - jest zaimplementowany za pomocą dedykowanej klasy Address.
        System.out.println("Complex attribute");
        System.out.println("-------------------");
        Address address1 = new Address(1, "West George Street", (short) 191, (short) 10, "Glasgow", "G1 1DN");
        Branch branch1 = new Branch(1, "North-01", address1);
        System.out.println(branch1);

        // Atrybut opcjonalny - na przykładzie klasy Brand.
        // Dla każdej marki samochodu trzeba podać jej nazwę.
        // Kraj pochodzenia, oraz rok założenia marki są opcjonalne.
        System.out.println();
        System.out.println("Optional attribute");
        System.out.println("-------------------");
        System.out.println("Example of a brand without an optional attribute: ");
        System.out.println(brand1);
        System.out.println();
        System.out.println("Example of a brand with one optional attribute: ");
        System.out.println(brand2);
        System.out.println();
        System.out.println("Example of a brand with two optional attributes: ");
        System.out.println(brand3);
        System.out.println();

        // Atrybut powtarzalny - na przykładzie klasy Car i atrybutu damages.
        // Dla każdego z samochodów można przechowywać informacje o braku uszkodzeń, o jednym uszkodzeniu lub wielu.
        // Implementacja: do przechowywania danych o uszkodzeniach wykorzystano HashSet, ponieważ
        // założono, że informacja o konkretnej szkodzie, np. "zarysowane drzwi lewe przednie" nie powinna być powielana.
        System.out.println();
        System.out.println("Repeating attribute");
        System.out.println("-------------------");
        System.out.println("At the beginning, no damages are registered (damages attribute values) - it is not a required attribute");
        System.out.println(car1.getDamages());
        System.out.println("Add the information about the first damage:");
        car1.addDamage("Scratched front left door");
        System.out.println(car1.getDamages());
        System.out.println("Then add the information about the second damage:");
        car1.addDamage("Broken right side mirror");
        System.out.println(car1.getDamages());
        System.out.println("Car with ID " + car1.getId() + " has the following damages registered: " + car1.getDamages());
        car1.removeDamage("Broken right side mirror");
        System.out.println("The right side mirror was repaired, unfortunately the scratched front left door still not: " + car1.getDamages());

        // Atrybut klasowy
        // Implementacja: ekstensja w ramach tej samej klasy. Zastosowano atrybut klasowy ze słowem kluczowym static.
        // Ten atrybut przechowuje informacje o cenie za każdy przejechany kilometr, która jest stała.
        // Wyżej wspomniana cena nie zmienia się, niezależnie od tego, który samochód wybierze klient.
        System.out.println();
        System.out.println("Class attribute");
        System.out.println("-------------------");
        System.out.println("Example of a class attribute based on the Rental class and the kmPrice attribute:");
        System.out.println(Rental.getKmPrice());

        // Atrybut pochodny - na przykładzie klasy Rental i metody getCost().
        // Koszt wynajmu zależy od trzech pozostałych atrybutów klasy Rental.
        // Wyżej wspomniane atrybuty to: przejechany dystans, cena za kilometr, oraz dodatkowe opłaty.
        System.out.println();
        System.out.println("Derived attribute");
        System.out.println("-------------------");
        System.out.print("Cost of rental with ID 1: " + rental1.getCost() + " consisting of: " + rental1.getDistance() + " kilometers * " + getKmPrice() + " per kilometer + " + rental1.getExtraFee() + " extra fee");
        System.out.println();
        System.out.print("Cost of rental with ID 2: " + rental2.getCost() + " consisting of: " + rental2.getDistance() + " kilometers * " + getKmPrice() + " per kilometer + " + rental2.getExtraFee() + " extra fee");

        // Metoda klasowa dla klasy ObjectPlus
        // Implementacja: wykorzystano ekstensję w ramach tej samej klasy. Użyto słowa kluczowego static.
        // Po wywołaniu metody klasowej na rzecz klasy ObjectPlus przy podaniu parametru będącego ineteresującą użytkownika klasą, zwracana jest cała ekstensja obiektów.
        System.out.println();
        System.out.println();
        System.out.println("Class method");
        System.out.println("-------------------");
        ObjectPlus.showExtentForClass(Rental.class);

        // Przesłonięcie metody toString() dla klasy Car - wszystkie klasy w Javie dziedziczą w sposób niejawny z klasy Object
        System.out.println();
        System.out.println("Method overriding");
        System.out.println("-------------------");
        System.out.println(car1);

        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
