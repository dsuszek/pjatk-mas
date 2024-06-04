package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import pjatk.finalproject.model.CompanyBranch;
import pjatk.finalproject.model.Region;

import java.util.List;

public class MainWindowController {

    @FXML
    private ImageView logoImageView;

    @FXML
    private ComboBox<String> regionComboBox;

    @FXML
    private ComboBox<String> companyBranchComboBox;
    private List<Region> regions;
    private Region selectedRegion;
    private CompanyBranch selectedBranch;

    public void setRegions(List<Region> regions) {
        this.regions = regions;
        for (Region region : this.regions) {
            regionComboBox.getItems().add(region.getName());
        }
        companyBranchComboBox.setDisable(true);

        regionComboBox.setOnAction(event -> {
            String selectedRegionName = regionComboBox.getSelectionModel().getSelectedItem();
            selectedRegion = regions.stream().filter(r -> r.getName().equals(selectedRegionName)).findFirst().orElse(null);
            updateCompanyBranchComboBox(selectedRegion);
        });
    }

    private void updateCompanyBranchComboBox(Region region) {
        companyBranchComboBox.getItems().clear();
        if (region != null) {
            for (CompanyBranch branch : region.getCompanyBranches()) {
                companyBranchComboBox.getItems().add(branch.getName());
            }
            companyBranchComboBox.setDisable(false);

            companyBranchComboBox.setOnAction(event -> {
                String selectedBranchName = companyBranchComboBox.getSelectionModel().getSelectedItem();
                selectedBranch = region.getCompanyBranches().stream().filter(b -> b.getName().equals(selectedBranchName)).findFirst().orElse(null);
            });
        }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VehiclesParametersWindow.fxml"));
            Parent root = loader.load();

            VehicleParametersWindowController controller = loader.getController();
            controller.setRegionAndBranch(selectedRegion, selectedBranch);

            Scene currentScene = regionComboBox.getScene();
            currentScene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        Image logo = new Image(getClass().getResourceAsStream("/images/logo_rent4u.png"));
        logoImageView.setImage(logo);
        logoImageView.setFitHeight(350);
        logoImageView.setPreserveRatio(true);
    }
}
