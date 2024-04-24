package pjatk.mp2;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static pjatk.mp2.Utils.*;

public class Rental extends ObjectPlus {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double distance; // atrybut wymagany
    private Double extraFee; // atrybut opcjonalny - dla samochodów zaliczanych do klasy Premium
    private static double kmPrice = 1.70; // atrybut klasowy - dla wszystkich samochodów opłata za każdy przejechany kilometr jest taka sama
    private Car car;
    private Client client;

    public Rental(LocalDate startDate, LocalDate endDate, double distance, Car car, Client client) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setCar(car);
            setClient(client);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(LocalDate startDate, LocalDate endDate, double distance, String unit, Car car, Client client) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance, unit);
            setCar(car);
            setClient(client);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(LocalDate startDate, LocalDate endDate, double distance, Double extraFee, Car car, Client client) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setExtraFee(extraFee);
            setCar(car);
            setClient(client);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(LocalDate startDate, LocalDate endDate, double distance, String unit, Double extraFee, Car car, Client client) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setExtraFee(extraFee);
            setDistance(distance, unit);
            setCar(car);
            setClient(client);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            logError("Start date cannot be null.");
            throw new IllegalArgumentException("Start date cannot be null.");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null.");
        }
        if (endDate.isBefore(this.getStartDate())) {
            logError("End date cannot be before start date.");
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.endDate = endDate;
    }

    public long getLengthOfRental() {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    public double getCost() { // atrybut pochodny - zależy od trzech pozostałych
        if (extraFee == null) {
            return (this.distance * kmPrice);
        }

        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        return Double.parseDouble( decimalFormat.format((this.distance * kmPrice) + extraFee));
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(distance, "Distance");
        this.distance = distance;
    }

    // przeciążona metoda setDistance, która może przyjąć dwa parametry - double distance oraz String unit
    public void setDistance(double distance, String unit) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(distance, "Distance");

        if ("kilometers".equalsIgnoreCase(unit)) {
            this.distance = distance;
        } else if ("miles".equalsIgnoreCase(unit)) {
            // zamiana mil na kilometry
            DecimalFormat decimalFormat = new DecimalFormat("##.00");
            this.distance = Double.parseDouble(decimalFormat.format(distance * 1.60934));
        } else {
            throw new IllegalArgumentException("Invalid unit specified. Supported units are kilometers or miles.");
        }
    }

    public double getExtraFee() {
        if (extraFee != null) {
            return extraFee;
        } else {
            return 0.0;
        }
    }

    public void setExtraFee(Double extraFee) {
        // Wartość null jest dozwolona, ponieważ jest to atrybut opcjonalny.
        checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero(extraFee, "Extra fee");
        this.extraFee = extraFee;
    }


    public static double getKmPrice() {
        return kmPrice;
    }

    public static void setKmPrice(double kmPrice) {
        checkCorrectnessOfNumericalValueGreaterThanZero(kmPrice, "Price for one kilometer");
        Rental.kmPrice = kmPrice;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) throws Exception {

        if (this.car == null && car != null) {
            // new car - no car previously assigned
            this.car = car;

            if (car.getRentals().contains(this)) {
                return;
            }
            car.addRental(this);
        } else if (this.car != null && car == null) {

            // removing the car
            Car tmp = this.car;
            this.car = null;
            tmp.removeRental(this);
        } else if (this.car != null && car != null) {

            // changing the car
            Car tmp = this.car;
            this.car = null;
            tmp.removeRental(this);

            this.car = car;
            car.addRental(this);
        }
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) throws Exception {
        if (this.client == null && client != null) {
            // new client - no client previously assigned
            this.client = client;
            client.addRental(this);

        } else if (this.client != null && client == null) {

            // removing the client
            Client tmp = this.client;
            this.client = null;
            tmp.removeRental(this);
        } else if (this.client != null && client != null) {

            // changing the client
            Client tmp = this.client;
            this.client = null;
            tmp.removeRental(this);

            this.client = client;
            client.addRental(this);
        }
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "\n\nRental ID: " + id +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + distance +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate +
                "\nClient: " + client.getFirstName() + " " + client.getLastName();
    }
}
