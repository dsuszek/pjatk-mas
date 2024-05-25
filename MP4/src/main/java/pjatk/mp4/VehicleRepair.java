package pjatk.mp4;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import static pjatk.mp4.Utils.*;

public class VehicleRepair implements Serializable {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;
    private String damageRepaired;
    private Vehicle vehicle;
    private Mechanic mechanic;

    public VehicleRepair(LocalDate startDate, LocalDate endDate, double cost, String damageRepaired, Vehicle vehicle, Mechanic mechanic) {
        setId();
        setStartDate(startDate);
        setEndDate(endDate);
        setCost(cost);
        setDamageRepaired(damageRepaired);
        setVehicle(vehicle);
        setMechanic(mechanic);
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
        checkIfDateIsNotNull(startDate, "Start date of vehicle repair");
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        checkIfDateIsNotNull(endDate, "End date of vehicle repair");
        if (endDate.isBefore(this.getStartDate())) {
            throw new IllegalArgumentException("End date of vehicle repair cannot be before start date.");
        }
        this.endDate = endDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        checkCorrectnessOfNumericalValueGreaterThanZero(cost, "Cost of vehicle repair");
        this.cost = cost;
    }

    public String getDamageRepaired() {
        return damageRepaired;
    }

    public void setDamageRepaired(String damageRepaired) {
        checkCorrectnessOfStringAttribute(damageRepaired, "Damage repaired");
        this.damageRepaired = damageRepaired;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {

        if (this.vehicle == null && vehicle != null) {
            // new vehicle - no vehicle previously assigned
            this.vehicle = vehicle;

            if (vehicle.getVehicleRepairs().contains(this)) {
                return;
            }
            vehicle.addVehicleRepair(this);

        } else if (this.vehicle != null && vehicle == null) {
            // removing the vehicle
            Vehicle tmp = this.vehicle;
            this.vehicle = null;
            tmp.removeVehicleRepair(this);

        } else if (this.vehicle != null && vehicle != null) {
            // changing the vehicle
            Vehicle tmp = this.vehicle;
            this.vehicle = null;
            tmp.removeVehicleRepair(this);

            this.vehicle = vehicle;
            vehicle.addVehicleRepair(this);
        }
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        if (this.mechanic == null && mechanic != null) {
            // new mechanic - no mechanic previously assigned
            this.mechanic = mechanic;

            if (mechanic.getVehicleRepairs().contains(this)) {
                return;
            }
            mechanic.addVehicleRepair(this);

        } else if (this.mechanic != null && mechanic == null) {
            // removing the mechanic
            Mechanic tmp = this.mechanic;
            this.mechanic = null;
            tmp.removeVehicleRepair(this);

        } else if (this.mechanic != null && mechanic != null) {
            // changing the mechanic
            Mechanic tmp = this.mechanic;
            this.mechanic = null;
            tmp.removeVehicleRepair(this);

            this.mechanic = mechanic;
            mechanic.addVehicleRepair(this);
        }
    }

    @Override
    public String toString() {
        return "Vehicle repair ID: " + id +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate +
                "\nCost: " + cost +
                "\nDamage repaired: " + damageRepaired +
                "\nVehicle included: \n" + vehicle +
                "\nMechanic included: \n" + mechanic;
    }
}
