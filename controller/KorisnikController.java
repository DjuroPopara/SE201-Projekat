package controller;

import database.DatabaseConnection;
import model.Korisnik;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja upravlja korisnicima u sistemu, omogućavajući dodavanje,
 * ažuriranje, brisanje i preuzimanje podataka iz baze.
 * 
 * @author [DjuroPopara/6271
 */
public class KorisnikController {

    /**
     * Dodaje novog korisnika u bazu podataka.
     * 
     * @param korisnik Objekat korisnika koji se dodaje
     */
    public void dodajKorisnika(Korisnik korisnik) {
        String sql = "INSERT INTO korisnik (ime, email, telefon) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, korisnik.getIme());
            preparedStatement.setString(2, korisnik.getEmail());
            preparedStatement.setString(3, korisnik.getTelefon());
            preparedStatement.executeUpdate();
            System.out.println("Korisnik uspešno dodat u bazu.");
        } catch (SQLException e) {
            System.err.println("Greška pri dodavanju korisnika: " + e.getMessage());
        }
    }

    /**
     * Dohvata sve korisnike iz baze podataka.
     * 
     * @return Lista svih korisnika iz baze
     */
    public List<Korisnik> getSviKorisnici() {
        List<Korisnik> korisnici = new ArrayList<>();
        String sql = "SELECT * FROM korisnik";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ime = resultSet.getString("ime");
                String email = resultSet.getString("email");
                String telefon = resultSet.getString("telefon");
                korisnici.add(new Korisnik(id, ime, email, telefon));
            }
        } catch (SQLException e) {
            System.err.println("Greška pri čitanju korisnika: " + e.getMessage());
        }
        return korisnici;
    }

    /**
     * Ažurira broj telefona korisnika na osnovu email adrese.
     * 
     * @param korisnik Objekat korisnika koji se ažurira
     * @param noviTelefon Novi broj telefona korisnika
     */
    public void azurirajKorisnika(Korisnik korisnik, String noviTelefon) {
        String sql = "UPDATE korisnik SET telefon = ? WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, noviTelefon);
            preparedStatement.setString(2, korisnik.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Korisnik uspešno ažuriran.");
        } catch (SQLException e) {
            System.err.println("Greška pri ažuriranju korisnika: " + e.getMessage());
        }
    }

    /**
     * Briše korisnika iz baze podataka na osnovu email adrese.
     * 
     * @param email Email korisnika koji se briše
     */
    public void obrisiKorisnika(String email) {
        String sql = "DELETE FROM korisnik WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            System.out.println("Korisnik uspešno obrisan.");
        } catch (SQLException e) {
            System.err.println("Greška pri brisanju korisnika: " + e.getMessage());
        }
    }

    /**
     * Dodaje korisnika u bazu sa dodatnom validacijom email-a i broja telefona.
     * 
     * @param korisnik Objekat korisnika koji se dodaje
     */
    public void dodajKorisnika2(Korisnik korisnik) {
        if (!isValidEmail(korisnik.getEmail())) {
            System.err.println("Nevalidan email format: " + korisnik.getEmail());
            return;
        }
        if (korisnik.getTelefon().length() < 9) {
            System.err.println("Nevalidan broj telefona: " + korisnik.getTelefon());
            return;
        }

        String sql = "INSERT INTO korisnik (ime, email, telefon) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, korisnik.getIme());
            preparedStatement.setString(2, korisnik.getEmail());
            preparedStatement.setString(3, korisnik.getTelefon());
            preparedStatement.executeUpdate();
            System.out.println("Korisnik uspešno dodat u bazu.");
        } catch (SQLException e) {
            System.err.println("Greška pri dodavanju korisnika: " + e.getMessage());
        }




    }

    /**
     * Proverava da li je email u ispravnom formatu.
     * 
     * @param email Email adresa koja se proverava
     * @return true ako je email validan, false ako nije
     */
    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}






