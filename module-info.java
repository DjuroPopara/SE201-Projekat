module com.example.projekatfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens view to javafx.fxml;
    exports view;
    exports controller;

    opens model to javafx.base;  // ✅ OTVARA model paket za JavaFX
}
