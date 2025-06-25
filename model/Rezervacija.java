package model;

import java.util.Objects;

/**
 * Predstavlja rezervaciju sportske opreme u sistemu.
 * <p>
 * Svaka rezervacija ima svoj jedinstveni ID, referencu na korisnika i opremu,
 * kao i informacije o datumima rezervacije i vraćanja, količini i statusu.
 * </p>
 *
 * @author DjuroPopara6271
 */
public class Rezervacija {

    /** Jedinstveni identifikator rezervacije. */
    private int id;

    /** ID korisnika koji je napravio rezervaciju. */
    private int korisnikId;

    /** ID opreme koja je rezervisana. */
    private int opremaId;

    /** Ime korisnika (za prikaz u tabeli). */
    private String korisnikIme;

    /** Naziv opreme (za prikaz u tabeli). */
    private String opremaNaziv;

    /** Datum kada je rezervacija napravljena (u formatu YYYY-MM-DD). */
    private String datumRezervacije;

    /** Datum kada se oprema treba vratiti (u formatu YYYY-MM-DD). */
    private String datumVracanja;

    /** Količina opreme koja je rezervisana. */
    private int kolicina;

    /** Status rezervacije (npr. "aktivna", "otkazana", "završena"). */
    private String status;

    /**
     * Konstruktor za punu inicijalizaciju, uključujući i polja za prikaz
     * (korisničko ime i naziv opreme).
     *
     * @param id               Jedinstveni ID rezervacije
     * @param korisnikId       ID korisnika
     * @param opremaId         ID opreme
     * @param korisnikIme      Ime korisnika za prikaz
     * @param opremaNaziv      Naziv opreme za prikaz
     * @param datumRezervacije Datum rezervacije (YYYY-MM-DD)
     * @param datumVracanja    Datum vraćanja (YYYY-MM-DD)
     * @param kolicina         Količina rezervisane opreme
     * @param status           Status rezervacije
     */
    public Rezervacija(int id,
                       int korisnikId,
                       int opremaId,
                       String korisnikIme,
                       String opremaNaziv,
                       String datumRezervacije,
                       String datumVracanja,
                       int kolicina,
                       String status) {
        this.id = id;
        this.korisnikId = korisnikId;
        this.opremaId = opremaId;
        this.korisnikIme = korisnikIme;
        this.opremaNaziv = opremaNaziv;
        this.datumRezervacije = datumRezervacije;
        this.datumVracanja = datumVracanja;
        this.kolicina = kolicina;
        this.status = status;
    }

    /**
     * Konstruktor koji se koristi pri učitavanju iz baze, bez polja za prikaz.
     *
     * @param id               Jedinstveni ID rezervacije
     * @param korisnikId       ID korisnika
     * @param opremaId         ID opreme
     * @param datumRezervacije Datum rezervacije (YYYY-MM-DD)
     * @param datumVracanja    Datum vraćanja (YYYY-MM-DD)
     * @param kolicina         Količina rezervisane opreme
     * @param status           Status rezervacije
     */
    public Rezervacija(int id,
                       int korisnikId,
                       int opremaId,
                       String datumRezervacije,
                       String datumVracanja,
                       int kolicina,
                       String status) {
        this.id = id;
        this.korisnikId = korisnikId;
        this.opremaId = opremaId;
        this.datumRezervacije = datumRezervacije;
        this.datumVracanja = datumVracanja;
        this.kolicina = kolicina;
        this.status = status;
    }

    /**
     * Konstruktor za kreiranje nove rezervacije (pre nego što se ID dodeli).
     *
     * @param korisnikId       ID korisnika
     * @param opremaId         ID opreme
     * @param datumRezervacije Datum rezervacije (YYYY-MM-DD)
     * @param datumVracanja    Datum vraćanja (YYYY-MM-DD)
     * @param kolicina         Količina rezervisane opreme
     * @param status           Status rezervacije
     */
    public Rezervacija(int korisnikId,
                       int opremaId,
                       String datumRezervacije,
                       String datumVracanja,
                       int kolicina,
                       String status) {
        this.korisnikId = korisnikId;
        this.opremaId = opremaId;
        this.datumRezervacije = datumRezervacije;
        this.datumVracanja = datumVracanja;
        this.kolicina = kolicina;
        this.status = status;
    }

    /** @return ID rezervacije */
    public int getId() {
        return id;
    }

    /** @param id Novi ID rezervacije */
    public void setId(int id) {
        this.id = id;
    }

    /** @return ID korisnika */
    public int getKorisnikId() {
        return korisnikId;
    }

    /** @param korisnikId Novi ID korisnika */
    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    /** @return ID opreme */
    public int getOpremaId() {
        return opremaId;
    }

    /** @param opremaId Novi ID opreme */
    public void setOpremaId(int opremaId) {
        this.opremaId = opremaId;
    }

    /** @return Ime korisnika */
    public String getKorisnikIme() {
        return korisnikIme;
    }

    /** @param korisnikIme Novo ime korisnika */
    public void setKorisnikIme(String korisnikIme) {
        this.korisnikIme = korisnikIme;
    }

    /** @return Naziv opreme */
    public String getOpremaNaziv() {
        return opremaNaziv;
    }

    /** @param opremaNaziv Novi naziv opreme */
    public void setOpremaNaziv(String opremaNaziv) {
        this.opremaNaziv = opremaNaziv;
    }

    /** @return Datum rezervacije (YYYY-MM-DD) */
    public String getDatumRezervacije() {
        return datumRezervacije;
    }

    /** @param datumRezervacije Novi datum rezervacije (YYYY-MM-DD) */
    public void setDatumRezervacije(String datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    /** @return Datum vraćanja (YYYY-MM-DD) */
    public String getDatumVracanja() {
        return datumVracanja;
    }

    /** @param datumVracanja Novi datum vraćanja (YYYY-MM-DD) */
    public void setDatumVracanja(String datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    /** @return Količina rezervisane opreme */
    public int getKolicina() {
        return kolicina;
    }

    /** @param kolicina Nova količina opreme */
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    /** @return Status rezervacije */
    public String getStatus() {
        return status;
    }

    /** @param status Novi status rezervacije */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Uključuje sve atribute za lakšu dijagnostiku.
     * </p>
     */
    @Override
    public String toString() {
        return "Rezervacija{" +
                "id=" + id +
                ", korisnikId=" + korisnikId +
                ", opremaId=" + opremaId +
                ", korisnikIme='" + korisnikIme + '\'' +
                ", opremaNaziv='" + opremaNaziv + '\'' +
                ", datumRezervacije='" + datumRezervacije + '\'' +
                ", datumVracanja='" + datumVracanja + '\'' +
                ", kolicina=" + kolicina +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * Dva objekta su jednaka ako imaju isti ID rezervacije.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rezervacija)) return false;
        Rezervacija that = (Rezervacija) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
