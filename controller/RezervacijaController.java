package controller;

import database.DatabaseConnection;
import model.Rezervacija;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja upravlja rezervacijama u sistemu, omogućavajući dodavanje,
 * ažuriranje, brisanje i preuzimanje podataka iz baze.
 *
 * @author DjuroPopara/6271
 */
public class RezervacijaController {



    public List<Rezervacija> getSveRezervacije() {
        List<Rezervacija> rezervacije = new ArrayList<>();
        String sql = "SELECT r.id, r.korisnik_id, r.oprema_id, k.ime AS korisnik_ime, o.naziv AS oprema_naziv, " +
                "r.datum_rezervacije, r.datum_vracanja, r.kolicina, r.status " +
                "FROM rezervacija r " +
                "JOIN korisnik k ON r.korisnik_id = k.id " +
                "JOIN oprema o ON r.oprema_id = o.id";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int korisnikId = rs.getInt("korisnik_id");
                int opremaId = rs.getInt("oprema_id");
                String datumRezervacije = rs.getDate("datum_rezervacije").toString();
                String datumVracanja = rs.getDate("datum_vracanja").toString();
                int kolicina = rs.getInt("kolicina");
                String status = rs.getString("status");
                String korisnikIme = rs.getString("korisnik_ime");
                String opremaNaziv = rs.getString("oprema_naziv");

                Rezervacija r = new Rezervacija(id, korisnikId, opremaId, korisnikIme, opremaNaziv, datumRezervacije, datumVracanja, kolicina, status);
                r.setKorisnikIme(korisnikIme);
                r.setOpremaNaziv(opremaNaziv);
                rezervacije.add(r);
            }

        } catch (SQLException e) {
            System.err.println("Greška pri prikazu rezervacija: " + e.getMessage());
            System.err.println("Greška pri dodavanju rezervacije: " + e.getMessage());
            e.printStackTrace();

        }

        return rezervacije;
    }


    public void azurirajRezervaciju(int id, String noviDatumVracanja) {
        String sql = "UPDATE rezervacija SET datum_vracanja = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(noviDatumVracanja));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Rezervacija uspešno ažurirana.");

        } catch (SQLException e) {
            System.err.println("Greška pri ažuriranju rezervacije: " + e.getMessage());
        }
    }

    public void obrisiRezervaciju(int id) {
        String sql = "DELETE FROM rezervacija WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Rezervacija uspešno obrisana.");

        } catch (SQLException e) {
            System.err.println("Greška pri brisanju rezervacije: " + e.getMessage());
        }
    }


    public void dodajRezervaciju(Rezervacija rezervacija) {
        String sql = "INSERT INTO rezervacija (korisnik_id, oprema_id, datum_rezervacije, datum_vracanja, kolicina, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Konvertuj string datume u LocalDate
            java.sql.Date datumRez = java.sql.Date.valueOf(rezervacija.getDatumRezervacije());
            java.sql.Date datumVrac = java.sql.Date.valueOf(rezervacija.getDatumVracanja());

            preparedStatement.setInt(1, rezervacija.getKorisnikId());
            preparedStatement.setInt(2, rezervacija.getOpremaId());
            preparedStatement.setDate(3, datumRez);
            preparedStatement.setDate(4, datumVrac);
            preparedStatement.setInt(5, rezervacija.getKolicina());
            preparedStatement.setString(6, rezervacija.getStatus() != null ? rezervacija.getStatus() : "aktivna");

            preparedStatement.executeUpdate();
            System.out.println("Rezervacija uspešno dodata u bazu.");

        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Greška pri dodavanju rezervacije: " + e.getMessage());
        }
    }



}

