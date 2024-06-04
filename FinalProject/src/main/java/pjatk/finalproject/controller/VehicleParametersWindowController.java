package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pjatk.finalproject.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleParametersWindowController {

    @FXML
    private ComboBox<String> brandComboBox;
    @FXML
    private ComboBox<String> modelComboBox;
    @FXML
    private Label vehicleDetailsLabel;
    private List<Vehicle> availableVehicles = new ArrayList<>();

    public void setRegionAndBranch(Region region, CompanyBranch branch) {

        availableVehicles = getAvailableVehicles(region, branch);

        // Populate brandComboBox with unique car brands from available cars
        brandComboBox.getItems().addAll(
                availableVehicles.stream()
                        .map(Vehicle::getBrand)
                        .map(Brand::getName)
                        .distinct()
                        .toList()
        );

        brandComboBox.setOnAction(event -> updateModelComboBox(availableVehicles, brandComboBox.getSelectionModel().getSelectedItem()));
    }

    private void updateModelComboBox(List<Vehicle> availableVehicles, String selectedBrand) {
        modelComboBox.getItems().clear();
        if (selectedBrand != null) {
            modelComboBox.getItems().addAll(
                    availableVehicles.stream()
                            .filter(car -> car.getBrand().getName().equals(selectedBrand))
                            .map(Vehicle::getModel)
                            .distinct()
                            .toList()
            );
        }
    }

    private List<Vehicle> getAvailableVehicles(Region region, CompanyBranch branch) {
        // This method should return a list of available cars based on the region and branch.
        // Replace with actual logic to fetch available cars.
        return region.getCompanyBranches().stream()
                .filter(b -> b.equals(branch))
                .flatMap(b -> b.getVehicles().stream())
                .collect(Collectors.toList());
    }

    @FXML
    private void handleDisplayVehicleDetails() {
        String selectedBrand = brandComboBox.getSelectionModel().getSelectedItem();
        String selectedModel = modelComboBox.getSelectionModel().getSelectedItem();

        if (selectedBrand == null || selectedModel == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Missing");
            alert.setContentText("Please select both brand and model.");
            alert.showAndWait();
            return;
        }

        vehicleDetailsLabel.setText("");
        availableVehicles.stream()
                .filter(vehicle -> vehicle.getBrand().getName().equals(selectedBrand) && vehicle.getModel().equals(selectedModel))
                .findAny()
                .ifPresent(vehicle -> {
                    StringBuilder details = new StringBuilder("Registration Number: " + vehicle.getVehicleRegistrationNumber());
                    if (vehicle instanceof Car) {
                        Car car = (Car) vehicle;
                        details.append("\nEngine Size: ").append(car.getEngineSize());
                        details.append("\nCar Types: ").append(car.getCarTypes());
                    } else if (vehicle instanceof Truck) {
                        Truck truck = (Truck) vehicle;
                        details.append("\nMaximum Authorised Mass: ").append(truck.getMaximumAuthorisedMass());
                    }
                    vehicleDetailsLabel.setText(details.toString());
                });
    }

    @FXML
    private void handleBackToMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pjatk/finalproject/MainWindow.fxml"));
            Parent root = loader.load();

            List<Region> regions = ObjectPlus.getExtent(Region.class);
            MainWindowController controller = loader.getController();
            controller.setRegions(regions);

            Scene currentScene = brandComboBox.getScene();
            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
