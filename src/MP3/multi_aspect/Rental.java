package MP3.multi_aspect;

import MP3.ObjectPlus;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public abstract class Rental extends ObjectPlus {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;

    // Atrybuty ze zlikwidowane aspektu (w tym przypadku długosc wynajmu) umieszczamy w nadklasie
    private Enum<RentalLength> rentalLength;

    public Rental(LocalDate startDate, LocalDate endDate, RentalLength rentalLength) {
        setId();
        setStartDate(startDate);
        setEndDate(endDate);
        setRentalLength(rentalLength);

        // add to extent
        addToExtent();
    }

    public Enum<RentalLength> getRentalLength() {
        return rentalLength;
    }

    public void setRentalLength(Enum<RentalLength> rentalLength) {

        // walidacja - czy użytkownik prawidłowo podał rodzaj wynajmu
        if (rentalLength == null) {
            throw new IllegalArgumentException("Rental length cannot be null.");
        }

        this.rentalLength = rentalLength;
    }


    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
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

    public int getNumberOfDays() {
        return Period.between(startDate, endDate).getDays();
    }

}
