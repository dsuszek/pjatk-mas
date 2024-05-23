package pjatk.mp4;

import java.util.UUID;

public class InsuranceCompany extends ObjectPlus4 {
    private UUID id;

    public InsuranceCompany() {
        setId();
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

}
