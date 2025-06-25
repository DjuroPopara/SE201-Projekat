package view;

import controller.KorisnikController;
import controller.RezervacijaController;
import controller.SportskaOpremaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Korisnik;
import model.Rezervacija;
import model.SportskaOprema;

import java.time.LocalDate;

/**
 * Kontroler za glavni ekran aplikacije.
 * Omogućava odabir korisnika, opreme, datuma i kreiranje rezervacije,
 * kao i navigaciju na prikaz rezervacija, opreme i grafikon.
 *
 * @author DjuroPopara6271
 */
public class MainController {

    @FXML private Label lblNaslov;
    @FXML private ComboBox<Korisnik> comboKorisnik;
    @FXML private ComboBox<SportskaOprema> comboOprema;
    @FXML private DatePicker dateRezervacija;
    @FXML private DatePicker dateVracanje;
    @FXML private TextField txtKolicina;
    @FXML private TextField txtStatus;
    @FXML private Button btnRezervisi;

    @FXML private TableView<Korisnik> tabelaKorisnika;
    @FXML private TableColumn<Korisnik, String> imeColumn;
    @FXML private TableColumn<Korisnik, String> telefonColumn;

    @FXML private Button btnPregledRezervacija;
    @FXML private Button btnPregledOpreme;

    private final KorisnikController korisnikController = new KorisnikController();
    private final SportskaOpremaController opremaController = new SportskaOpremaController();
    private final RezervacijaController rezervacijaController = new RezervacijaController();

    /**
     * Poziva se nakon učitavanja FXML-a.
     * Podešava TableView korisnika i ComboBox-e za korisnika i opremu.
     */
    @FXML
    public void initialize() {
        imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        ucitajKorisnike();

        comboKorisnik.setItems(FXCollections.observableArrayList(korisnikController.getSviKorisnici()));
        comboOprema.setItems(FXCollections.observableArrayList(opremaController.getSvaOprema()));

        // Prikaz samo imena/naziva u ComboBox-evima
        comboKorisnik.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(Korisnik item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getIme());
            }
        });
        comboKorisnik.setButtonCell(comboKorisnik.getCellFactory().call(null));

        comboOprema.setCellFactory(list -> new ListCell<>() {
            @Override protected void updateItem(SportskaOprema item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNaziv());
            }
        });
        comboOprema.setButtonCell(comboOprema.getCellFactory().call(null));
    }

    /**
     * Učitava sve postojeće korisnike u tabelu.
     */
    private void ucitajKorisnike() {
        ObservableList<Korisnik> korisnici = FXCollections.observableArrayList(korisnikController.getSviKorisnici());
        tabelaKorisnika.setItems(korisnici);
    }

    /**
     * Rukuje klikom na dugme „Rezerviši“.
     * Validira unos i, ako je ispravno, šalje novi objekat Rezervacija u kontroler.
     */
    @FXML
    private void handleRezervacija() {
        Korisnik korisnik = comboKorisnik.getValue();
        SportskaOprema oprema = comboOprema.getValue();
        LocalDate datumRez = dateRezervacija.getValue();
        LocalDate datumVrac = dateVracanje.getValue();
        String status = txtStatus.getText().trim();
        String kolText = txtKolicina.getText().trim();

        if (korisnik==null || oprema==null || datumRez==null || datumVrac==null ||
                kolText.isEmpty() || status.isEmpty()) {
            prikaziUpozorenje("Popunite sva polja (uključujući status).");
            return;
        }

        LocalDate danas = LocalDate.now();
        if (datumRez.isBefore(danas)) {
            prikaziUpozorenje("Datum rezervacije ne može biti u prošlosti.");
            return;
        }
        if (!datumVrac.isAfter(datumRez)) {
            prikaziUpozorenje("Datum vraćanja mora biti posle datuma rezervacije.");
            return;
        }

        int kolicina;
        try {
            kolicina = Integer.parseInt(kolText);
            if (kolicina <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            prikaziUpozorenje("Količina mora biti pozitivan ceo broj.");
            return;
        }
        if (kolicina > oprema.getKolicina()) {
            prikaziUpozorenje("Nema dovoljno opreme na stanju. Max: " + oprema.getKolicina());
            return;
        }

        if (!status.matches("aktivna|otkazana|završena")) {
            prikaziUpozorenje("Status mora biti: aktivna, otkazana ili završena.");
            return;
        }

        Rezervacija nova = new Rezervacija(
                korisnik.getId(),
                oprema.getId(),
                datumRez.toString(),
                datumVrac.toString(),
                kolicina,
                status
        );
        rezervacijaController.dodajRezervaciju(nova);
        prikaziInfo("Rezervacija uspešno dodata!");
    }

    /**
     * Prikazuje upozorenje korisniku.
     * @param poruka tekst upozorenja
     */
    private void prikaziUpozorenje(String poruka) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upozorenje");
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    /**
     * Prikazuje informacioni dijalog.
     * @param poruka tekst obaveštenja
     */
    private void prikaziInfo(String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Obaveštenje");
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    /**
     * Prebacuje na prikaz svih rezervacija.
     */
    @FXML
    private void handleOpenRezervacije() {
        SceneSwitcher.switchScene(btnPregledRezervacija, "RezervacijaView.fxml");
    }

    /**
     * Prebacuje na prikaz opreme.
     */
    @FXML
    private void handleOpenOprema() {
        SceneSwitcher.switchScene(btnPregledOpreme, "OpremaView.fxml");
    }

    /**
     * Prebacuje na grafikon rezervacija.
     */
    @FXML
    private void handleGrafikon() {
        SceneSwitcher.switchScene(btnPregledRezervacija, "GrafikonView.fxml");
    }
}
