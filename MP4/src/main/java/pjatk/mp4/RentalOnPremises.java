package pjatk.mp4;

import java.time.LocalDate;

public class RentalOnPremises extends Rental {
    private CompanyBranch companyBranch;

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, CompanyBranch companyBranch) {
        super(startDate, endDate, distance, vehicle, customer, rentalLengthType);
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, CompanyBranch companyBranch) {
        super(startDate, endDate, distance, unit, vehicle, customer, rentalLengthType);
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount, CompanyBranch companyBranch) {
        super(startDate, endDate, distance, vehicle, customer, rentalLengthType, additionalDiscount);
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount, CompanyBranch companyBranch) {
        super(startDate, endDate, distance, unit, vehicle, customer, rentalLengthType, additionalDiscount);
    }
}
