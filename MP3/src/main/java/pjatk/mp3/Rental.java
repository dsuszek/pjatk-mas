package pjatk.mp3;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static pjatk.mp3.Utils.*;

public class Rental extends ObjectPlus {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double distance; // atrybut wymagany
    private static double kmPrice = 1.70; // atrybut klasowy - dla wszystkich samochodów opłata za każdy przejechany kilometr jest taka sama
    private Vehicle vehicle;
    private Client client;

    // atrybuty ze zlikwidowane aspektu (długosc wynajmu) umieszczono w nadklasie Rental
    private Enum<RentalLength> rentalLengthType;
    private Double additionalDiscount; // atrybut opcjonalny - dodatkowa zniżka dla wynajmów długoterminowych

    public Rental(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Client client) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setVehicle(vehicle);
            setClient(client);
            setRentalLengthType();
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Client client) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance, unit);
            setVehicle(vehicle);
            setClient(client);
            setRentalLengthType();
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(LocalDate startDate, LocalDate endDate, double distance, Vehicle vehicle, Client client, Double additionalDiscount) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setVehicle(vehicle);
            setClient(client);
            setRentalLengthType();
            setAdditionalDiscount(additionalDiscount);
        } catch (Exception e) {
            removeFromExtent();
        }
    }
    public Rental(LocalDate startDate, LocalDate endDate, double distance, String unit, Vehicle vehicle, Client client, Double additionalDiscount) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance, unit);
            setVehicle(vehicle);
            setClient(client);
            setRentalLengthType();
            setAdditionalDiscount(additionalDiscount);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            logError("Start date cannot be null.");
            throw new IllegalArgumentException("Start date cannot be null.");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null.");
        }
        if (endDate.isBefore(this.getStartDate())) {
            logError("End date cannot be before start date.");
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.endDate = endDate;
    }

    public long getLengthOfRental() {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    public double getCost() { // atrybut pochodny - zależy od trzech pozostałych
        if (additionalDiscount == null) {
            return (this.distance * kmPrice);
        }

        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        return Double.parseDouble( decimalFormat.format((this.distance * kmPrice) - additionalDiscount));
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(distance, "Distance");
        this.distance = distance;
    }

    // przeciążona metoda setDistance, która może przyjąć dwa parametry - double distance oraz String unit
    public void setDistance(double distance, String unit) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(distance, "Distance");

        if ("kilometers".equalsIgnoreCase(unit)) {
            this.distance = distance;
        } else if ("miles".equalsIgnoreCase(unit)) {
            // zamiana mil na kilometry
            DecimalFormat decimalFormat = new DecimalFormat("##.00");
            this.distance = Double.parseDouble(decimalFormat.format(distance * 1.60934));
        } else {
            throw new IllegalArgumentException("Invalid unit specified. Supported units are kilometers or miles.");
        }
    }

    public static double getKmPrice() {
        return kmPrice;
    }

    public static void setKmPrice(double kmPrice) {
        checkCorrectnessOfNumericalValueGreaterThanZero(kmPrice, "Price for one kilometer");
        Rental.kmPrice = kmPrice;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {

        if (this.vehicle == null && vehicle != null) {
            // new vehicle - no vehicle previously assigned
            this.vehicle = vehicle;

            if (vehicle.getRentals().contains(this)) {
                return;
            }
            vehicle.addRental(this);

        } else if (this.vehicle != null && vehicle == null) {
            // removing the vehicle
            Vehicle tmp = this.vehicle;
            this.vehicle = null;
            tmp.removeRental(this);

        } else if (this.vehicle != null && vehicle != null) {
            // changing the vehicle
            Vehicle tmp = this.vehicle;
            this.vehicle = null;
            tmp.removeRental(this);

            this.vehicle = vehicle;
            vehicle.addRental(this);
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

    public Enum<RentalLength> getRentalLengthType() {
        return rentalLengthType;
    }

    public void setRentalLengthType() {
        if (getLengthOfRental() < 3) {
            this.rentalLengthType = RentalLength.ShortTermRental;
        } else {
            this.rentalLengthType = RentalLength.LongTermRental;
        }
    }

    public Double getAdditionalDiscount() {
        return additionalDiscount;
    }

    public void setAdditionalDiscount(Double additionalDiscount) {
        checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero(additionalDiscount, "Additional discount");
        // dla wynajmów krótkoterminowych (poniżej 3 dni) nie ma możliwości uwzględnienia dodatkowego rabatu
        if (this.rentalLengthType == RentalLength.ShortTermRental) {
            return;
        }
        this.additionalDiscount = additionalDiscount;
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "\n\nRental ID: " + id +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + distance +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate +
                "\nRental length type: " + rentalLengthType +
                "\nClient: " + client.getFirstName() + " " + client.getLastName();
    }
}
