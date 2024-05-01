package pjatk.mp3;

import java.time.LocalDate;

public class RentalOnPremises extends Rental {
    private CompanyBranch companyBranch;

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Client client, Enum<RentalLengthTypes> rentalLengthType, CompanyBranch companyBranch) {
        super(startDate, endDate, distance, vehicle, client, rentalLengthType);
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Client client, Enum<RentalLengthTypes> rentalLengthType, CompanyBranch companyBranch) {
        super(startDate, endDate, distance, unit, vehicle, client, rentalLengthType);
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Client client, Double additionalDiscount, Enum<RentalLengthTypes> rentalLengthType, CompanyBranch companyBranch) {
        super(startDate, endDate, distance, vehicle, client, additionalDiscount, rentalLengthType);
    }

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Client client, Double additionalDiscount, Enum<RentalLengthTypes> rentalLengthType, CompanyBranch companyBranch) {
        super(startDate, endDate, distance, unit, vehicle, client, additionalDiscount, rentalLengthType);
    }
}
