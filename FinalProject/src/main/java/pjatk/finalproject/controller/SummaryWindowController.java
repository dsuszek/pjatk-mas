package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pjatk.finalproject.model.Region;
import pjatk.finalproject.model.CompanyBranch;
import pjatk.finalproject.model.Vehicle;

import java.time.LocalDate;

public class SummaryWindowController {
    @FXML
    private Label regionLabel;
    @FXML
    private Label companyBranchLabel;
    @FXML
    private Label vehicleLabel;
    @FXML
    private Label endDateLabel;

    public void setRegion(Region region) {
        regionLabel.setText(region.getName());
    }

    public void setCompanyBranch(CompanyBranch companyBranch) {
        companyBranchLabel.setText(companyBranch.getName());
    }

    public void setVehicle(Vehicle vehicle) {
        vehicleLabel.setText(vehicle.getBrand().getName() + " " + vehicle.getModel());
    }

    public void setEndDate(LocalDate endDate) {
        endDateLabel.setText(endDate.toString());
    }
}
