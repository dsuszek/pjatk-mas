package pjatk.mp4;

import java.time.LocalDate;
import java.util.Set;

public interface IManualWorker {
    public abstract void setTechnicalSkills(Set<String> technicalSkills);
    public abstract Set<String> getTechnicalSkills();
    public abstract void addTechnicalSkill(String technicalSkill);
    public abstract LocalDate getDateUntilWhenWorkHealthAndSafetyTrainingIsValid();
    public abstract void setDateUntilWhenWorkHealthAndSafetyTrainingIsValid(LocalDate date);
    public abstract boolean isWorkHealthAndSafetyTrainingValid();
}
