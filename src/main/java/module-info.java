module ua.pavelf200205.phonedatabase.phonedatabase {
    requires javafx.controls;
    requires javafx.fxml;


    opens ua.pavelf200205.phonedatabase to javafx.fxml;
    exports ua.pavelf200205.phonedatabase;
}