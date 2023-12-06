package pjatk.mp1;

import java.io.Serializable;

public class Address implements Serializable {

    private long id;
    private String streetName;
    private short streetNumber;
    private short apartmentNumber;
    private String city;
    private String postalCode;

    public Address(long id, String streetName, short streetNumber, short apartmentNumber, String city, String postalCode) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public short getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(short streetNumber) {
        this.streetNumber = streetNumber;
    }

    public short getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(short apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
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
