package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Glavna JavaFX aplikacija za upravljanje sistemom rezervacije sportske opreme.
 * Učitava glavni FXML layout i prikazuje početni prozor.
 *
 * @author DjuroPopara6271
 */
public class MainApp extends Application {

    /**
     * Metoda koja se poziva pri pokretanju JavaFX aplikacije.
     * Učitava FXML fajl, postavlja naslov, dimenzije i prikazuje glavni scene.
     *
     * @param primaryStage primarni JavaFX Stage (prozor) u kome se prikazuje UI
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Rezervacija sportske opreme");
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);

            // Sprečava neočekivano zatvaranje (trenutno samo loguje događaj)
            primaryStage.setOnCloseRequest(event -> {
                System.out.println("Aplikacija se zatvara...");
            });

            primaryStage.show();
        } catch (IOException e) {
            System.err.println("❌ Greška pri učitavanju FXML-a: " + e.getMessage());
        }
    }

    /**
     * Ulazna tačka programa (nije obavezno definisati, ali korisno pri pokretanju iz IDE).
     *
     * @param args argumenti komandne linije
     */
    public static void main(String[] args) {
        launch(args);
    }
}
