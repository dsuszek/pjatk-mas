package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pjatk.finalproject.model.CompanyBranch;
import pjatk.finalproject.model.Region;
import pjatk.finalproject.model.Vehicle;

import java.time.LocalDate;

public class SummaryWithAddressWindowController {
    @FXML
    private Label regionLabel;
    @FXML
    private Label companyBranchLabel;
    @FXML
    private Label vehicleLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label streetNameLabel;
    @FXML
    private Label streetNumberLabel;
    @FXML
    private Label apartmentNumberLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalCodeLabel;

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

    public void setStreetName(String streetName) {
        streetNameLabel.setText(streetName);
    }

    public void setStreetNumber(String streetNumber) {
        streetNumberLabel.setText(streetNumber);
    }

    public void setApartmentNumber(String apartmentNumber) {
        apartmentNumberLabel.setText(apartmentNumber);
    }

    public void setCity(String city) {
        cityLabel.setText(city);
    }

    public void setPostalCode(String postalCode) {
        postalCodeLabel.setText(postalCode);
    }
}
