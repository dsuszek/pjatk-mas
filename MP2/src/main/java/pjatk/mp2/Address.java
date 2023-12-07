package pjatk.mp2;

import java.util.UUID;

public class Address {
    private UUID id;
    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String city;
    private String postalCode;


    public Address(String streetName, String streetNumber, String apartmentNumber, String city, String postalCode) {
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
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        String regex = "^\\d+$";

        if(!apartmentNumber.matches(regex)) {
            throw new IllegalArgumentException("Apartment number should be made up only of digits.");
        }

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
        String regex = "^\\d{2}(-)\\d{3}$";

        if(!postalCode.matches(regex)) {
            throw new IllegalArgumentException("Postal code must be in format XX-XXX.");
        }

        this.postalCode = postalCode;
    }
}
