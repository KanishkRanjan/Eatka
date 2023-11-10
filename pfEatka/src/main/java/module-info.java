module com.example.pfeatka {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.pfeatka.Controllers;
    opens com.example.pfeatka to javafx.fxml;
    exports com.example.pfeatka;

    exports com.example.pfeatka.Controllers;
}