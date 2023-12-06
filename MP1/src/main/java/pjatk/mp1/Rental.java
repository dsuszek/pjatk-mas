package pjatk.mp1;

import java.time.LocalDate;

public class Rental extends ObjectPlus {
    private long id; // atrybut wymagany
    private LocalDate startDate;
    private LocalDate endDate;
    private int lengthOfRental;
    private double distance; // atrybut wymagany
    private Double extraFee; // atrybut opcjonalny - dla samochodów zaliczanych do klasy Premium
    private static double kmPrice = 1.70; // atrybut klasowy - dla wszystkich samochodów opłata za każdy przejechany kilometr jest taka sama

    public Rental(long id, LocalDate startDate, LocalDate endDate, double distance) {
        super();
        try {
            setId(id);
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            addToExtent();
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(long id, LocalDate startDate, int lengthOfRental, double distance) {
        super();
        try {
            setId(id);
            setStartDate(startDate);
            setLengthOfRental(lengthOfRental);
            setDistance(distance);
            addToExtent();
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(long id, double distance, Double extraFee) {
        super();
        try {
            setId(id);
            setDistance(distance);
            setExtraFee(extraFee);
            addToExtent();
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "Rental ID: " + id +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + distance +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("ID must be positive number.");
        }
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getLengthOfRental() {
        return lengthOfRental;
    }

    public void setLengthOfRental(int lengthOfRental) {
        this.lengthOfRental = lengthOfRental;
    }

    public double getCost() { // atrybut pochodny - zależy od trzech pozostałych
        if (extraFee == null) {
            return (this.distance * this.kmPrice);
        }
        return (this.distance * this.kmPrice) + extraFee;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance must be a positive number.");
        }

        this.distance = distance;
    }

    public Double getExtraFee() {
        if(extraFee != null) {
            return extraFee;
        } else {
            return 0.0;
        }
    }

    public void setExtraFee(Double extraFee) {
        if (extraFee < 0) { // wartość null jest dozwolona, ponieważ jest to atrybut opcjonalny
            throw new IllegalArgumentException("Extra fee must be a positive number.");
        }

        this.extraFee = extraFee;
    }


    public static double getKmPrice() {
        return kmPrice;
    }

    public static void setKmPrice(double kmPrice) {
        Rental.kmPrice = kmPrice;
    }

    public static void getAllRentals() {
        getExtentForClass(Rental.class);
    }

}
