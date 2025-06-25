package view;

import controller.RezervacijaController;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import model.Rezervacija;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Kontroler za prikaz BarChart‑a koji pokazuje broj rezervacija po korisniku.
 * Učitava sve rezervacije iz baze, grupiše ih po imenu korisnika
 * i iscrtava kolone na barChart.
 *
 * @author DjuroPopara6271
 */
public class GrafikonController {

    /** BarChart iz FXML‑a u kojem crtamo podatke. */
    @FXML
    private BarChart<String, Number> barChart;

    /** Dugme za povratak na glavni ekran. */
    @FXML
    private Button btnNazad;

    /** Kontroler za rad sa rezervacijama. */
    private final RezervacijaController rezervacijaController = new RezervacijaController();

    /**
     * Inicijalizacija se pokreće nakon što FXML bude učitan:
     * dohvatamo sve rezervacije, brojimo po korisniku i iscrtavamo seriju.
     */
    @FXML
    public void initialize() {
        List<Rezervacija> rezervacije = rezervacijaController.getSveRezervacije();
        Map<String, Integer> brojPoKorisniku = new HashMap<>();

        // Grupisanje po korisničkom imenu
        for (Rezervacija r : rezervacije) {
            brojPoKorisniku.merge(r.getKorisnikIme(), 1, Integer::sum);
        }

        // Priprema serije podataka
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Rezervacije po korisniku");
        brojPoKorisniku.forEach((ime, count) ->
                series.getData().add(new XYChart.Data<>(ime, count))
        );

        // Dodavanje serije u chart
        barChart.getData().add(series);
    }

    /** Vraća na glavni prozor. */
    @FXML
    private void handleNazad() {
        SceneSwitcher.switchScene(btnNazad, "MainView.fxml");
    }
}
