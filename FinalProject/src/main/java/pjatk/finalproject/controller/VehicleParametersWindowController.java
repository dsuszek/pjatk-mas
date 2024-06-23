package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pjatk.finalproject.model.*;

import java.io.IOException;
import java.time.LocalDate;
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
    private CheckBox rentalDoorToDoorCheckBox;
    @FXML
    private ListView<Vehicle> vehicleListView;
    private List<Vehicle> availableVehicles = new ArrayList<>();
    private Region selectedRegion;
    private CompanyBranch selectedCompanyBranch;
    private Brand selectedBrand;
    private String selectedModel;
    private Vehicle selectedVehicle;
    private Customer selectedCustomer;
    private LocalDate selectedEndDate;

    public void setRegionAndBranch(Region region, CompanyBranch companyBranch) {
        selectedRegion = region;
        selectedCompanyBranch = companyBranch;

        availableVehicles = getAvailableVehicles(region, companyBranch);

        // Populate brandComboBox with unique car brands from available cars
        brandComboBox.getItems().addAll(
                availableVehicles.stream()
                        .map(Vehicle::getBrand)
                        .distinct()
                        .toList()
        );

        brandComboBox.setOnAction(event -> updateModelComboBox(availableVehicles, brandComboBox.getSelectionModel().getSelectedItem()));
    }

    public void setSelectedVehicleAndDate(Vehicle vehicle, LocalDate endDate) {
        this.selectedVehicle = vehicle;
        this.selectedEndDate = endDate;

        // Set the end date picker value
        endDatePicker.setValue(endDate);

        // Update the brand and model combo boxes
        selectedBrand = vehicle.getBrand();
        selectedModel = vehicle.getModel();
        brandComboBox.getSelectionModel().select(selectedBrand);
        updateModelComboBox(availableVehicles, selectedBrand);
        modelComboBox.getSelectionModel().select(selectedModel);

        // Select the vehicle in the list view
        vehicleListView.getSelectionModel().select(vehicle);
        handleSearch();
    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
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

    @FXML
    private void handleDisplayVehicleDetails() {
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

        StringBuilder details = new StringBuilder();

        availableVehicles.stream()
                .filter(vehicle -> vehicle.getBrand().equals(selectedBrand) && vehicle.getModel().equals(selectedModel))
                .forEach(vehicle -> {
                    if (vehicle instanceof Car) {
                        Car selectedCar = (Car) vehicle;
                        if (selectedCar.getCarTypes().contains(CarTypes.SPORT_CAR) && selectedCar.getSuspensionHeight() != null) {
                            details.append("Car registration number: " + vehicle.getVehicleRegistrationNumber() +
                                    "\nBrand: " + vehicle.getBrand().getName() +
                                    "\nModel: " + vehicle.getModel() +
                                    "\nEngine size: " + ((Car) vehicle).getEngineSize() +
                                    "\nCar types: " + ((Car) vehicle).getCarTypes() +
                                    "\nSuspension height: " + ((Car) vehicle).getSuspensionHeight() +
                                    "\nCompany branch: " + vehicle.getCompanyBranch().getName() +
                                    "\nPrice of rental per kilometer: " + vehicle.calculateRentalPricePerKilometer());
                        } else if (selectedCar.getCarTypes().contains(CarTypes.ELECTRIC_CAR) && selectedCar.getBatteryCapacity() != null) {
                            details.append("Car registration number: " + vehicle.getVehicleRegistrationNumber() +
                                    "\nBrand: " + vehicle.getBrand().getName() +
                                    "\nModel: " + vehicle.getModel() +
                                    "\nEngine size: " + ((Car) vehicle).getEngineSize() +
                                    "\nCar types: " + ((Car) vehicle).getCarTypes() +
                                    "\nBattery capacity: " + ((Car) vehicle).getBatteryCapacity() +
                                    "\nCompany branch: " + vehicle.getCompanyBranch().getName() +
                                    "\nPrice of rental per kilometer: " + vehicle.calculateRentalPricePerKilometer());
                        } else if (((Car) vehicle).getCarTypes().contains(CarTypes.PREMIUM_CAR) && (((Car) vehicle).getLuxuryDesignElements() != null)) {
                            details.append("Car registration number: " + vehicle.getVehicleRegistrationNumber() +
                                    "\nBrand: " + vehicle.getBrand().getName() +
                                    "\nModel: " + vehicle.getModel() +
                                    "\nEngine size: " + ((Car) vehicle).getEngineSize() +
                                    "\nCar types: " + ((Car) vehicle).getCarTypes() +
                                    "\nLuxury design elements: " + ((Car) vehicle).getLuxuryDesignElements() +
                                    "\nCompany branch: " + vehicle.getCompanyBranch().getName() +
                                    "\nPrice of rental per kilometer: " + vehicle.calculateRentalPricePerKilometer());
                        } else {
                            details.append("Car registration number:" + vehicle.getVehicleRegistrationNumber() +
                                    "\nBrand: " + vehicle.getBrand().getName() +
                                    "\nModel: " + vehicle.getModel() +
                                    "\nEngine size: " + ((Car) vehicle).getEngineSize() +
                                    "\nCar types: " + ((Car) vehicle).getCarTypes() +
                                    "\nCompany branch: " + vehicle.getCompanyBranch().getName() +
                                    "\nPrice of rental per kilometer: " + vehicle.calculateRentalPricePerKilometer());
                        }

                    } else if (vehicle instanceof Truck) {
                        Truck selectedTruck = (Truck) vehicle;
                        details.append("Truck registration number: ").append(selectedTruck.getVehicleRegistrationNumber());
                        details.append("\nMaximum Authorised Mass: ").append(selectedTruck.getMaximumAuthorisedMass());
                        details.append("\nPrice of rental per kilometer: ").append(selectedTruck.calculateRentalPricePerKilometer());
                    }
                    details.append("\n\n");
                });

        if (details.isEmpty()) {
            details.append("No vehicles found for the selected brand and model.");
        }

        showVehicleDetailsPopup(details.toString());
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
            controller.setCustomer(selectedCustomer);

            Scene currentScene = brandComboBox.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            stage.setTitle("Create A New Rental");
            stage.setScene(new Scene(root, 800, 800));
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

        selectedVehicle = vehicleListView.getSelectionModel().getSelectedItem();
        selectedEndDate = endDatePicker.getValue();


        if (rentalDoorToDoorCheckBox.isSelected()) {
            loadAddressWindow(selectedRegion, selectedCompanyBranch, selectedVehicle, selectedEndDate);
        } else {
            loadSummaryWindow(selectedRegion, selectedCompanyBranch, selectedVehicle, selectedEndDate);
        }
    }

    private boolean isInputValid() {
        if (brandComboBox.getSelectionModel().getSelectedItem() == null) {
            showAlert("Error", "Brand Missing", "Please select a brand.");
            return false;
        }

        if (modelComboBox.getSelectionModel().getSelectedItem() == null) {
            showAlert("Error", "Model Missing", "Please select a model.");
            return false;
        }

        if (vehicleListView.getSelectionModel().getSelectedItem() == null) {
            showAlert("Error", "Vehicle Missing", "Please select a vehicle.");
            return false;
        }

        if (endDatePicker.getValue() == null) {
            showAlert("Error", "End Date Missing", "Please select an end date.");
            return false;
        }

        if (endDatePicker.getValue().isBefore(LocalDate.now())) {
            showAlert("Error", "Invalid End Date", "The end date cannot be in the past.");
            return false;
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

    private void loadAddressWindow(Region region, CompanyBranch companyBranch, Vehicle vehicle, LocalDate endDate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddressWindow.fxml"));
            Parent root = loader.load();

            AddressWindowController controller = loader.getController();
            controller.setRegion(region);
            controller.setCustomer(selectedCustomer);
            controller.setCompanyBranch(companyBranch);
            controller.setVehicle(vehicle);
            controller.setEndDate(endDate);

            Scene currentScene = brandComboBox.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            stage.setMinHeight(800);
            stage.setMinWidth(800);
            stage.setTitle("Delivery Address Details");
            stage.setScene(new Scene(root, 800, 1000));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSummaryWindow(Region region, CompanyBranch companyBranch, Vehicle vehicle, LocalDate endDate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SummaryWindow.fxml"));
            Parent root = loader.load();

            SummaryWindowController controller = loader.getController();
            controller.setRegion(region);
            controller.setCustomer(selectedCustomer);
            controller.setCompanyBranch(companyBranch);
            controller.setVehicle(vehicle);
            controller.setEndDate(endDate);

            Scene currentScene = brandComboBox.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            stage.setMinHeight(800);
            stage.setMinWidth(800);
            stage.setTitle("New Rental Summary");
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showVehicleDetailsPopup(String details) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VehicleDetailsPopup.fxml"));
            Parent root = loader.load();

            VehicleDetailsPopupController controller = loader.getController();
            controller.setVehicleDetails(details);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Vehicle Details");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
