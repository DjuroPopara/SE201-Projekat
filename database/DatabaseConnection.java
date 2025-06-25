package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility klasa za uspostavljanje konekcije sa MySQL bazom podataka.
 * <p>
 * Koristi {@link DriverManager} da se poveže na bazu čiji su podaci
 * specificirani u konstanti {@link #URL}, {@link #USER} i {@link #PASSWORD}.
 * </p>
 *
 * @author DjuroPopara6271
 */
public class DatabaseConnection {

    /** JDBC URL baze podataka (sa parametrima za SSL i vremensku zonu). */
    private static final String URL =
            "jdbc:mysql://localhost:3306/rezervacijasportskeopreme?useSSL=false&serverTimezone=UTC";

    /** Korisničko ime za pristup MySQL bazi. */
    private static final String USER = "root";

    /** Lozinka za pristup MySQL bazi. */
    private static final String PASSWORD = "";

    /**
     * Uspostavlja i vraća novu {@link Connection} ka bazi podataka.
     *
     * @return nova konekcija ako je uspostavljena, inače {@code null}
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Učitaj JDBC drajver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Poveži se na bazu
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Konekcija sa bazom je uspešno uspostavljena!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC drajver nije pronađen: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Greška pri konekciji sa bazom: " + e.getMessage());
        }
        return connection;
    }
}
