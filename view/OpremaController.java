package view;

import controller.SportskaOpremaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.SportskaOprema;

/**
 * JavaFX kontroler za prikaz i upravljanje sportskom opremom.
 * <p>
 * Povezuje kolone tabele sa svojstvima modela i učitava
 * listu opreme iz baze, kao i omogućava povratak na glavni meni.
 * </p>
 *
 * @author DjuroPopara
 */
public class OpremaController {

    /** Tabela koja prikazuje listu sportske opreme. */
    @FXML
    private TableView<SportskaOprema> tableOprema;

    /** Kolona za ID opreme. */
    @FXML
    private TableColumn<SportskaOprema, Integer> idColumn;
    /** Kolona za naziv opreme. */
    @FXML
    private TableColumn<SportskaOprema, String> nazivColumn;
    /** Kolona za tip opreme (ID kategorije). */
    @FXML
    private TableColumn<SportskaOprema, Integer> tipColumn;
    /** Kolona za dostupnost opreme. */
    @FXML
    private TableColumn<SportskaOprema, Boolean> dostupnostColumn;
    /** Kolona za cenu iznajmljivanja opreme. */
    @FXML
    private TableColumn<SportskaOprema, Double> cenaColumn;
    /** Kolona za količinu dostupne opreme. */
    @FXML
    private TableColumn<SportskaOprema, Integer> kolicinaColumn;
    /** Kolona za lokaciju skladištenja opreme. */
    @FXML
    private TableColumn<SportskaOprema, String> lokacijaColumn;

    /** Dugme za povratak na glavni meni. */
    @FXML
    private Button btnNazadOprema;

    /**
     * Inicijalizuje kontroler.
     * <p>
     * Postavlja {@code PropertyValueFactory}-e za svaku kolonu
     * i učitava podatke iz baze u tabelu.
     * </p>
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        tipColumn.setCellValueFactory(new PropertyValueFactory<>("tipId"));
        dostupnostColumn.setCellValueFactory(new PropertyValueFactory<>("dostupnost"));
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        kolicinaColumn.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
        lokacijaColumn.setCellValueFactory(new PropertyValueFactory<>("lokacija"));

        SportskaOpremaController controller = new SportskaOpremaController();
        ObservableList<SportskaOprema> lista =
                FXCollections.observableArrayList(controller.getSvaOprema());
        tableOprema.setItems(lista);
    }

    /**
     * Vrši povratak na glavni meni klikom na dugme.
     */
    @FXML
    private void handleBack() {
        SceneSwitcher.switchScene(btnNazadOprema, "MainView.fxml");
    }
}
