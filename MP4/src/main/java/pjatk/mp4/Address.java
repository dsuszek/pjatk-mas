package pjatk.mp4;

import java.io.Serializable;
import java.util.UUID;

import static pjatk.mp4.Utils.*;

public class Address implements Serializable {
    private UUID id;
    private String streetName;
    private short streetNumber;
    private short apartmentNumber;
    private String city;
    private String postalCode;

    public Address(String streetName, short streetNumber, short apartmentNumber, String city, String postalCode) {
        setId();
        setStreetName(streetName);
        setStreetNumber(streetNumber);
        setApartmentNumber(apartmentNumber);
        setCity(city);
        setPostalCode(postalCode);
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        checkCorrectnessOfStringAttribute("Street name", streetName);
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
        checkCorrectnessOfStringAttribute("City", city);
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        checkCorrectnessOfStringAttribute("Postal code", postalCode);
        this.postalCode = postalCode;
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "\n\tStreet name: " + streetName +
                "\n\tStreet number: " + streetNumber +
                "\n\tApartment number: " + apartmentNumber +
                "\n\tCity: " + city +
                "\n\tPostal code: " + postalCode;
    }
}
