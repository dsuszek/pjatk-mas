package pjatk.mp1;

import java.text.DecimalFormat;
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

    public Rental(int id, LocalDate startDate, LocalDate endDate, double distance, String unit) {
        super();
        try {
            setId(id);
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance, unit);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(int id, LocalDate startDate, LocalDate endDate, double distance, Double extraFee) {
        super();
        try {
            setId(id);
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setExtraFee(extraFee);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(int id, LocalDate startDate, LocalDate endDate, double distance, String unit, Double extraFee) {
        super();
        try {
            setId(id);
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setExtraFee(extraFee);
            setDistance(distance, unit);
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
        // wartość null jest dozwolona, ponieważ jest to atrybut opcjonalny
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

    @Override // Przesłonięcie metody.
    public String toString() {
        return "Rental ID: " + id +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + distance +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate;
    }
}
