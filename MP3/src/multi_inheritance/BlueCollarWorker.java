package multi_inheritance;

import java.util.List;

public class BlueCollarWorker extends Employee implements IBlueCollarWorker {

    private List<String> technicalSkills;

    public BlueCollarWorker(String firstName, String lastName, List<String> technicalSkills) {
        super(firstName, lastName);
        setTechnicalSkills(technicalSkills);
    }

    @Override
    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    @Override
    public List<String> getTechnicalSkills() {
        return getTechnicalSkills();
    }

    @Override
    public void addTechnicalSkill(String technicalSkill) {
        technicalSkills.add(technicalSkill);
    }

    @Override
    public String toString() {
        return "Blue collar worker ID: " + getId() + "\n" +
                "- first name: " + getFirstName() + "\n" +
                "- last name: " + getLastName() + ".\n" +
                "- technical skills: " + getTechnicalSkills() + "\n";
    }
}
