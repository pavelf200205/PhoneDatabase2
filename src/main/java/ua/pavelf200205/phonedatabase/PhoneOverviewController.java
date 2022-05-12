package ua.pavelf200205.phonedatabase;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class PhoneOverviewController {
    private Logic logic;
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
    @FXML
    TableColumn<Phone, String> cityColumn;
    @FXML
    TableColumn<Phone, Integer> totalCallsTime;
    @FXML
    TableColumn<Phone, Integer> domesticCallsTimeColumn;
    @FXML
    TableColumn<Phone, Integer> intercityCallsTimeColumn;
    @FXML
    Spinner<Integer> domesticCallsTimeSpinner;
    @FXML
    Spinner<Integer> IDRangeStartSpinner;
    @FXML
    Spinner<Integer> IDRangeStopSpinner;
    @FXML
    RadioButton domesticCallsFilterButton;
    @FXML
    RadioButton IDRangeFilterButton;

    private PhoneApplication mainApp;

    @FXML
    private void initialize() {

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        patronymicColumn.setCellValueFactory(cellData -> cellData.getValue().patronymicProperty());
        accountIDColumn.setCellValueFactory(cellData -> cellData.getValue().accountIDProperty().asObject());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        totalCallsTime.setCellValueFactory(cellData -> cellData.getValue().domesticCallsTimeProperty()
                .add(cellData.getValue().interCityCallsTimeProperty().getValue()).asObject());
        domesticCallsTimeColumn.setCellValueFactory(cellData -> cellData.getValue().domesticCallsTimeProperty().asObject());
        intercityCallsTimeColumn.setCellValueFactory(cellData -> cellData.getValue().interCityCallsTimeProperty().asObject());

        phoneTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        logic = new Logic();

        domesticCallsTimeSpinner.getValueFactory().valueProperty().addListener((observableValue, o, t1) -> {
            if(domesticCallsFilterButton.isSelected())
                showDomesticCallsGreaterThan();});

        ChangeListener<Integer> forIDFilter = (observableValue, o, t1) -> {
            if(IDRangeFilterButton.isSelected())
                showByAccountIDRange();
        };
        IDRangeStartSpinner.getValueFactory().valueProperty().addListener(forIDFilter);
        IDRangeStopSpinner.getValueFactory().valueProperty().addListener(forIDFilter);
    }

    @FXML
    protected void onDeleteButtonCLick() {
        ObservableList<Phone> selectedItems = phoneTable.getSelectionModel().getSelectedItems();
        phoneTable.getItems().removeAll(selectedItems);
    }

    @FXML
    protected void onAddButtonClick() {
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("AddPhone.fxml"));
        try {
            fxmlloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddPhoneController addPhoneController = fxmlloader.getController();
        Parent root = fxmlloader.getRoot();
        Stage addPhoneWindow = new Stage();
        addPhoneWindow.setResizable(false);
        Scene AddPhone = new Scene(root);
        addPhoneWindow.setScene(AddPhone);
        addPhoneWindow.setTitle("Додати абонента");

        addPhoneWindow.showAndWait();

        if(addPhoneController.isPhone()) {
            if(logic.checkIfIDUnique(phoneTable.getItems(),
                    addPhoneController.getPhone().getAccountID())) {
                phoneTable.getItems().add(addPhoneController.getPhone());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка");
                alert.setHeaderText("Неможливо додати запис:");
                alert.setContentText("запис з таким номером рахунку вже існує");
                alert.showAndWait();
            }
        }
    }

    @FXML
    protected void incDomesticCallsTime() {
        if(!phoneTable.getSelectionModel().isEmpty()) {
            Phone selected = phoneTable.getSelectionModel().getSelectedItem();
            selected.updateDomesticCallsTime(1);
        }
    }

    @FXML
    protected void incIntercityCallsTime() {
        if(!phoneTable.getSelectionModel().isEmpty()) {
            Phone selected = phoneTable.getSelectionModel().getSelectedItem();
            selected.updateIntercityCallsTime(1);
        }
    }

    @FXML
    protected void onShowCitiesButtonClick() {
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("CityListView.fxml"));
        try {
            fxmlloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CityListViewController cityListViewController = fxmlloader.getController();
        cityListViewController.setCityMap(logic.generateCitiesMap(mainApp.getPhoneData()));
        Parent root = fxmlloader.getRoot();
        Stage cityListWindow = new Stage();
        cityListWindow.setResizable(false);
        Scene AddPhone = new Scene(root);
        cityListWindow.setScene(AddPhone);
        cityListWindow.setTitle("Список міст");

        cityListWindow.showAndWait();
    }

    @FXML
    protected void showAllPhones() {
        phoneTable.setItems(mainApp.getPhoneData());
    }

    @FXML
    protected void showDomesticCallsGreaterThan() {
        int time = domesticCallsTimeSpinner.getValue();
        phoneTable.setItems(logic.findDomesticCallsGreaterThan(mainApp.getPhoneData(), time));
    }

    @FXML
    protected void showIfIntercityNonZero() {
        phoneTable.setItems(logic.findIntercityNonZero(mainApp.getPhoneData()));
    }

    @FXML
    protected void showByAccountIDRange() {
        int startID = IDRangeStartSpinner.getValue();
        int stopID = IDRangeStopSpinner.getValue();
        phoneTable.setItems(logic.findByAccountIDRange(mainApp.getPhoneData(), startID, stopID));
    }

    public void setMainApp(PhoneApplication app) {
        mainApp = app;
        showAllPhones();
    }

}
