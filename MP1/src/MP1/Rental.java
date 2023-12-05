package MP1;

public class Rental extends ObjectPlus {

    private long id; // atrybut wymagany
    private int numberOfDays; // atrybut wymagany
    private double distance; // atrybut wymagany
    private Double extraFee; // atrybut opcjonalny - dla samochodów zaliczanych do klasy Premium
    private static double kmPrice = 1.70; // atrybut klasowy - dla wszystkich samochodów opłata za każdy przejechany kilometr jest taka sama

    public Rental(long id, int numberOfDays, double distance) {
        super();
        try {
            setId(id);
            setNumberOfDays(numberOfDays);
            setDistance(distance);

        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Rental(long id, int numberOfDays, double distance, Double extraFee) {
        super();
        try {
            setId(id);
            setNumberOfDays(numberOfDays);
            setDistance(distance);
            setExtraFee(extraFee);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "MP1.Rental ID: " + id +
                "\nTotal cost: " + getCost() +
                "\nTotal distance: " + distance +
                "\nNumber of days: " + numberOfDays;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("ID must be positive number.");
        }
        this.id = id;
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
}
