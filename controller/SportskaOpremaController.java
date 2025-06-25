package controller;

import database.DatabaseConnection;
import model.SportskaOprema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja upravlja sportskom opremom u sistemu, omogućavajući dodavanje,
 * ažuriranje, brisanje i preuzimanje podataka iz baze.
 * 
 * @author [DjuroPopara/6271
 */
public class SportskaOpremaController {

    /**
     * Dodaje novu sportsku opremu u bazu podataka.
     * 
     * @param oprema Objekat sportske opreme koji se dodaje
     */
    public void dodajOprema(SportskaOprema oprema) {
        String sql = "INSERT INTO oprema (naziv, tip_id, dostupnost, cena, kolicina, lokacija) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, oprema.getNaziv());
            preparedStatement.setInt(2, oprema.getTipId());
            preparedStatement.setBoolean(3, oprema.isDostupnost());
            preparedStatement.setDouble(4, oprema.getCena());
            preparedStatement.setInt(5, oprema.getKolicina());
            preparedStatement.setString(6, oprema.getLokacija());
            preparedStatement.executeUpdate();
            System.out.println("Oprema uspešno dodata u bazu.");
        } catch (SQLException e) {
            System.err.println("Greška pri dodavanju opreme: " + e.getMessage());
        }
    }

    /**
     * Dohvata svu sportsku opremu iz baze podataka.
     * 
     * @return Lista sve sportske opreme iz baze
     */
    public List<SportskaOprema> getSvaOprema() {
        List<SportskaOprema> opreme = new ArrayList<>();
        String sql = "SELECT * FROM oprema";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naziv = resultSet.getString("naziv");
                int tipId = resultSet.getInt("tip_id");
                boolean dostupnost = resultSet.getBoolean("dostupnost");
                double cena = resultSet.getDouble("cena");
                int kolicina = resultSet.getInt("kolicina");
                String lokacija = resultSet.getString("lokacija");
                opreme.add(new SportskaOprema(id, naziv, tipId, dostupnost, cena, kolicina, lokacija));
            }
        } catch (SQLException e) {
            System.err.println("Greška pri prikazu opreme: " + e.getMessage());
        }
        return opreme;
    }

    /**
     * Ažurira cenu i količinu sportske opreme na osnovu ID-a.
     * 
     * @param id ID sportske opreme
     * @param novaCena Nova cena sportske opreme
     * @param novaKolicina Nova količina sportske opreme
     */
    public void azurirajOprema(int id, double novaCena, int novaKolicina) {
        String sql = "UPDATE oprema SET cena = ?, kolicina = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, novaCena);
            preparedStatement.setInt(2, novaKolicina);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Oprema uspešno ažurirana.");
        } catch (SQLException e) {
            System.err.println("Greška pri ažuriranju opreme: " + e.getMessage());
        }
    }

    /**
     * Briše sportsku opremu iz baze podataka na osnovu ID-a.
     * 
     * @param id ID sportske opreme koja se briše
     */
    public void obrisiOprema(int id) {
        String sql = "DELETE FROM oprema WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Oprema uspešno obrisana.");
        } catch (SQLException e) {
            System.err.println("Greška pri brisanju opreme: " + e.getMessage());
        }
    }

    /**
     * Dodaje sportsku opremu u bazu sa dodatnom validacijom podataka.
     * 
     * @param oprema Objekat sportske opreme koji se dodaje
     */
    public void dodajOprema2(SportskaOprema oprema) {
        if (oprema.getKolicina() < 0) {
            System.err.println("Količina ne može biti negativna.");
            return;
        }
        if (oprema.getCena() <= 0) {
            System.err.println("Cena mora biti veća od 0.");
            return;
        }

        String sql = "INSERT INTO oprema (naziv, tip_id, dostupnost, cena, kolicina, lokacija) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, oprema.getNaziv());
            preparedStatement.setInt(2, oprema.getTipId());
            preparedStatement.setBoolean(3, oprema.isDostupnost());
            preparedStatement.setDouble(4, oprema.getCena());
            preparedStatement.setInt(5, oprema.getKolicina());
            preparedStatement.setString(6, oprema.getLokacija());
            preparedStatement.executeUpdate();
            System.out.println("Oprema uspešno dodata u bazu.");
        } catch (SQLException e) {
            System.err.println("Greška pri dodavanju opreme: " + e.getMessage());
        }
    }
}
