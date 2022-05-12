package ua.pavelf200205.phonedatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        return allPhones.stream().noneMatch(x -> x.getAccountID()==accountID);
//        for(Phone phone : allPhones) {
//            if(phone.getAccountID() == accountID) return false;
//        }
//        return true;
    }

    public ObservableList<Phone> findDomesticCallsGreaterThan(ObservableList<Phone> allPhones, int time){
        return FXCollections.observableList(allPhones.stream().filter(x->x.getDomesticCallsTime()>time).toList());
//        ObservableList<Phone> result = FXCollections.observableArrayList();
//        for(Phone phone : allPhones) {
//            if(phone.getDomesticCallsTime()>time)
//                result.add(phone);
//        }
//        return result;
    }

    public ObservableList<Phone> findIntercityNonZero(ObservableList<Phone> allPhones) {
       return FXCollections.observableList(allPhones.stream()
                .filter(x -> x.getInterCityCallsTime()!= 0)
                .sorted(Comparator.comparing(Phone::getLastName)
                .thenComparing(Phone::getFirstName)
                .thenComparing(Phone::getPatronymic))
                .toList());
//        ObservableList<Phone> result = FXCollections.observableArrayList();
//        for(Phone phone : allPhones) {
//            if(phone.getInterCityCallsTime() != 0)
//                result.add(phone);
//        }
//        result.sort(Comparator.comparing(Phone::getLastName)
//                .thenComparing(Phone::getFirstName)
//                .thenComparing(Phone::getPatronymic));
//        return result;
    }

    public ObservableList<Phone> findByAccountIDRange(ObservableList<Phone> allPhones, int startID, int stopID) {
        return FXCollections.observableArrayList(allPhones.stream()
                .filter(x->(x.getAccountID()>=startID)&&(x.getAccountID()<=stopID))
                .toList());
//        ObservableList<Phone> result = FXCollections.observableArrayList();
//        for(Phone phone : allPhones) {
//            if(phone.getAccountID() >= startID && phone.getAccountID() <= stopID)
//                result.add(phone);
//        }
//        return result;
    }
}
