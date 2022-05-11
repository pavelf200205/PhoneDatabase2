package ua.pavelf200205.phonedatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.*;

public class Logic {

    public Map<String, List<Phone>> generateCitiesMap(ObservableList<Phone> allPhones) {
        Map<String, List<Phone>> map = new HashMap<>();
        for(Phone phone : allPhones) {
            if(map.containsKey(phone.getCity()))
                map.get(phone.getCity()).add(phone);
            else {
                map.put(phone.getCity(), new ArrayList<>(List.of(phone)));
            }

        }
        return map;
    }

    public boolean checkIfIDUnique(ObservableList<Phone> allPhones, int accountID) {
        for(Phone phone : allPhones) {
            if(phone.getAccountID() == accountID) return false;
        }
        return true;
    }

    public ObservableList<Phone> findDomesticCallsGreaterThan(ObservableList<Phone> allPhones, int time){
        ObservableList<Phone> result = FXCollections.observableArrayList();
        for(Phone phone : allPhones) {
            if(phone.getDomesticCallsTime()>time)
                result.add(phone);
        }
        return result;
    }

    public ObservableList<Phone> findIntercityNonZero(ObservableList<Phone> allPhones) {
        ObservableList<Phone> result = FXCollections.observableArrayList();
        for(Phone phone : allPhones) {
            if(phone.getInterCityCallsTime() != 0)
                result.add(phone);
        }
        result.sort(Comparator.comparing(Phone::getLastName)
                .thenComparing(Phone::getFirstName)
                .thenComparing(Phone::getPatronymic));
        return result;
    }

    public ObservableList<Phone> findByIDRange(ObservableList<Phone> allPhones, int startID, int stopID) {
        ObservableList<Phone> result = FXCollections.observableArrayList();
        for(Phone phone : allPhones) {
            if(phone.getAccountID() >= startID && phone.getAccountID() <= stopID)
                result.add(phone);
        }
        return result;
    }
}
