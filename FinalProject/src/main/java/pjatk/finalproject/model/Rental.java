package pjatk.finalproject.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static pjatk.finalproject.model.Utils.*;

public class Rental extends ObjectPlus {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double distance;
    private static double kmPrice = 0.90; // atrybut klasowy - dla wszystkich samochodów opłata za każdy przejechany kilometr jest taka sama
    private Vehicle vehicle;
    private Customer customer;

    // atrybuty ze zlikwidowane aspektu (długosc wynajmu) umieszczono w nadklasie Rental
    private Enum<RentalLengthTypes> rentalLengthType;
    private Double additionalDiscount; // atrybut opcjonalny - dodatkowa zniżka dla wynajmów długoterminowych

    public Rental(LocalDate startDate, LocalDate endDate, Double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setVehicle(vehicle);
            setCustomer(customer);
            setRentalLengthType(rentalLengthType);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(LocalDate startDate, LocalDate endDate, Double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance, unit);
            setVehicle(vehicle);
            setCustomer(customer);
            setRentalLengthType(rentalLengthType);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(LocalDate startDate, LocalDate endDate, Double distance, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType, Double additionalDiscount) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance);
            setVehicle(vehicle);
            setCustomer(customer);
            setRentalLengthType(rentalLengthType);
            setAdditionalDiscount(additionalDiscount);
        } catch (Exception e) {
            removeFromExtent();
        }
    }
    public Rental(LocalDate startDate, LocalDate endDate, Double distance, String unit, Vehicle vehicle, Customer customer, Enum<RentalLengthTypes> rentalLengthType,  Double additionalDiscount) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setDistance(distance, unit);
            setVehicle(vehicle);
            setCustomer(customer);
            setRentalLengthType(rentalLengthType);
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
        checkIfDateIsNotNull(startDate, "Start date of rental");
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        checkIfDateIsNotNull(endDate, "End date of rental");
        if (endDate.isBefore(this.getStartDate())) {
            throw new IllegalArgumentException("End date of rental cannot be before start date.");
        }
        this.endDate = endDate;
    }

    public long getLengthOfRental() {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    public Double getCost() { // atrybut pochodny - zależy od wartości pozostałych
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        if (additionalDiscount == null) {
            return Double.parseDouble(decimalFormat.format(this.distance * kmPrice));
        }


        return Double.parseDouble( decimalFormat.format((this.distance * kmPrice) - additionalDiscount));
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        checkCorrectnessOfOptionalNumericalValueGreaterThanZero(distance, "Distance");
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
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) throws Exception {
        if (this.customer == null && customer != null) {
            // new customer - no customer previously assigned
            this.customer = customer;
            customer.addRental(this);

        } else if (this.customer != null && customer == null) {
            // removing the customer
            Customer tmp = this.customer;
            this.customer = null;
            tmp.removeRental(this);

        } else if (this.customer != null && customer != null) {
            // changing the customer
            Customer tmp = this.customer;
            this.customer = null;
            tmp.removeRental(this);

            this.customer = customer;
            customer.addRental(this);
        }
    }

    public Enum<RentalLengthTypes> getRentalLengthType() {
        return rentalLengthType;
    }

    public void setRentalLengthType(Enum<RentalLengthTypes> rentalLengthType) {
        // @TODO automatycznie ustawiac rodzaj wynajmu ze względu na dlugosc
        // metoda getLengthOfRental()
        this.rentalLengthType = rentalLengthType;
    }

    public Double getAdditionalDiscount() {
        return additionalDiscount;
    }

    public void setAdditionalDiscount(Double additionalDiscount) {
        checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero(additionalDiscount, "Additional discount");
        // dla wynajmów krótkoterminowych nie ma możliwości uwzględnienia dodatkowego rabatu
        if (this.rentalLengthType == RentalLengthTypes.SHORT_TERM_RENTAL) {
            throw new IllegalArgumentException("Additional discount cannot be granted to short-term rental.");
        }
        this.additionalDiscount = additionalDiscount;
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "Rental ID: " + id +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + distance +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate +
                "\nRental length type: " + rentalLengthType +
                "\nCustomer: " + customer.getFirstName() + " " + customer.getLastName();
    }
}
