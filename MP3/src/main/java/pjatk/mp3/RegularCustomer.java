package pjatk.mp3;

import static pjatk.mp3.Utils.*;

public class RegularCustomer extends Person {

    double discount;
    public RegularCustomer(Person previousPerson, double discount) {
        super(previousPerson.firstName, previousPerson.lastName, previousPerson.birthDate);
        try {
            setDiscount(discount);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(discount, "Discount for experienced driver");
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Driver ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nDiscount: " + discount;
    }
}
