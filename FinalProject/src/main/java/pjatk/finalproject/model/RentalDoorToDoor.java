package pjatk.finalproject.model;

import java.time.LocalDate;

import static pjatk.finalproject.model.Utils.checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero;

public class RentalDoorToDoor extends Rental {
    private double extraFee;
    private Address deliveryAddress;

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, Double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, vehicle, customer, rentalLengthType);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, Double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, unit, vehicle, customer, rentalLengthType);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, Double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, vehicle, customer, rentalLengthType, additionalDiscount);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, Double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, unit, vehicle, customer, rentalLengthType, additionalDiscount);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public double getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(double extraFee) {
        checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero(extraFee, "Extra fee");
        this.extraFee = extraFee;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public Double getCost() {

        if (this.getDistance() == null) {
            return 0.0;
        }

        if (getAdditionalDiscount() == null) {
            return (this.getDistance() * getKmPrice()) + getExtraFee();
        }

        return (this.getDistance() * getKmPrice()) - getAdditionalDiscount() + getExtraFee();
    }

    @Override
    public String toString() {
        return "Rental door to door ID: " + getId() +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + getDistance() +
                "\nStart date: " + getStartDate() +
                "\nEnd date: " + getEndDate() +
                "\nRental length type: " + getRentalLengthType() +
                "\nCustomer: " + getCustomer().getFirstName() + " " + getCustomer().getLastName() +
                "\n" + getVehicle() +
                "\nExtra fee: " + getExtraFee() +
                "\nDelivery address: " + getDeliveryAddress();
    }
}
