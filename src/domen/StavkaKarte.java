/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Božidar
 */
public class StavkaKarte implements Serializable, OpstiDomenskiObjekat {

    private Karta karta;
    private int redniBroj;
    private double cenaStavke;
    private KlasaTokomLeta klasaTokomLeta;
    private int brojSedista;
    private int terminal;
    private Let let;

    public StavkaKarte() {
    }

    public StavkaKarte(Karta karta, int redniBroj, double cenaStavke, KlasaTokomLeta klasaTokomLeta, int brojSedista, int terminal, Let let) {
        this.karta = karta;
        this.redniBroj = redniBroj;
        this.cenaStavke = cenaStavke;
        this.klasaTokomLeta = klasaTokomLeta;
        this.brojSedista = brojSedista;
        this.terminal = terminal;
        this.let = let;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public KlasaTokomLeta getKlasaTokomLeta() {
        return klasaTokomLeta;
    }

    public void setKlasaTokomLeta(KlasaTokomLeta klasaTokomLeta) {
        this.klasaTokomLeta = klasaTokomLeta;
    }

    public int getBrojSedista() {
        return brojSedista;
    }

    public void setBrojSedista(int brojSedista) {
        this.brojSedista = brojSedista;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }

    public Let getLet() {
        return let;
    }

    public void setLet(Let let) {
        this.let = let;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return (karta == null ? null : karta.getSifraKarte()) + ", " + redniBroj + ", " + cenaStavke + ", '" + klasaTokomLeta + "'"
                + ", '" + brojSedista + "'" + ", '" + terminal + "'" + ", " + (let == null ? null : let.getSifraLeta());
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "karta= " + (karta == null ? null : karta.getSifraKarte()) + ", " + "RB = " + redniBroj + ", " + "cenaStavke = "
                + cenaStavke + ", " + "klasaTokomLeta = " + "'" + klasaTokomLeta + "'" + ", " + "brojSedista = "
                + brojSedista + ", " + "terminal = " + "'" + terminal + "'" + ", " + "let = " + (let == null ? null : let.getSifraLeta());
    }

    @Override
    public String vratiImeKlase() {
        return "stavkaKarte";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        System.out.println("Sifra karte: " + karta);
        return "karta = " + karta.getSifraKarte() + " AND " + "RB = " + redniBroj;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        karta.setSifraKarte(rs.getInt("karta"));

        redniBroj = rs.getInt("rb");

        cenaStavke = rs.getDouble("cenaStavke");

        if (rs.getString("klasaTokomLeta") == "Eko") {
            klasaTokomLeta = klasaTokomLeta.Eko;
        } else {
            klasaTokomLeta = klasaTokomLeta.Biz;
        }

        brojSedista = rs.getInt("brojSedista");
        terminal = rs.getInt("terminal");

        Let l = new Let();
        l.setSifraLeta(rs.getInt("let"));
        let = l;

        System.out.println(let.getSifraLeta());

    }

    @Override
    public String vratiNazivPK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviPocetniBroj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        StavkaKarte stavka = new StavkaKarte();
        stavka.setKarta(karta);
        return stavka;
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 1;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            return let;
        }

        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i == 0) {
            let = (Let) vezo;
        }

    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "karta = " + karta.getSifraKarte();
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
        final StavkaKarte other = (StavkaKarte) obj;
        if (this.redniBroj != other.redniBroj) {
            return false;
        }
        if (!Objects.equals(this.karta, other.karta)) {
            return false;
        }
        return true;
    }

}
