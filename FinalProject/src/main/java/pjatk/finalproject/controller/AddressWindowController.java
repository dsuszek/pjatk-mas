package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pjatk.finalproject.model.Address;
import pjatk.finalproject.model.ObjectPlus;
import pjatk.finalproject.model.Region;

import java.io.IOException;
import java.util.List;

public class AddressWindowController {

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

    private boolean isInputValid() {
        // Add validation logic if necessary
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
            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isDataMissing(TextField textField, String headerText, String contextText) {
        if (textField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(headerText);
            alert.setContentText(contextText);
            alert.showAndWait();
            return true;
        }
        return false;
    }

    @FXML
    private void handleContinue() {
        if (isDataMissing(streetNameField, "Street Name Missing", "Please fill in the information about street name.")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddressWindow.fxml"));
                Parent root = loader.load();

                AddressWindowController controller = loader.getController();

                Scene currentScene = streetNameField.getScene();
                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SummaryWindow.fxml"));
                Parent root = loader.load();

                SummaryWindowController controller = loader.getController();

                Scene currentScene = streetNameField.getScene();
                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
