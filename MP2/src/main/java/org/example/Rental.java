package org.example;

import java.util.UUID;

public class Rental extends ObjectPlus {

    private UUID id; // atrybut wymagany
    private int numberOfDays; // atrybut wymagany
    private double distance; // atrybut wymagany
    private Double extraFee; // atrybut opcjonalny - dla samochodów zaliczanych do klasy Premium
    private static double kmPrice = 1.70; // atrybut klasowy - dla wszystkich samochodów opłata za każdy przejechany kilometr jest taka sama
    private Car car;
    private Client client;

    public Rental(int numberOfDays, double distance) {
        super();
        try {
            setId();
            setNumberOfDays(numberOfDays);
            setDistance(distance);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(int numberOfDays, double distance, Double extraFee) {
        super();
        try {
            setId();
            setNumberOfDays(numberOfDays);
            setDistance(distance);
            setExtraFee(extraFee);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "Rental ID: " + id +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + distance +
                "\nNumber of days: " + numberOfDays +
                "\nCar: \n" + car.toString().indent(2) +
                "\nClient: \n" + client.toString().indent(2);
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        if (numberOfDays < 0) {
            System.out.println(numberOfDays < 0);
            throw new IllegalArgumentException("Number of days should be greater than 0.");
        }
        this.numberOfDays = numberOfDays;
    }

    public double getCost() { // atrybut złożony - zależy od trzech pozostałych
        if (extraFee == null) {
            return (this.distance * this.kmPrice);
        }
        return (this.distance * this.kmPrice) + extraFee;
    }


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance must be a positive number.");
        }

        this.distance = distance;
    }

    public Double getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(Double extraFee) {
        if (extraFee < 0) { // wartość null jest dozwolona, ponieważ jest to atrybut opcjonalny
            throw new IllegalArgumentException("Extra fee must be a positive number.");
        }

        this.extraFee = extraFee;
    }


    public static double getKmPrice() {
        return kmPrice;
    }

    public static void setKmPrice(double kmPrice) {
        Rental.kmPrice = kmPrice;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) throws Exception {

        if (this.car == null && car != null) {
            // new car - no car previously assigned
            this.car = car;

            if (car.getRentals().contains(this)) {
                return;
            }
            car.addRental(this);
        } else if (this.car != null && car != null) {
            // changing the car
            Car tmp = this.car;
            this.car = null;
            tmp.removeRental(this);

            this.car = car;
            car.addRental(this);
        } else if (this.car != null && car == null) {

            // removing the car
            Car tmp = this.car;
            this.car = null;
            tmp.removeRental(this);

            this.car = car;
            car.addRental(this);
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) throws Exception {
        if (this.client == null && client != null) {
            // new client - no client previously assigned
            this.client = client;
            client.addRental(this);

        } else if (this.client != null && client == null) {

            // removing the client
            Client tmp = this.client;
            this.client = null;
            tmp.removeRental(this);
        } else if (this.client != null && client != null) {

            // changing the client
            Client tmp = this.client;
            this.client = null;
            tmp.removeRental(this);

            this.client = client;
            client.addRental(this);
        }
    }
}
