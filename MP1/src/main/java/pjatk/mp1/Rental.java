package pjatk.mp1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static pjatk.mp1.Utils.*;

public class Rental extends ObjectPlus {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double distance; // atrybut wymagany
    private Double extraFee; // atrybut opcjonalny - dla samochodów zaliczanych do klasy Premium
    private static double kmPrice = 1.70; // atrybut klasowy - dla wszystkich samochodów opłata za każdy przejechany kilometr jest taka sama

    public Rental(int id, LocalDate startDate, LocalDate endDate, double distance) {
        super();
        try {
            setId(id);
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        checkCorrectnessOfId(id);
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
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
        return (this.distance * kmPrice) + extraFee;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(distance, "Distance");

        this.distance = distance;
    }

    public Double getExtraFee() {
        if (extraFee != null) {
            return extraFee;
        } else {
            return 0.0;
        }
    }

    public void setExtraFee(Double extraFee) {
        // wartość null jest dozwolona, ponieważ jest to atrybut opcjonalny
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(extraFee, "Extra fee");
        this.extraFee = extraFee;
    }


    public static double getKmPrice() {
        return kmPrice;
    }

    public static void setKmPrice(double kmPrice) {
        checkCorrectnessOfNumericalValueGreaterThanZero(kmPrice, "Price for one kilometer");
        Rental.kmPrice = kmPrice;
    }

    public static void getAllRentals() {
        getExtentForClass(Rental.class);
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "Rental ID: " + id +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + distance +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate;
    }
}
