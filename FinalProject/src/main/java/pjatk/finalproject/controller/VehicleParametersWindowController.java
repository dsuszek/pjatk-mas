package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import pjatk.finalproject.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleParametersWindowController {

    @FXML
    private ComboBox<Brand> brandComboBox;
    @FXML
    private ComboBox<String> modelComboBox;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Label vehicleDetailsLabel;
    @FXML
    private CheckBox rentalDoorToDoorCheckBox;
    @FXML
    private ListView<Vehicle> vehicleListView;
    private List<Vehicle> availableVehicles = new ArrayList<>();
    private Brand selectedBrand;
    private String selectedModel;

    public void setRegionAndBranch(Region region, CompanyBranch branch) {

        availableVehicles = getAvailableVehicles(region, branch);

        // Populate brandComboBox with unique car brands from available cars
        brandComboBox.getItems().addAll(
                availableVehicles.stream()
                        .map(Vehicle::getBrand)
                        .distinct()
                        .toList()
        );

        brandComboBox.setOnAction(event -> updateModelComboBox(availableVehicles, brandComboBox.getSelectionModel().getSelectedItem()));
    }

    private void updateModelComboBox(List<Vehicle> availableVehicles, Brand selectedBrand) {
        modelComboBox.getItems().clear();
        if (selectedBrand != null) {
            modelComboBox.getItems().addAll(
                    availableVehicles.stream()
                            .filter(vehicle -> vehicle.getBrand().equals(selectedBrand))
                            .map(Vehicle::getModel)
                            .distinct()
                            .toList()
            );
        }
    }

    private List<Vehicle> getAvailableVehicles(Region region, CompanyBranch branch) {
        return region.getCompanyBranches().stream()
                .filter(b -> b.equals(branch))
                .flatMap(b -> b.getVehicles().stream())
                .collect(Collectors.toList());
    }

    @FXML
    private void handleDisplayVehicleDetails() {
        selectedBrand = brandComboBox.getSelectionModel().getSelectedItem();
        selectedModel = modelComboBox.getSelectionModel().getSelectedItem().toString();

        if (selectedBrand == null || selectedModel == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Missing");
            alert.setContentText("Please select both brand and model.");
            alert.showAndWait();
            return;
        }

        StringBuilder details = new StringBuilder();

        availableVehicles.stream()
                .filter(vehicle -> vehicle.getBrand().equals(selectedBrand) && vehicle.getModel().equals(selectedModel))
                .forEach(vehicle -> {
                    details.append("Registration Number: ").append(vehicle.getVehicleRegistrationNumber());
                    if (vehicle instanceof Car) {
                        Car car = (Car) vehicle;
                        details.append("\nEngine Size: ").append(car.getEngineSize());
                        details.append("\nCar Types: ").append(car.getCarTypes());
                    } else if (vehicle instanceof Truck) {
                        Truck truck = (Truck) vehicle;
                        details.append("\nMaximum Authorised Mass: ").append(truck.getMaximumAuthorisedMass());
                    }
                    details.append("\n\n");
                });

        if (details.isEmpty()) {
            details.append("No vehicles found for the selected brand and model.");
        }

        vehicleDetailsLabel.setText(details.toString());
    }


    @FXML
    private void handleSearch() {
        selectedBrand = brandComboBox.getSelectionModel().getSelectedItem();
        selectedModel = modelComboBox.getSelectionModel().getSelectedItem();

        if (selectedBrand == null || selectedModel == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Missing");
            alert.setContentText("Please select both brand and model.");
            alert.showAndWait();
            return;
        }

        List<Vehicle> filteredVehicles = availableVehicles.stream()
                .filter(vehicle -> vehicle.getBrand().equals(selectedBrand) && vehicle.getModel().equals(selectedModel))
                .toList();

        vehicleListView.getItems().clear();
        vehicleListView.getItems().addAll(filteredVehicles);
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

    @FXML
    private void handleContinue() {
        if (endDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("End Date Missing");
            alert.setContentText("Please select an end date for the rental.");
            alert.showAndWait();
        }

        if (rentalDoorToDoorCheckBox.isSelected()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddressWindow.fxml"));
                Parent root = loader.load();

                AddressWindowController controller = loader.getController();

                Scene currentScene = brandComboBox.getScene();
                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        brandComboBox.setCellFactory(param -> new ListCell<Brand>() {
            @Override
            protected void updateItem(Brand item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });
        brandComboBox.setButtonCell(new ListCell<Brand>() {
            @Override
            protected void updateItem(Brand item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });

        modelComboBox.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
            }
        });
        modelComboBox.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
            }
        });
    }
}
