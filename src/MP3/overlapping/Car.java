package MP3.overlapping;

import MP3.ObjectPlus;

import java.util.*;

public class Car extends ObjectPlus implements IPremiumCar, ISportCar {
    private UUID id;
    private String brand;
    private String model;
    private int horsepower;
    private Double suspensionHeight; // dostępne tylko dla klasy SportCar
    private List<String> luxuryDesignElements; // dostępne tylko dla klasy PremiumCar
    private EnumSet<CarType> carTypes;


    public Car(String brand, String model, int horsepower, double suspensionHeight, List<String> luxuryDesignElements, EnumSet<CarType> carTypes) {
        super();

        //  4.3. Przydzielenie zestawu ról jest statyczne - nie jest możliwe dodanie / usunięcie / zmiana roli po utworzeniu obiektu.
        setCarTypes(carTypes);
        setId();
        setBrand(brand);
        setModel(model);
        setHorsepower(horsepower);
        setSuspensionHeight(suspensionHeight);
        setLuxuryDesignElement(luxuryDesignElements);
    }


    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        if (horsepower <= 0) {
            throw new IllegalArgumentException("Number of horsepower must be greater than 0.");
        }
        this.horsepower = horsepower;
    }

    public double getSuspensionHeight() {
        if (!carTypes.contains(CarType.SportCar)) {
            throw new RuntimeException("This is not a sport car.");
        }

        return suspensionHeight;
    }

    public void setSuspensionHeight(Double suspensionHeight) {
        if (!carTypes.contains(CarType.SportCar)) {
            throw new RuntimeException("Info about suspension height is stored only for sport cars.");
        } else if (suspensionHeight == null && carTypes.contains(CarType.SportCar)) {
            throw new IllegalArgumentException("Null value not allowed.");
        } else if (suspensionHeight <= 5.0) {
            throw new IllegalArgumentException("Suspension height must be at least 5 cm.");
        }

        this.suspensionHeight = suspensionHeight;
    }

    public void setLuxuryDesignElement(List<String> luxuryDesignElements) {
        if (!carTypes.contains(CarType.PremiumCar) && luxuryDesignElements == null) {
            return;
        } else if(!carTypes.contains(CarType.PremiumCar)) {
            throw new RuntimeException("Info about luxury design elements is stored only for premium cars.");
        } else if (carTypes.contains(CarType.PremiumCar) && luxuryDesignElements == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }

        this.luxuryDesignElements = luxuryDesignElements;
    }

    public List<String> getLuxuryDesignElements() {
        if(!carTypes.contains(CarType.PremiumCar)) {
            throw new RuntimeException("This is not a premium car.");
        }

        return Collections.unmodifiableList(luxuryDesignElements);
    }

    public EnumSet<CarType> getCarTypes() {
        return carTypes;
    }

    private void setCarTypes(EnumSet<CarType> carTypes) { // prywatny setter, dzięki czemu nie będzie można zmienić raz przypisanego typu samochodu

        // walidacja - czy użytkownik prawidłowo podał rodzaj(e) samochodu
        if (carTypes == null) {
            throw new IllegalArgumentException("Car type cannot be null.");
        } else if (carTypes.isEmpty()) {
            throw new IllegalArgumentException("Please specify type of car.");
        }

        this.carTypes = carTypes;
    }

    @Override
    public void lowerSuspension(double differenceInCm) {
        if (!carTypes.contains(CarType.SportCar)) {
            throw new RuntimeException("Suspension can be lowered only for sport cars.");
        } else if (differenceInCm <= 0) {
            throw new IllegalArgumentException("Please specify by how many centimeters suspension should be lowered.");
        } else if ((this.suspensionHeight -= differenceInCm) < 5.0) {
            throw new IllegalArgumentException("Suspension height must be at least 5 cm.");
        }

        this.suspensionHeight -= differenceInCm;
    }

    @Override
    public void boostEngine(int horsepowerToBeAdded) {
        if (!carTypes.contains(CarType.SportCar)) {
            throw new RuntimeException("Engine can be boosted only for sport cars.");
        } else if (horsepowerToBeAdded <= 0) {
            throw new IllegalArgumentException("If you would like to boost the engine, please specify how much horsepower should be added.");
        } else if (horsepowerToBeAdded > 30) {
            throw new IllegalArgumentException("Engine can be boosted by 30 horsepower at the maximum.");
        }

        this.horsepower += horsepowerToBeAdded;
    }

    @Override
    public void addLuxuryDesignElement(String luxuryDesignElement) {
        if(!carTypes.contains(CarType.PremiumCar)) {
            throw new RuntimeException("Luxury design element can be added only to premium car.");
        } else if (luxuryDesignElements == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }

        this.luxuryDesignElements.add(luxuryDesignElement);
    }

    @Override
    public String toString() {

        String info = "";

        if (carTypes.contains(CarType.SportCar)) {
            info = "Parameters of car with ID: " + getId() + "\n" +
                    "- brand: " + getBrand() + "\n" +
                    "- model: " + getModel() + "\n" +
                    "- horsepower: " + getHorsepower() + "\n" +
                    "- suspension height: " + getSuspensionHeight() + "\n" +
                    "- type(s) of car: " + getCarTypes();

        } else if (carTypes.contains(CarType.PremiumCar)) {
            info = "Parameters of car with ID: " + getId() + "\n" +
                    "- brand: " + getBrand() + "\n" +
                    "- model: " + getModel() + "\n" +
                    "- horsepower: " + getHorsepower() + "\n" +
                    "- luxury design elements: " + luxuryDesignElements + " cm\n" +
                    "- type(s) of car: " + getCarTypes();
        }
        return info;
    }
}
