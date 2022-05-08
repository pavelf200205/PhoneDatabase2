package ua.pavelf200205.phonedatabase;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PhoneApplication extends Application {
    private ObservableList<Phone> phoneData = FXCollections.observableArrayList();

    public ObservableList<Phone> getPhoneData() {
        return phoneData;
    }

    public PhoneApplication() {

        phoneData.add(new Phone("Fadieiev", "Pavlo", "Vyacheslavovich", 1));
        phoneData.add(new Phone("Fadieiev", "Artyom", "Vyacheslavovich", 1));
        phoneData.add(new Phone("Fadieiev", "Pavlo", "Vyacheslavovich", 1));
        phoneData.add(new Phone("Fadieiev", "Pavlo", "Vyacheslavovich", 1));
        phoneData.add(new Phone("Fadieiev", "Pavlo", "Vyacheslavovich", 1));
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhoneApplication.class.getResource("PhoneOverview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PhoneOverviewController controller = fxmlLoader.getController();
        controller.setMainApp(this);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
