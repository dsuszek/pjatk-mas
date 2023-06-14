package MP3.multi_aspect;

import java.time.LocalDate;

public class RentalOnPremises extends Rental {

    public RentalOnPremises(LocalDate startDate, LocalDate endDate, RentalLength rentalLength) {
        super(startDate, endDate, rentalLength);
    }

    public String toString() {
        return "Rental on premises ID: " + getId() + "\n" +
                "- start date: " + getStartDate() + "\n" +
                "- end date: " + getEndDate() + "\n" +
                "- type based on rental length: " + getRentalLength() + "\n" +
                "- number of days: " + getNumberOfDays();
    }
}
