package multi_aspect;

import java.time.LocalDate;

public class RentalDoorToDoor extends Rental {
    public RentalDoorToDoor(LocalDate startDate, LocalDate endDate, RentalLength rentalLength) {
        super(startDate, endDate, rentalLength);
    }

    @Override
    public String toString() {
        return "Rental of type door to door ID: " + getId() + "\n" +
                "- start date: " + getStartDate() + "\n" +
                "- end date: " + getEndDate() + "\n" +
                "- type based on rental length: " + getRentalLength() + "\n" +
                "- number of days: " + getNumberOfDays();

    }
}
