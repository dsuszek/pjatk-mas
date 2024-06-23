package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VehicleDetailsPopupController {

    @FXML
    private Label vehicleDetailsLabel;

    public void setVehicleDetails(String details) {
        vehicleDetailsLabel.setText(details);
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) vehicleDetailsLabel.getScene().getWindow();
        stage.close();
    }
}
