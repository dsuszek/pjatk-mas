package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import pjatk.finalproject.model.CompanyBranch;
import pjatk.finalproject.model.Region;
import pjatk.finalproject.model.Vehicle;
import pjatk.finalproject.model.Brand;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleParametersController {

    @FXML
    private ComboBox<String> brandComboBox;

    @FXML
    private ComboBox<String> modelComboBox;

    public void setRegionAndBranch(Region region, CompanyBranch branch) {

        // Get available cars based on selected region and branch
        List<Vehicle> availableVehicles = getAvailableVehicles(region, branch);

        // Populate brandComboBox with unique car brands from available cars
        brandComboBox.getItems().addAll(
                availableVehicles.stream()
                        .map(Vehicle::getBrand)
                        .map(Brand::getName)
                        .distinct()
                        .toList()
        );

        // Add listener to update modelComboBox based on selected brand
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
}
