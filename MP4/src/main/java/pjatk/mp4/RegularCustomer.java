package pjatk.mp4;

import static pjatk.mp4.Utils.*;

public class RegularCustomer extends Person {

    Double discount;
    public RegularCustomer(Person previousPerson, Double discount) {
        super(previousPerson.firstName, previousPerson.lastName, previousPerson.birthDate);
        try {
            setDiscount(discount);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(discount, "Discount for experienced driver");
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Regular customer ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nDiscount: " + discount;
    }
}
