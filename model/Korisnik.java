package model;

/**
 * Klasa koja predstavlja korisnika u sistemu rezervacije sportske opreme.
 * <p>
 * Obuhvata podatke o jedinstvenom identifikatoru korisnika, imenu,
 * email adresi i broju telefona.
 * </p>
 *
 * @author DjuroPopara/6271
 */
public class Korisnik {

    /** Jedinstveni identifikator korisnika. */
    private int id;

    /** Ime korisnika. */
    private String ime;

    /** Email adresa korisnika. */
    private String email;

    /** Broj telefona korisnika. */
    private String telefon;

    /**
     * Konstruktor sa svim parametrima, koristi se pri čitanju iz baze.
     *
     * @param id      Jedinstveni ID korisnika
     * @param ime     Ime korisnika
     * @param email   Email adresa korisnika
     * @param telefon Broj telefona korisnika
     */
    public Korisnik(int id, String ime, String email, String telefon) {
        this.id = id;
        this.ime = ime;
        this.email = email;
        this.telefon = telefon;
    }

    /**
     * Konstruktor bez ID-a, koristi se pri kreiranju novog korisnika
     * pre nego što mu baza dodeli ID.
     *
     * @param ime     Ime korisnika
     * @param email   Email adresa korisnika
     * @param telefon Broj telefona korisnika
     */
    public Korisnik(String ime, String email, String telefon) {
        this.ime = ime;
        this.email = email;
        this.telefon = telefon;
    }

    /**
     * Vraća jedinstveni identifikator korisnika.
     *
     * @return ID korisnika
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja jedinstveni identifikator korisnika.
     *
     * @param id Novi ID korisnika
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ime korisnika.
     *
     * @return Ime korisnika
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime korisnika.
     *
     * @param ime Novo ime korisnika
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraća email adresu korisnika.
     *
     * @return Email adresa korisnika
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email adresu korisnika.
     *
     * @param email Nova email adresa korisnika
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vraća broj telefona korisnika.
     *
     * @return Broj telefona korisnika
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Postavlja broj telefona korisnika.
     *
     * @param telefon Novi broj telefona korisnika
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Vraća tekstualnu reprezentaciju korisnika, uključujući sve podatke.
     *
     * @return String koji prikazuje stanje objekta
     */
    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
