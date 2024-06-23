package pjatk.finalproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pjatk.finalproject.model.ObjectPlus;
import pjatk.finalproject.model.RentalDoorToDoor;
import pjatk.finalproject.model.RentalOnPremises;

import java.util.List;

public class PreviousRentalsWindowController {
    @FXML
    private ListView<RentalDoorToDoor> rentalDoorToDoorListView;

    @FXML
    private ListView<RentalOnPremises> rentalOnPremisesListView;

    public void initialize() throws ClassNotFoundException {
        List<RentalDoorToDoor> rentalDoorToDoorList = getPreviousRentalDoorToDoor();
        List<RentalOnPremises> rentalOnPremisesList = getPreviousRentalOnPremises();

        rentalDoorToDoorListView.getItems().addAll(rentalDoorToDoorList);
        rentalOnPremisesListView.getItems().addAll(rentalOnPremisesList);
    }

    private List<RentalDoorToDoor> getPreviousRentalDoorToDoor() throws ClassNotFoundException {
        List<RentalDoorToDoor> rentalsDoorToDoor = ObjectPlus.getExtent(RentalDoorToDoor.class);

        return rentalsDoorToDoor;
    }

    private List<RentalOnPremises> getPreviousRentalOnPremises() throws ClassNotFoundException {
        List<RentalOnPremises> rentalOnPremises = ObjectPlus.getExtent(RentalOnPremises.class);

        return rentalOnPremises;
    }
}
