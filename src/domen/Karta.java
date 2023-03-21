/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Božidar
 */
public class Karta implements Serializable, OpstiDomenskiObjekat {

    private int sifraKarte;
    private String imePutnika;
    private String prezimePutnika;
    private String brojPasosa;
    private double ukupnaCena;
    private List<StavkaKarte> stavkeKarte;

    public Karta() {
    }

    public Karta(int sifraKarte, String imePutnika, String prezimePutnika, String brojPasosa, double ukupnaCena, List<StavkaKarte> stavkeKarte) {
        this.sifraKarte = sifraKarte;
        this.imePutnika = imePutnika;
        this.prezimePutnika = prezimePutnika;
        this.brojPasosa = brojPasosa;
        this.ukupnaCena = ukupnaCena;
        this.stavkeKarte = stavkeKarte;
    }

    public int getSifraKarte() {
        return sifraKarte;
    }

    public void setSifraKarte(int sifraKarte) {
        this.sifraKarte = sifraKarte;
    }

    public String getImePutnika() {
        return imePutnika;
    }

    public void setImePutnika(String imePutnika) {
        this.imePutnika = imePutnika;
    }

    public String getPrezimePutnika() {
        return prezimePutnika;
    }

    public void setPrezimePutnika(String prezimePutnika) {
        this.prezimePutnika = prezimePutnika;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public List<StavkaKarte> getStavkeKarte() {
        return stavkeKarte;
    }

    public void setStavkeKarte(List<StavkaKarte> stavkeKarte) {
        this.stavkeKarte = stavkeKarte;
    }

    @Override
    public String vratiVrednostiAtributa() {

        return sifraKarte + ", " + (imePutnika == null ? null : "'" + imePutnika + "'")
                + ", " + (prezimePutnika == null ? null : "'" + prezimePutnika + "'")
                + ", " + (brojPasosa == null ? null : "'" + brojPasosa + "'")
                + ", " + ukupnaCena;
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "sifraKarte = " + sifraKarte + ", "
                + "imePutnika = " + (imePutnika == null ? null : "'" + imePutnika + "'") + ", "
                + "prezimePutnika = " + (prezimePutnika == null ? null : "'" + prezimePutnika + "'") + ", "
                + "brojPasosa = " + (brojPasosa == null ? null : "'" + brojPasosa + "'") + ", "
                + "ukupnaCena = " + ukupnaCena;
    }

    @Override
    public String vratiImeKlase() {
        return "Karta";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraKarte = " + sifraKarte;
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "brojPasosa LIKE '" + brojPasosa + "'";
    }

    @Override
    public String toString() {
        return brojPasosa + "  " + imePutnika + " " + prezimePutnika + " " + ukupnaCena + " " + stavkeKarte.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        sifraKarte = rs.getInt("sifraKarte");
        ukupnaCena = rs.getDouble("ukupnaCena");
        imePutnika = rs.getString("imePutnika");
        prezimePutnika = rs.getString("prezimePutnika");
        brojPasosa = rs.getString("brojPasosa");
        stavkeKarte = new ArrayList<>();

    }

    @Override
    public String vratiNazivPK() {
        return "sifraKarte";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        sifraKarte = rs.getInt("sifraKarte") + 1;
    }

    @Override
    public void postaviPocetniBroj() {
        sifraKarte = 1;
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Karta();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            StavkaKarte stavkaKarte = new StavkaKarte();
            stavkaKarte.setKarta(this);
            return stavkaKarte;
        }
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int j) {
        if (j < stavkeKarte.size()) {
            stavkeKarte.set(j, (StavkaKarte) vezo);
        }
    }

    @Override
    public int vratiBrojSlogovaVezanogObjekta(int i) {
        if (i == 0) {
            return stavkeKarte.size();
        }
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiSlogVezanogObjekta(int i, int j) {
        if (i == 0) {
            return stavkeKarte.get(j);
        }
        return null;
    }

    @Override
    public void kreirajVezaniObjekat(int brojStavki, int i) {
        if (i == 0) {
            stavkeKarte = new ArrayList<>();
            for (int j = 0; j < brojStavki; j++) {
                StavkaKarte stavkaKarte = new StavkaKarte();
                stavkaKarte.setKarta(this);
                stavkeKarte.add(stavkaKarte);
            }
        }
    }

    @Override
    public void napuni(ResultSet rs, int brojSloga, int i) throws Exception {
        stavkeKarte.get(brojSloga).setKarta(this);
        stavkeKarte.get(brojSloga).setRedniBroj(brojSloga + 1);
        stavkeKarte.get(brojSloga).setCenaStavke(rs.getDouble("cenaStavke"));
        stavkeKarte.get(brojSloga).setBrojSedista(rs.getInt("brojSedista"));
        stavkeKarte.get(brojSloga).setKlasaTokomLeta(KlasaTokomLeta.valueOf(rs.getString("klasaTokomLeta")));
        stavkeKarte.get(brojSloga).setTerminal(rs.getInt("terminal"));

        Let let = new Let();
        let.setSifraLeta(rs.getInt("let"));
        stavkeKarte.get(brojSloga).setLet(let);

        stavkeKarte.get(brojSloga).setCenaStavke(ukupnaCena);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.sifraKarte ^ (this.sifraKarte >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Karta other = (Karta) obj;
        if (this.sifraKarte != other.sifraKarte) {
            return false;
        }
        return true;
    }

}
