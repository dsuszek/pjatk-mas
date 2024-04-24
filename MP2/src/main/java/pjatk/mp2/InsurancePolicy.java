package pjatk.mp2;

import java.time.LocalDate;
import java.util.UUID;

import static pjatk.mp2.Utils.*;

public class InsurancePolicy extends ObjectPlus {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;
    private String scope;
    private Car car;
    private Insurer insurer;

    public InsurancePolicy(LocalDate startDate, LocalDate endDate, double cost, String scope, Car car, Insurer insurer) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setCost(cost);
            setScope(scope);
            setCar(car);
            setInsurer(insurer);
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

    public void setCar(Car car) throws Exception {

        if (this.car == null && car != null) {
            // new car - no car previously assigned
            this.car = car;

            if (car.getInsurancePolicies().contains(this)) {
                return;
            }
            car.addInsurancePolicy(this);
        } else if (this.car != null && car == null) {

            // removing the car
            Car tmp = this.car;
            this.car = null;
            tmp.removeInsurancePolicy(this);
        } else if (this.car != null && car != null) {

            // changing the car
            Car tmp = this.car;
            this.car = null;
            tmp.removeInsurancePolicy(this);

            this.car = car;
            car.addInsurancePolicy(this);
        }
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) throws Exception {

        if (this.insurer == null && insurer != null) {
            // new insurer - no insurer previously assigned
            this.insurer = insurer;

            if (insurer.getInsurancePolicies().contains(this)) {
                return;
            }
            insurer.addInsurancePolicy(this);
        } else if (this.insurer != null && insurer == null) {

            // removing the insurer
            Insurer tmp = this.insurer;
            this.insurer = null;
            tmp.removeInsurancePolicy(this);
        } else if (this.insurer != null && insurer != null) {

            // changing the insurer
            Insurer tmp = this.insurer;
            this.insurer = null;
            tmp.removeInsurancePolicy(this);

            this.insurer = insurer;
            insurer.addInsurancePolicy(this);
        }
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "Insurance policy ID: " + id +
                "\nTotal cost: " + cost +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate +
                "\nScope: " + scope;
    }
}
