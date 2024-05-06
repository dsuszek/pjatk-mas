package pjatk.mp4;

import java.time.LocalDate;
import java.util.UUID;

import static pjatk.mp4.Utils.*;

public class VehicleInsurance extends ObjectPlus {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;
    private String scope;
    private Vehicle vehicle;
    private Insurer insurer;

    public VehicleInsurance(LocalDate startDate, LocalDate endDate, double cost, String scope, Vehicle vehicle, Insurer insurer) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setCost(cost);
            setScope(scope);
            setVehicle(vehicle);
            setInsurer(insurer);
        } catch (Exception e) {
            e.printStackTrace();
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {

        if (this.vehicle == null && vehicle != null) {
            // new vehicle - no vehicle previously assigned
            this.vehicle = vehicle;

            if (vehicle.getVehicleInsurances().contains(this)) {
                return;
            }
            vehicle.addVehicleInsurance(this);

        } else if (this.vehicle != null && vehicle == null) {
            // removing the vehicle
            Vehicle tmp = this.vehicle;
            this.vehicle = null;
            tmp.removeVehicleInsurance(this);

        } else if (this.vehicle != null && vehicle != null) {
            // changing the vehicle
            Vehicle tmp = this.vehicle;
            this.vehicle = null;
            tmp.removeVehicleInsurance(this);

            this.vehicle = null;
            vehicle.addVehicleInsurance(this);
        }
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {

        if (this.insurer == null && insurer != null) {
            // new insurer - no insurer previously assigned
            this.insurer = insurer;

            if (insurer.getVehicleInsurances().contains(this)) {
                return;
            }
            insurer.addVehicleInsurance(this);

        } else if (this.insurer != null && insurer == null) {
            // removing the insurer
            Insurer tmp = this.insurer;
            this.insurer = null;
            tmp.removeVehicleInsurance(this);

        } else if (this.insurer != null && insurer != null) {
            // changing the insurer
            Insurer tmp = this.insurer;
            this.insurer = null;
            tmp.removeVehicleInsurance(this);

            this.insurer = null;
            insurer.addVehicleInsurance(this);
        }
    }

    public void removeCarInsurance() {
        this.insurer.removeVehicleInsurance(this);
        this.vehicle.removeVehicleInsurance(this);
        this.removeFromExtent();
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "Vehicle insurance ID: " + id +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate +
                "\nCost: " + cost +
                "\nScope: " + scope +
                "\nCar included: " +
                "\n\tCar ID: " + vehicle.getId() +
                "\n\tBrand: " + vehicle.getBrand() +
                "\n\tModel: " + vehicle.getModel() +
                "\n\tCar's company branch: " + vehicle.getCompanyBranch() +
                "\nInsurance company: " + insurer.getCompanyName();
    }
}
