package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import pjatk.finalproject.model.CompanyBranch;
import pjatk.finalproject.model.Customer;
import pjatk.finalproject.model.ObjectPlus;
import pjatk.finalproject.model.Region;

import java.io.IOException;
import java.util.List;

public class MainWindowController {

    @FXML
    private ImageView logoImageView;

    @FXML
    private ComboBox<Region> regionComboBox;
    @FXML
    private Label branchLabel;

    @FXML
    private ListView<CompanyBranch> companyBranchListView;
    private List<Region> regions;
    private Region selectedRegion;
    private CompanyBranch selectedBranch;
    private Customer selectedCustomer;

    public void setRegions(List<Region> regions) {
        this.regions = regions;
        regionComboBox.getItems().addAll(regions);
    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }

    @FXML
    private void handleRegionSelection() {
        selectedRegion = regionComboBox.getSelectionModel().getSelectedItem();
        if (selectedRegion != null) {
            companyBranchListView.getItems().clear();
            companyBranchListView.getItems().addAll(selectedRegion.getCompanyBranches());
            branchLabel.setVisible(true);
            companyBranchListView.setVisible(true);
            selectedBranch = null;
        } else {
            branchLabel.setVisible(false);
            companyBranchListView.setVisible(false);
        }
    }

    @FXML
    private void handleBranchSelection() {
        selectedBranch = companyBranchListView.getSelectionModel().getSelectedItem();
    }


    @FXML
    private void handleContinue() {

        if (selectedRegion == null || selectedBranch == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Missing");
            alert.setContentText("Please select both region and company branch.");
            alert.showAndWait();
            return;
        }

        try {
            selectedCustomer = ObjectPlus.getExtent(Customer.class).get(0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VehiclesParametersWindow.fxml"));
            Parent root = loader.load();

            VehicleParametersWindowController controller = loader.getController();
            controller.setRegionAndBranch(selectedRegion, selectedBranch);
            controller.setCustomer(selectedCustomer);

            Scene currentScene = regionComboBox.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            stage.setTitle("Rental Parameters");
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewPreviousRentals() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PreviousRentalsWindow.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Previous Rentals");
            stage.setScene(new Scene(root, 800, 1200));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        Image logo = new Image(getClass().getResourceAsStream("/images/logo_rent4u.png"));
        logoImageView.setImage(logo);
        logoImageView.setFitWidth(350);
        logoImageView.setPreserveRatio(true);

        regionComboBox.setOnAction(event -> handleRegionSelection());
        companyBranchListView.setOnMouseClicked(event -> handleBranchSelection());

        regionComboBox.setCellFactory(param -> new ListCell<Region>() {
            @Override
            protected void updateItem(Region item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });
        regionComboBox.setButtonCell(new ListCell<Region>() {
            @Override
            protected void updateItem(Region item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });

        companyBranchListView.setCellFactory(param -> new ListCell<CompanyBranch>() {
            @Override
            protected void updateItem(CompanyBranch item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });
    }
}
