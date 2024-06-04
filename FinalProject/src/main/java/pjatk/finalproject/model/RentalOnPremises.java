package pjatk.finalproject.model;

import java.time.LocalDate;

public class RentalOnPremises extends Rental {

    boolean freeCancellation;

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, boolean freeCancellation) {
        super(startDate, endDate, distance, vehicle, customer, rentalLengthType);
        try {
            setFreeCancellation(freeCancellation);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, boolean freeCancellation) {
        super(startDate, endDate, distance, unit, vehicle, customer, rentalLengthType);
        try {
            setFreeCancellation(freeCancellation);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount, boolean freeCancellation) {
        super(startDate, endDate, distance, vehicle, customer, rentalLengthType, additionalDiscount);
        try {
            setFreeCancellation(freeCancellation);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount, boolean freeCancellation) {
        super(startDate, endDate, distance, unit, vehicle, customer, rentalLengthType, additionalDiscount);
        try {
            setFreeCancellation(freeCancellation);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public void setFreeCancellation(boolean freeCancellation) {
        this.freeCancellation = freeCancellation;
    }
    public boolean getFreeCancellation() {
        return freeCancellation;
    }
    @Override // Przesłonięcie metody.
    public String toString() {
        return "Rental on premises ID: " + getId() +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + getDistance() +
                "\nStart date: " + getStartDate() +
                "\nEnd date: " + getEndDate() +
                "\nRental length type: " + getRentalLengthType() +
                "\nCustomer: " + getCustomer().getFirstName() + " " + getCustomer().getLastName() +
                "\nFree cancelation: " + getFreeCancellation();
    }
}
