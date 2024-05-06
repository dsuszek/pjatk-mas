package pjatk.mp4;

import java.time.LocalDate;

import static pjatk.mp4.Utils.checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero;

public class RentalDoorToDoor extends Rental {
    private double extraFee;
    private Address deliveryAddress;

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, vehicle, customer, rentalLengthType);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, unit, vehicle, customer, rentalLengthType);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, vehicle, customer, rentalLengthType, additionalDiscount);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount, double extraFee, Address deliveryAddress) {
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
}
