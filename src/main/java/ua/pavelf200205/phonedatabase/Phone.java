package ua.pavelf200205.phonedatabase;

import javafx.beans.property.*;

import java.io.Serializable;

public class Phone implements Serializable {
    private static int counter = 0;
    private final SimpleIntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty patronymic;
    private final SimpleIntegerProperty accountID;
    private StringProperty city;
    private SimpleIntegerProperty domesticCallsTime; //Minutes
    private SimpleIntegerProperty interCityCallsTime; //Minutes

    public Phone(String firstName, String lastName, String patronymic, int accountID, String city) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.accountID = new SimpleIntegerProperty(accountID);
        this.city = new SimpleStringProperty(city);
        id = new SimpleIntegerProperty(counter++);
        domesticCallsTime = new SimpleIntegerProperty(0);
        interCityCallsTime = new SimpleIntegerProperty(0);

    }

    public String toString() {
        return "id=" + getId() + ", рахунок №" + getAccountID() + " " + getLastName() + " " + getFirstName() + " " + getPatronymic() + "\n";
    }

    public int getDomesticCallsTime() {
        return domesticCallsTime.get();
    }


    public IntegerProperty domesticCallsTimeProperty() {
        return domesticCallsTime;
    }

    public int getInterCityCallsTime() {
        return interCityCallsTime.get();
    }

    public IntegerProperty interCityCallsTimeProperty() {
        return interCityCallsTime;
    }

    public void updateDomesticCallsTime(int minutes) {
        if(minutes > 0)
            domesticCallsTime.set(domesticCallsTime.get()+minutes);
    }

    public void updateIntercityCallsTime(int minutes) {
        if(minutes > 0)
            interCityCallsTime.set(interCityCallsTime.get()+minutes);
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

    public int getAccountID() {
        return accountID.get();
    }

    public SimpleIntegerProperty accountIDProperty() {
        return accountID;
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

}

