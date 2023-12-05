package multi_inheritance;

import java.util.List;

public interface IBlueCollarWorker {

    void setTechnicalSkills(List<String> technicalSkills);
    List<String> getTechnicalSkills();
    void addTechnicalSkill(String technicalSkill);
}
