package pjatk.finalproject;

import java.time.LocalDate;
import java.util.UUID;

import static pjatk.finalproject.Utils.*;

public class VehicleInsurance extends ObjectPlus {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;
    private String scope;
    private Vehicle vehicle;
    private InsuranceCompany insuranceCompany;

    public VehicleInsurance(LocalDate startDate, LocalDate endDate, double cost, String scope, Vehicle vehicle, InsuranceCompany insuranceCompany) {
        super();
        try {
            setId();
            setStartDate(startDate);
            setEndDate(endDate);
            setCost(cost);
            setScope(scope);
            setVehicle(vehicle);
            setInsurer(insuranceCompany);
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
        checkIfDateIsNotNull(startDate, "Start date of vehicle insurance");
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        checkIfDateIsNotNull(endDate, "End date of vehicle insurance");
        if (endDate.isBefore(this.getStartDate())) {
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

    public InsuranceCompany getInsurer() {
        return insuranceCompany;
    }

    public void setInsurer(InsuranceCompany insuranceCompany) {

        if (this.insuranceCompany == null && insuranceCompany != null) {
            // new insurer - no insurer previously assigned
            this.insuranceCompany = insuranceCompany;

            if (insuranceCompany.getVehicleInsurances().contains(this)) {
                return;
            }
            insuranceCompany.addVehicleInsurance(this);

        } else if (this.insuranceCompany != null && insuranceCompany == null) {
            // removing the insurer
            InsuranceCompany tmp = this.insuranceCompany;
            this.insuranceCompany = null;
            tmp.removeVehicleInsurance(this);

        } else if (this.insuranceCompany != null && insuranceCompany != null) {
            // changing the insurer
            InsuranceCompany tmp = this.insuranceCompany;
            this.insuranceCompany = null;
            tmp.removeVehicleInsurance(this);

            this.insuranceCompany = null;
            insuranceCompany.addVehicleInsurance(this);
        }
    }

    public void removeCarInsurance() {
        this.insuranceCompany.removeVehicleInsurance(this);
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
                "\nInsurance company: " + insuranceCompany.getCompanyName();
    }
}
