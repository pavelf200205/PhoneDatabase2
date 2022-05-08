package ua.pavelf200205.phonedatabase;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PhoneOverviewController {
    @FXML
    TableView<Phone> phoneTable;
    @FXML
    TableColumn<Phone, Integer> idColumn;
    @FXML
    TableColumn<Phone, String> lastNameColumn;
    @FXML
    TableColumn<Phone, String> firstNameColumn;
    @FXML
    TableColumn<Phone, String> patronymicColumn;
    @FXML
    TableColumn<Phone, Integer> accountIDColumn;

    public PhoneOverviewController() {
    }

    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        patronymicColumn.setCellValueFactory(cellData -> cellData.getValue().patronymicProperty());
    }

    public void setMainApp(PhoneApplication mainApp) {
//        this.phoneApplication = phoneApplication;

        // Добавление в таблицу данных из наблюдаемого списка
        phoneTable.setItems(mainApp.getPhoneData());
    }
}
