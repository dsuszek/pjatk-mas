package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pjatk.finalproject.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class SummaryWindowController {
    @FXML
    private Label regionLabel;
    @FXML
    private Label companyBranchLabel;
    @FXML
    private Label vehicleLabel;
    @FXML
    private Label endDateLabel;
    private Region selectedRegion;
    private CompanyBranch selectedCompanyBranch;
    private Vehicle selectedVehicle;
    private Customer selectedCustomer;
    private LocalDate selectedEndDate;


    public void setRegion(Region region) {
        selectedRegion = region;
        regionLabel.setText(region.getName());
    }

    public void setCompanyBranch(CompanyBranch companyBranch) {
        selectedCompanyBranch = companyBranch;
        companyBranchLabel.setText(companyBranch.getName());
    }

    public void setVehicle(Vehicle vehicle) {
        selectedVehicle = vehicle;
        vehicleLabel.setText(vehicle.getBrand().getName() + " " + vehicle.getModel());
    }

    public void setEndDate(LocalDate endDate) {
        selectedEndDate = endDate;
        endDateLabel.setText(endDate.toString());
    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }

    @FXML
    private void handleCreateRental() {
        LocalDate startDate = LocalDate.now();

        RentalLengthTypes rentalLengthType;
        if (selectedEndDate.isBefore(startDate.plusDays(3))) {
            rentalLengthType = RentalLengthTypes.SHORT_TERM_RENTAL;
        } else {
            rentalLengthType = RentalLengthTypes.LONG_TERM_RENTAL;
        }

        RentalOnPremises rental = new RentalOnPremises(
                startDate,
                selectedEndDate,
                null,
                selectedVehicle,
                selectedCustomer,
                rentalLengthType,
                false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rental Created");
        alert.setHeaderText("Rental has been successfully created.");
        alert.setContentText("Rental Details:\n" +
                "Start Date: " + rental.getStartDate() + "\n" +
                "End Date: " + rental.getEndDate() + "\n" +
                "Vehicle: " + rental.getVehicle() + "\n" +
                "Customer: " + rental.getCustomer() + "\n" +
                "Rental Length Type: " + rental.getRentalLengthType());
        alert.showAndWait().ifPresent(response -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pjatk/finalproject/MainWindow.fxml"));
                Parent root = loader.load();

                List<Region> regions = ObjectPlus.getExtent(Region.class);
                MainWindowController controller = loader.getController();
                controller.setRegions(regions);

                Scene currentScene = regionLabel.getScene();
                Stage stage = (Stage) currentScene.getWindow();
                stage.setTitle("Create A New Rental");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            stage.setTitle("Rental Parameters");
            stage.setScene(new Scene(root,800, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
