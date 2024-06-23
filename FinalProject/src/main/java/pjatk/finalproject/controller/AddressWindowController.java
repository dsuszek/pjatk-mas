package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pjatk.finalproject.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class AddressWindowController {

    @FXML
    private Label regionLabel;
    @FXML
    private Label companyBranchLabel;
    @FXML
    private Label vehicleLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private TextField streetNameField;
    @FXML
    private TextField streetNumberField;
    @FXML
    private TextField apartmentNumberField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField postalCodeField;
    private Region selectedRegion;
    private CompanyBranch selectedCompanyBranch;
    private Vehicle selectedVehicle;
    private Customer selectedCustomer;
    private LocalDate selectedEndDate;
    public void setRegion(Region region) {
        this.selectedRegion = region;
        regionLabel.setText(region.getName());
    }

    public void setCompanyBranch(CompanyBranch companyBranch) {
        this.selectedCompanyBranch = companyBranch;
        companyBranchLabel.setText(companyBranch.getName());
    }

    public void setVehicle(Vehicle vehicle) {
        this.selectedVehicle = vehicle;
        vehicleLabel.setText(vehicle.getBrand().getName() + " " + vehicle.getModel());
    }

    public void setEndDate(LocalDate endDate) {
        this.selectedEndDate = endDate;
        endDateLabel.setText(endDate.toString());
    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }

    private boolean isAddressInputValid() {
        String streetName = streetNameField.getText();
        String streetNumber = streetNumberField.getText();
        String apartmentNumber = apartmentNumberField.getText();
        String cityName = cityField.getText();
        String postalCode = postalCodeField.getText();

        if (!Pattern.matches("[a-zA-Z .']{2,}", streetName)) {
            showAlert("Error", "Invalid Street Name", "Street name should only consist of letters with a minimum of 2 letters.");
            return false;
        }

        if (!Pattern.matches("\\d+", streetNumber)) {
            showAlert("Error", "Invalid Street Number", "Street number should only consist of digits.");
            return false;
        }

        if (!Pattern.matches("\\d*", apartmentNumber)) {
            showAlert("Error", "Invalid Apartment Number", "Apartment number should only consist of digits.");
            return false;
        }

        if (!Pattern.matches("[a-zA-Z]{2,}", cityName)) {
            showAlert("Error", "Invalid City Name", "City name should only consist of letters.");
            return false;
        }

        if (!Pattern.matches("\\d{2}-\\d{3}", postalCode)) {
            showAlert("Error", "Invalid Postal Code", "Postal code should only consist of digits and be in the format XX-XXX.");
            return false;
        }

        return true;
    }

    @FXML
    private void handleBackToMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pjatk/finalproject/MainWindow.fxml"));
            Parent root = loader.load();

            List<Region> regions = ObjectPlus.getExtent(Region.class);
            MainWindowController controller = loader.getController();
            controller.setRegions(regions);

            Scene currentScene = streetNameField.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            stage.setTitle("Create A New Rental");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleContinue() {
        if (!isInputValid()) {
            return;
        }

        if (!isAddressInputValid()) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SummaryWithAddressWindow.fxml"));
            Parent root = loader.load();

            SummaryWithAddressWindowController controller = loader.getController();
            controller.setRegion(selectedRegion);
            controller.setCompanyBranch(selectedCompanyBranch);
            controller.setVehicle(selectedVehicle);
            controller.setCustomer(selectedCustomer);
            controller.setEndDate(selectedEndDate);

            controller.setStreetName(streetNameField.getText());
            controller.setStreetNumber(streetNumberField.getText());
            controller.setApartmentNumber(apartmentNumberField.getText());
            controller.setCity(cityField.getText());
            controller.setPostalCode(postalCodeField.getText());

            Scene currentScene = streetNameField.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.setMinHeight(800);
            currentStage.setMinWidth(800);
            currentStage.setTitle("New Rental Summary");
            currentStage.setScene(new Scene(root, 800, 1000));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isInputValid() {
        TextField[] fields = {streetNameField, streetNumberField, apartmentNumberField, cityField, postalCodeField};
        String[] fieldNames = {"Street Name", "Street Number", "Apartment Number", "City", "Postal Code"};

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getText() == null || fields[i].getText().isEmpty()) {
                showAlert("Error", fieldNames[i] + " Missing", "Please enter a value for " + fieldNames[i] + ".");
                return false;
            }
        }
        return true;
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleCancel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VehiclesParametersWindow.fxml"));
            Parent root = loader.load();

            VehicleParametersWindowController controller = loader.getController();
            controller.setRegionAndBranch(selectedRegion, selectedCompanyBranch);
            controller.setSelectedVehicleAndDate(selectedVehicle, selectedEndDate);

            Scene currentScene = regionLabel.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            stage.setMinHeight(800);
            stage.setMinWidth(800);
            stage.setTitle("Rental Parameters");
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
