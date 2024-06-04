package pjatk.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pjatk.finalproject.controller.MainWindowController;
import pjatk.finalproject.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ObjectPlus.FILE_NAME))) {
            ObjectPlus.readExtents(ois);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            List<Region> regions = ObjectPlus.getExtent(Region.class);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = loader.load();

            MainWindowController controller = loader.getController();
            controller.setRegions(regions);

            Scene scene = new Scene(root, 800, 800);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Loaded Data");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
