package dynamic;

public class YoungDriver extends Person {
    private boolean isRecklessDriver;


    // konstruktor kopiujący atrybuty klasy Person
    public YoungDriver(Person prevPerson, boolean isRecklessDriver) {
        super(prevPerson.firstName, prevPerson.lastName, prevPerson.birthDate);

        // ustaw wartość pola charakterystycznego dla młodego kierowcy
        setIsRecklessDriver(isRecklessDriver);

        // usuń obiekt prevPerson z ekstensji klasy nadrzędnej Person
        prevPerson.removeFromExtent();
    }

    public void promoteToRegularDriver(float discount) {
        RegularDriver regularDriver = new RegularDriver(this, discount); // obiekt regularDriver jest automatycznie dodawany do ekstensji (przez konstruktor)
    }

    public boolean getIsRecklessDriver() {
        return isRecklessDriver;
    }

    public void setIsRecklessDriver(boolean isRecklessDriver) {
        this.isRecklessDriver = isRecklessDriver;
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "Young driver ID: " + getId() + "\n" +
                "- first name: " + getFirstName() + "\n" +
                "- last name: " + getLastName() + "\n" +
                "- birth date: " + getBirthDate() + "\n" +
                "- age: " + getAge() + "\n" +
                "- is reckless: " + getIsRecklessDriver();
    }


}
