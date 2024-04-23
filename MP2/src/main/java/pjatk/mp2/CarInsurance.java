package pjatk.mp2;

import java.time.LocalDate;
import java.util.UUID;

import static pjatk.mp2.Utils.*;

public class CarInsurance {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;
    private String scope;
    private Car car;
    private Insurer insurer;

    public CarInsurance(LocalDate startDate, LocalDate endDate, double cost, String scope, Car car, Insurer insurer) {
        setId();
        setStartDate(startDate);
        setEndDate(endDate);
        setCost(cost);
        setScope(scope);
        setCar(car);
        setInsurer(insurer);
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        checkCorrectnessOfNumericalValueGreaterThanZero(cost, "Cost");
        this.cost = cost;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        checkCorrectnessOfStringAttribute("Scope", scope);
        this.scope = scope;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }
}