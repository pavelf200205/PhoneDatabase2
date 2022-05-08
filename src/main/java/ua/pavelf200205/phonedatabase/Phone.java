package ua.pavelf200205.phonedatabase;

import javafx.beans.property.*;

import java.io.Serializable;

public class Phone implements Serializable {
    private static int counter = 0;
    private final SimpleIntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty patronymic;
    private final IntegerProperty accountID;
    private StringProperty city;

    private IntegerProperty domesticCallsTime; //Minutes
    private IntegerProperty interCityCallsTime; //Minutes

    public Phone(String firstName, String lastName, String patronymic, int accountID) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.accountID = new SimpleIntegerProperty(accountID);
        id = new SimpleIntegerProperty(counter++);
        domesticCallsTime = new SimpleIntegerProperty(0);
        interCityCallsTime = new SimpleIntegerProperty(0);

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public long getAccountID() {
        return accountID.get();
    }

    public IntegerProperty accountIDProperty() {
        return accountID;
    }
}

