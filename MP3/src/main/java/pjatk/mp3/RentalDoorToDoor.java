package pjatk.mp3;

import java.time.LocalDate;

import static pjatk.mp3.Utils.checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero;

public class RentalDoorToDoor extends Rental {
    private double extraFee;
    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, Car car, Client client) {
        super(startDate, endDate, distance, car, client);
    }

    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, double distance, String unit, Car car, Client client) {
        super(startDate, endDate, distance, unit, car, client);
    }

    public double getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(double extraFee) {
        checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero(extraFee, "Extra fee");
        this.extraFee = extraFee;
    }
}
