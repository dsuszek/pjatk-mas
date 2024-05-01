package pjatk.mp3;

import java.time.LocalDate;

import static pjatk.mp3.Utils.checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero;

public class RentalDoorToDoor extends Rental {
    private double extraFee;
    private Address deliveryAddress;

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Client client, Enum<RentalLengthTypes> rentalLengthType, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, vehicle, client, rentalLengthType);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Client client, Enum<RentalLengthTypes> rentalLengthType, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, unit, vehicle, client, rentalLengthType);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Client client, Double additionalDiscount, Enum<RentalLengthTypes> rentalLengthType, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, vehicle, client, additionalDiscount, rentalLengthType);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Client client, Double additionalDiscount, Enum<RentalLengthTypes> rentalLengthType, double extraFee, Address deliveryAddress) {
        super(startDate, endDate, distance, unit, vehicle, client, additionalDiscount, rentalLengthType);
        try {
            setExtraFee(extraFee);
            setDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
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
