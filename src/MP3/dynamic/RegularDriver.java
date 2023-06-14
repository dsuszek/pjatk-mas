package MP3.dynamic;


import java.util.List;

public class RegularDriver extends Person {

    private float discount;


    // konstruktor kopiujący atrybuty klasy Person
    public RegularDriver(Person prevPerson, float discount) {
        super(prevPerson.firstName, prevPerson.lastName, prevPerson.birthDate);

        // ustaw wartość pola charakterystycznego dla młodego kierowcy
        setDiscount(discount);

        // usuń obiekt prevPerson z ekstensji klasy nadrzędnej Person
        prevPerson.removeFromExtent();
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {

        if(discount < 0) {
            throw new IllegalArgumentException("Discount must be a non-negative number.");
        }
        this.discount = discount;
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "Driver ID: " + getId() +
                "\nFirst name: " + getFirstName() +
                "\nLast name: " + getLastName() +
                "\nDiscount: " + getDiscount();
    }

}
