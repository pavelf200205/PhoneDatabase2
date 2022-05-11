package ua.pavelf200205.phonedatabase;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.List;
import java.util.Map;

public class CityListViewController {
    @FXML
    TreeView<String> cityTree;

    public void setCityMap(Map<String, List<Phone>> map) {
        TreeItem<String> root = new TreeItem<>("Міста:");
        for(String city : map.keySet()) {
            TreeItem<String> cityItem = new TreeItem<>(city);
            root.getChildren().add(cityItem);
            for(Phone phone : map.get(city)) {
                TreeItem<String> phoneItem = new TreeItem<>(phone.toString());
                cityItem.getChildren().add(phoneItem);
            }
        }
        cityTree.setRoot(root);
    }
}
