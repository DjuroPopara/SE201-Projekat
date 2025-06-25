package view;

import controller.RezervacijaController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Rezervacija;

import java.util.List;

/**
 * JavaFX kontroler za prikaz, pretragu, ažuriranje i brisanje rezervacija.
 * <p>
 * Povezuje tabelu i njene kolone sa modelom {@link Rezervacija},
 * upravlja akcijama korisnika (brisanje, pretraga, promena datuma vraćanja)
 * i vraćanjem na glavni meni.
 * </p>
 *
 * @author DjuroPopara
 */
public class RezervacijaViewController {

    /** Tabela sa rezervacijama prikazanim za korisnika. */
    @FXML
    private TableView<Rezervacija> tableRezervacije;

    /** Kolona koja prikazuje ime korisnika. */
    @FXML
    private TableColumn<Rezervacija, String> korisnikColumn;

    /** Kolona koja prikazuje naziv opreme. */
    @FXML
    private TableColumn<Rezervacija, String> opremaColumn;

    /** Kolona koja prikazuje datum rezervacije. */
    @FXML
    private TableColumn<Rezervacija, String> datumRezColumn;

    /** Kolona koja prikazuje datum vraćanja opreme. */
    @FXML
    private TableColumn<Rezervacija, String> vracanjeColumn;

    /** Kolona koja prikazuje status rezervacije. */
    @FXML
    private TableColumn<Rezervacija, String> statusColumn;

    /** Dugme za povratak na glavni meni. */
    @FXML
    private Button btnNazad;

    /** Dugme za brisanje selektovane rezervacije. */
    @FXML
    private Button btnObrisiRezervaciju;

    /** DatePicker za izbor novog datuma vraćanja. */
    @FXML
    private DatePicker dateNovoVracanje;

    /** Polje za unos teksta za pretragu po korisniku ili opremi. */
    @FXML
    private TextField txtPretraga;

    /** Dugme za pokretanje pretrage. */
    @FXML
    private Button btnPretrazi;

    /** Kontroler koji upravlja operacijama nad rezervacijama. */
    private final RezervacijaController rezervacijaController = new RezervacijaController();

    /**
     * Inicijalizuje tabelu rezervacija i postavlja sortiranje po datumu rezervacije.
     * <p>
     * Povezuje kolone sa odgovarajućim svojstvima modela i učitava sve rezervacije iz baze.
     * </p>
     */
    @FXML
    public void initialize() {
        korisnikColumn.setCellValueFactory(new PropertyValueFactory<>("korisnikIme"));
        opremaColumn.setCellValueFactory(new PropertyValueFactory<>("opremaNaziv"));
        datumRezColumn.setCellValueFactory(new PropertyValueFactory<>("datumRezervacije"));
        vracanjeColumn.setCellValueFactory(new PropertyValueFactory<>("datumVracanja"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        ucitajRezervacije();

        // Sortiraj po datumu rezervacije uzlazno
        datumRezColumn.setSortType(TableColumn.SortType.ASCENDING);
        tableRezervacije.getSortOrder().add(datumRezColumn);
    }

    /**
     * Učitava sve rezervacije iz baze u tabelu.
     */
    private void ucitajRezervacije() {
        List<Rezervacija> rezervacije = rezervacijaController.getSveRezervacije();
        tableRezervacije.setItems(FXCollections.observableArrayList(rezervacije));
        tableRezervacije.refresh();
    }

    /**
     * Vraća se na glavni meni.
     */
    @FXML
    private void handleBack() {
        SceneSwitcher.switchScene(btnNazad, "MainView.fxml");
    }

    /**
     * Briše trenutno selektovanu rezervaciju iz baze i osvežava prikaz.
     * Ako nije ništa selektovano, prikazuje upozorenje.
     */
    @FXML
    private void obrisiSelektovanuRezervaciju() {
        Rezervacija selektovana = tableRezervacije.getSelectionModel().getSelectedItem();
        if (selektovana == null) {
            prikaziUpozorenje("Niste selektovali rezervaciju.");
            return;
        }

        System.out.println("Brisanje rezervacije sa ID: " + selektovana.getId());
        rezervacijaController.obrisiRezervaciju(selektovana.getId());
        prikaziInfo("Rezervacija uspešno obrisana!");
        ucitajRezervacije();
    }

    /**
     * Ažurira datum vraćanja za selektovanu rezervaciju na onaj iz {@code dateNovoVracanje}.
     * Ako nema selekcije ili nije odabran novi datum, prikazuje upozorenje.
     */
    @FXML
    private void azurirajDatumVracanja() {
        Rezervacija selektovana = tableRezervacije.getSelectionModel().getSelectedItem();
        if (selektovana == null) {
            prikaziUpozorenje("Niste selektovali rezervaciju.");
            return;
        }
        if (dateNovoVracanje.getValue() == null) {
            prikaziUpozorenje("Niste odabrali novi datum.");
            return;
        }

        String noviDatum = dateNovoVracanje.getValue().toString();
        rezervacijaController.azurirajRezervaciju(selektovana.getId(), noviDatum);
        prikaziInfo("Datum vraćanja uspešno ažuriran.");
        ucitajRezervacije();
    }

    /**
     * Filtrira prikaz rezervacija po unetom tekstu u {@code txtPretraga}.
     * </p>
     */
    @FXML
    private void handlePretraga() {
        String unos = txtPretraga.getText().toLowerCase().trim();
        if (unos.isEmpty()) {
            ucitajRezervacije();
            return;
        }

        List<Rezervacija> sve = rezervacijaController.getSveRezervacije();
        var filtrirane = sve.stream()
                .filter(r ->
                        (r.getKorisnikIme() != null && r.getKorisnikIme().toLowerCase().contains(unos)) ||
                                (r.getOpremaNaziv() != null && r.getOpremaNaziv().toLowerCase().contains(unos)))
                .toList();

        tableRezervacije.setItems(FXCollections.observableArrayList(filtrirane));
    }

    /**
     * Prikazuje upozorenje korisniku.
     *
     * @param poruka Tekst poruke
     */
    private void prikaziUpozorenje(String poruka) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upozorenje");
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    /**
     * Prikazuje informativni dijalog korisniku.
     *
     * @param poruka Tekst poruke
     */
    private void prikaziInfo(String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setContentText(poruka);
        alert.showAndWait();
    }
}
