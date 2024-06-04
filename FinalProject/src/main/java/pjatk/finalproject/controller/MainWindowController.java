package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import pjatk.finalproject.model.CompanyBranch;
import pjatk.finalproject.model.Region;

import java.util.List;

public class MainWindowController {

    @FXML
    private ComboBox<String> regionComboBox;

    @FXML
    private ComboBox<String> companyBranchComboBox;

    @FXML
    private ImageView logoImageView;

    private List<Region> regions;
    private Region selectedRegion;
    private CompanyBranch selectedBranch;

    public void setRegions(List<Region> regions) {
        this.regions = regions;
        for (Region region : this.regions) {
            regionComboBox.getItems().add(region.getName());
        }
        companyBranchComboBox.setDisable(true); // Initially disable the companyBranchComboBox

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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VehiclesParameters.fxml"));
            Parent root = loader.load();

            VehicleParametersController controller = loader.getController();
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
    }
}
