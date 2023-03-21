/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Božidar
 */
public class Aerodrom implements Serializable, OpstiDomenskiObjekat {

    private int sifraAerodroma;
    private String imeAerodroma;
    private String grad;
    private String drzava;

    public Aerodrom() {
    }

    public Aerodrom(int sifraAerodroma, String imeAerodroma, String grad, String drzava) {
        this.sifraAerodroma = sifraAerodroma;
        this.imeAerodroma = imeAerodroma;
        this.grad = grad;
        this.drzava = drzava;
    }

    public int getSifraAerodroma() {
        return sifraAerodroma;
    }

    public void setSifraAerodroma(int sifraAerodroma) {
        this.sifraAerodroma = sifraAerodroma;
    }

    public String getImeAerodroma() {
        return imeAerodroma;
    }

    public void setImeAerodroma(String imeAerodroma) {
        this.imeAerodroma = imeAerodroma;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return sifraAerodroma + ", " + (imeAerodroma == null ? null : "'" + imeAerodroma + "'") + ", "
                + (grad == null ? null : "'" + grad + "'") + ", "
                + (drzava == null ? null : "'" + drzava + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "sifraAerodroma = " + sifraAerodroma + ", " + "imeAerodroma = " + (imeAerodroma == null ? null : "'" + imeAerodroma + "'") + ", "
                + "grad = " + (grad == null ? null : "'" + grad + "'")
                + ", " + "drzava = " + (drzava == null ? null : "'" + drzava + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "Aerodrom";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {

        if (grad == null && drzava == null) {
            return "sifraAerodroma = " + sifraAerodroma;
        }

        String uslov = "";
        boolean flag = true;
        if (sifraAerodroma != 0) {
            uslov = "sifraAerodroma = " + sifraAerodroma;
            flag = false;
            if (grad != null) {
                uslov = uslov + " AND grad = " + "'" + grad + "'";
            }

            if (drzava != null) {
                uslov = uslov + " AND drzava = " + "'" + drzava + "'";
            }

        } else {
            if (grad != null) {
                uslov = "grad = " + "'" + grad + "'";
                flag = false;
                if (drzava != null) {
                    uslov = uslov + " AND drzava = " + "'" + drzava + "'";
                }
            }
        }

        if (flag) {
            uslov = "drzava = " + "'" + drzava + "'";
        }

        return uslov;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        Aerodrom a = (Aerodrom) this;

        a.setSifraAerodroma(rs.getInt("sifraAerodroma"));
        a.setImeAerodroma(rs.getString("imeAerodroma"));
        a.setDrzava(rs.getString("drzava"));
        a.setGrad(rs.getString("grad"));

    }

    @Override
    public String toString() {
        return imeAerodroma + " - " + grad + "," + drzava;
    }

    @Override
    public String vratiNazivPK() {
        return "sifraAerodroma";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        this.setSifraAerodroma(rs.getInt(vratiNazivPK()) + 1);
    }

    @Override
    public void postaviPocetniBroj() {
        this.setSifraAerodroma(1);
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Aerodrom();
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
        final Aerodrom other = (Aerodrom) obj;
        if (this.sifraAerodroma != other.sifraAerodroma) {
            return false;
        }
        return true;
    }

}
