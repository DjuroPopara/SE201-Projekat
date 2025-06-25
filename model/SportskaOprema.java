package model;

import java.util.Objects;

/**
 * Predstavlja stavku sportske opreme dostupne za iznajmljivanje.
 * <p>
 * Svaka oprema ima jedinstveni ID, naziv, kategoriju (tipId),
 * status dostupnosti, cenu, količinu i lokaciju skladištenja.
 * </p>
 *
 * @author DjuroPopara6271
 */
public class SportskaOprema {

    /** Jedinstveni identifikator sportske opreme. */
    private int id;

    /** Naziv sportske opreme. */
    private String naziv;

    /** ID kategorije (tip opreme). */
    private int tipId;

    /** {@code true} ako je oprema dostupna za iznajmljivanje. */
    private boolean dostupnost;

    /** Cena iznajmljivanja (u valuti sistema). */
    private double cena;

    /** Količina komada na raspolaganju. */
    private int kolicina;

    /** Lokacija skladišta gde se oprema nalazi. */
    private String lokacija;

    /**
     * Konstruktor za kreiranje nove stavke opreme sa svim parametrima.
     *
     * @param id         Jedinstveni ID opreme
     * @param naziv      Naziv opreme
     * @param tipId      ID kategorije opreme
     * @param dostupnost {@code true} ako je oprema dostupna
     * @param cena       Cena iznajmljivanja
     * @param kolicina   Broj dostupnih komada
     * @param lokacija   Lokacija skladišta
     */
    public SportskaOprema(int id,
                          String naziv,
                          int tipId,
                          boolean dostupnost,
                          double cena,
                          int kolicina,
                          String lokacija) {
        this.id = id;
        this.naziv = naziv;
        this.tipId = tipId;
        this.dostupnost = dostupnost;
        this.cena = cena;
        this.kolicina = kolicina;
        this.lokacija = lokacija;
    }

    /** @return Jedinstveni ID opreme */
    public int getId() {
        return id;
    }

    /** @param id Novi ID opreme */
    public void setId(int id) {
        this.id = id;
    }

    /** @return Naziv opreme */
    public String getNaziv() {
        return naziv;
    }

    /** @param naziv Novi naziv opreme */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /** @return ID kategorije (tip opreme) */
    public int getTipId() {
        return tipId;
    }

    /** @param tipId Novi ID kategorije */
    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    /** @return {@code true} ako je oprema dostupna */
    public boolean isDostupnost() {
        return dostupnost;
    }

    /** @param dostupnost {@code true} ako je oprema dostupna */
    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }

    /** @return Cena iznajmljivanja */
    public double getCena() {
        return cena;
    }

    /** @param cena Nova cena iznajmljivanja */
    public void setCena(double cena) {
        this.cena = cena;
    }

    /** @return Broj dostupnih komada */
    public int getKolicina() {
        return kolicina;
    }

    /** @param kolicina Nova količina dostupnih komada */
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    /** @return Lokacija skladišta */
    public String getLokacija() {
        return lokacija;
    }

    /** @param lokacija Nova lokacija skladišta */
    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Prikazuje sve atribute opreme.
     * </p>
     */
    @Override
    public String toString() {
        return "SportskaOprema{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", tipId=" + tipId +
                ", dostupnost=" + dostupnost +
                ", cena=" + cena +
                ", kolicina=" + kolicina +
                ", lokacija='" + lokacija + '\'' +
                '}';
    }

    /**
     * Dva objekta su jednaka ako imaju isti ID.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportskaOprema)) return false;
        SportskaOprema that = (SportskaOprema) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
