package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Mobile4UController {

    // Pola dla zakładki Klienci
    @FXML private TextField clientFirstName;
    @FXML private TextField clientLastName;
    @FXML private DatePicker clientBirthDate;
    @FXML private DatePicker clientLicenseIssueDate;
    @FXML private DatePicker clientLicenseExpiryDate;

    // Pola dla zakładki Pracownicy
    @FXML private TextField employeeFirstName;
    @FXML private TextField employeeLastName;
    @FXML private DatePicker employeeBirthDate;
    @FXML private TextField mechanicSkills;
    @FXML private DatePicker mechanicBhpExpiryDate;
    @FXML private TextField officeSkills;
    @FXML private DatePicker officeEyeTestExpiryDate;
    @FXML private TextField salesAgentCommission;
    @FXML private TextArea marketingPortfolio;
    @FXML private TextArea marketingCampaigns;

    // Pola dla zakładki Pojazdy
    @FXML private TextField vehicleRegNumber;
    @FXML private TextField vehicleBrand;
    @FXML private TextField vehicleModel;
    @FXML private TextField vehicleEngineCapacity;
    @FXML private ComboBox<String> vehicleType;
    @FXML private TextField truckPayload;

    // Pola dla zakładki Wynajmy
    @FXML private TextField rentalId;
    @FXML private DatePicker rentalStartDate;
    @FXML private DatePicker rentalEndDate;
    @FXML private TextField rentalDistance;

    // Pola dla zakładki Oddziały
    @FXML private TextField branchId;
    @FXML private TextField branchName;
    @FXML private TextField branchAddress;

    // Metody obsługi akcji
    @FXML
    private void handleAddClient() {
        // Obsługa dodawania klienta
        String firstName = clientFirstName.getText();
        String lastName = clientLastName.getText();
        // Kontynuuj obsługę
        System.out.println("Dodano klienta: " + firstName + " " + lastName);
    }

    @FXML
    private void handleAddEmployee() {
        // Obsługa dodawania pracownika
        String firstName = employeeFirstName.getText();
        String lastName = employeeLastName.getText();
        // Kontynuuj obsługę
        System.out.println("Dodano pracownika: " + firstName + " " + lastName);
    }

    @FXML
    private void handleAddVehicle() {
        // Obsługa dodawania pojazdu
        String regNumber = vehicleRegNumber.getText();
        String brand = vehicleBrand.getText();
        // Kontynuuj obsługę
        System.out.println("Dodano pojazd: " + regNumber + " " + brand);
    }

    @FXML
    private void handleAddRental() {
        // Obsługa dodawania wynajmu
        String rentalId = this.rentalId.getText();
        // Kontynuuj obsługę
        System.out.println("Dodano wynajem: " + rentalId);
    }

    @FXML
    private void handleAddBranch() {
        // Obsługa dodawania oddziału
        String branchId = this.branchId.getText();
        String name = branchName.getText();
        // Kontynuuj obsługę
        System.out.println("Dodano oddział: " + branchId + " " + name);
    }
}
