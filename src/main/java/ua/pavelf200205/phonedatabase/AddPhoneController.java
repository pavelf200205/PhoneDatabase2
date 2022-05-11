package ua.pavelf200205.phonedatabase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPhoneController {
    Phone phone;
    @FXML
    TextField lastNameField;
    @FXML
    TextField firstNameField;
    @FXML
    TextField patronymicField;
    @FXML
    TextField accountIDField;
    @FXML
    TextField cityField;

    public Phone getPhone() {
        return phone;
    }
    public boolean isPhone() {
        return phone != null;
    }
    @FXML
    private void initialize() {
        accountIDField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    accountIDField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    @FXML
    protected void onSubmitButtonClick() {
        try {
            int accountID = Integer.parseInt(accountIDField.getText());
            phone = new Phone(firstNameField.getText(),
                    lastNameField.getText(),
                    patronymicField.getText(),
                    accountID,
                    cityField.getText());
            lastNameField.getScene().getWindow().hide();
        } catch(Exception e) {
            e.printStackTrace();

        }
    }


}
