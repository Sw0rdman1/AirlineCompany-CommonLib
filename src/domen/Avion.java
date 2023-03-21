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

/**
 *
 * @author Božidar
 */
public class Avion implements Serializable, OpstiDomenskiObjekat {

    private int sifraAviona;
    private String modelAviona;
    private int brojMestaEkonomska;
    private int brojMestaBiznis;
    private int godinaProizvodnje;

    public Avion() {
    }

    public Avion(int sifraAviona, String modelAviona, int brojMestaEkonomskaKlasa, int brojMestaBiznisKlasa, int godinaProizvodnje) {
        this.sifraAviona = sifraAviona;
        this.modelAviona = modelAviona;
        this.brojMestaEkonomska = brojMestaEkonomskaKlasa;
        this.brojMestaBiznis = brojMestaBiznisKlasa;
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public int getSifraAviona() {
        return sifraAviona;
    }

    public void setSifraAviona(int sifraAviona) {
        this.sifraAviona = sifraAviona;
    }

    public String getModelAviona() {
        return modelAviona;
    }

    public void setModelAviona(String modelAviona) {
        this.modelAviona = modelAviona;
    }

    public int getBrojMestaEkonomska() {
        return brojMestaEkonomska;
    }

    public void setBrojMestaEkonomska(int brojMestaEkonomska) {
        this.brojMestaEkonomska = brojMestaEkonomska;
    }

    public int getBrojMestaBiznis() {
        return brojMestaBiznis;
    }

    public void setBrojMestaBiznis(int brojMestaBiznis) {
        this.brojMestaBiznis = brojMestaBiznis;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return sifraAviona + ", " + (modelAviona == null ? null : "'" + modelAviona + "'") + ", "
                + (brojMestaEkonomska == 0 ? null : "'" + brojMestaEkonomska + "'") + ", "
                + (brojMestaBiznis == 0 ? null : "'" + brojMestaBiznis + "'") + ", "
                + (godinaProizvodnje == 0 ? null : "'" + godinaProizvodnje + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "sifraAviona = " + sifraAviona + ", " + "modelAviona = " + (modelAviona == null ? null : "'" + modelAviona + "'") + ", "
                + "brojMestaEkonomska = " + (brojMestaEkonomska == 0 ? null : "'" + brojMestaEkonomska + "'")
                + "brojMestaBiznis = " + (brojMestaBiznis == 0 ? null : "'" + brojMestaBiznis + "'") + ", "
                + ", " + "godinaProizvodnje = " + (godinaProizvodnje == 0 ? null : "'" + godinaProizvodnje + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "Avion";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {

        if (modelAviona == null) {
            return "sifraAviona = " + sifraAviona;
        }

        String uslov = "";
        if (sifraAviona != 0) {
            uslov = "sifraAviona = " + sifraAviona;
            if (modelAviona != null) {
                uslov = uslov + " AND modelAviona = " + "'" + modelAviona + "'";
            }

        } else {
            if (modelAviona != null) {
                uslov = "modelAviona = " + "'" + modelAviona + "'";

            }
        }

        return uslov;

    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        Avion a = (Avion) this;

        a.setSifraAviona(rs.getInt("sifraAviona"));
        a.setModelAviona(rs.getString("modelAviona"));
        a.setBrojMestaEkonomska(rs.getInt("brojMestaEkonomska"));
        a.setBrojMestaBiznis(rs.getInt("brojMestaBiznis"));
        a.setGodinaProizvodnje(rs.getInt("godinaProizvodnje"));

    }

    @Override
    public String toString() {
        return "ID: " + sifraAviona + " - " + modelAviona + " - BM:" + (brojMestaBiznis + brojMestaEkonomska);
    }

    @Override
    public String vratiNazivPK() {
        return "sifraAviona";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        this.setSifraAviona(rs.getInt(vratiNazivPK()) + 1);
    }

    @Override
    public void postaviPocetniBroj() {
        this.setSifraAviona(1);
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Avion();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        final Avion other = (Avion) obj;
        if (this.sifraAviona != other.sifraAviona) {
            return false;
        }
        return true;
    }

}
