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

    private Address address;
    private Stage dialogStage;
    private boolean submitClicked = false;

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isSubmitClicked() {
        return submitClicked;
    }

    public Address getAddress() {
        return address;
    }

    @FXML
    private void handleSubmit() {
        if (isInputValid()) {
            address = new Address(
                    streetNameField.getText(),
                    Short.parseShort(streetNumberField.getText()),
                    Short.parseShort(apartmentNumberField.getText()),
                    cityField.getText(),
                    postalCodeField.getText()
            );
            submitClicked = true;
            dialogStage.close();
        }
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
            currentScene.setRoot(root);
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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SummaryWithAddressWindow.fxml"));
            Parent root = loader.load();

            SummaryWithAddressWindowController controller = loader.getController();
            controller.setStreetName(streetNameField.getText());
            controller.setStreetNumber(streetNumberField.getText());
            controller.setApartmentNumber(apartmentNumberField.getText());
            controller.setCity(cityField.getText());
            controller.setPostalCode(postalCodeField.getText());

            Scene currentScene = streetNameField.getScene();
            currentScene.setRoot(root);
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

}
