package pjatk.mp1;

import java.io.Serializable;

import static pjatk.mp1.Utils.*;
import static pjatk.mp1.Utils.checkCorrectnessOfNumericalValueGreaterThanZero;

public class Address implements Serializable {

    private int id;
    private String streetName;
    private short streetNumber;
    private short apartmentNumber;
    private String city;
    private String postalCode;

    public Address(int id, String streetName, short streetNumber, short apartmentNumber, String city, String postalCode) {
        setId(id);
        setStreetName(streetName);
        setStreetNumber(streetNumber);
        setApartmentNumber(apartmentNumber);
        setCity(city);
        setPostalCode(postalCode);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        checkCorrectnessOfId(id);
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        checkCorrectnessOfStringAttribute(streetName);
        this.streetName = streetName;
    }

    public short getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(short streetNumber) {
        checkCorrectnessOfNumericalValueGreaterThanZero(streetNumber, "Street number");
        this.streetNumber = streetNumber;
    }

    public short getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(short apartmentNumber) {
        checkCorrectnessOfNumericalValueGreaterThanZero(apartmentNumber, "Apartment number");
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        checkCorrectnessOfStringAttribute(city);
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        checkCorrectnessOfStringAttribute(postalCode);
        this.postalCode = postalCode;
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "\n\tStreet name: " + streetName +
                "\n\tStreet number: " + streetNumber +
                "\n\tApartment number: " + apartmentNumber +
                "\n\tCity: " + city +
                "\n\tPostal code: " + postalCode;
    }
}
