package pjatk.mp3;

import java.time.LocalDate;
import java.util.Set;

public class MarketingSpecialist extends OfficeWorker {

    public MarketingSpecialist(String firstName, String lastName, LocalDate birthDate, Set<String> officeWorkSkills, LocalDate dateUntilWhenSightTestIsValid) {
        super(firstName, lastName, birthDate, officeWorkSkills, dateUntilWhenSightTestIsValid);
    }
}
