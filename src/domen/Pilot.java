package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Božidar
 */
public class Pilot implements Serializable, OpstiDomenskiObjekat {

    private int idPilota;
    private String ime;
    private String prezime;
    private String brojPasosa;
    private Date datumRodjenja;
    private String mestoBroavka;

    public Pilot() {
    }

    public Pilot(int idPilota, String ime, String prezime, String brojPasosa, Date datumRodjenja, String mestoBroavka) {
        this.idPilota = idPilota;
        this.ime = ime;
        this.prezime = prezime;
        this.brojPasosa = brojPasosa;
        this.datumRodjenja = datumRodjenja;
        this.mestoBroavka = mestoBroavka;
    }

    public int getIdPilota() {
        return idPilota;
    }

    public void setIdPilota(int idPilota) {
        this.idPilota = idPilota;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = datumRodjenja;
        datumRodjenja = java.sql.Date.valueOf(sm.format(dDatum));
        this.datumRodjenja = datumRodjenja;
    }

    public String getMestoBroavka() {
        return mestoBroavka;
    }

    public void setMestoBroavka(String mestoBroavka) {
        this.mestoBroavka = mestoBroavka;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return idPilota + ", " + (ime == null ? null : "'" + ime + "'") + ", "
                + (prezime == null ? null : "'" + prezime + "'") + ", " + (brojPasosa == null ? null : "'" + brojPasosa + "'")
                + ", " + (mestoBroavka == null ? null : "'" + mestoBroavka + "'") + ", " + (datumRodjenja == null ? null : "'" + datumRodjenja + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "idPilota = " + idPilota + ", " + "ime = " + (ime == null ? null : "'" + ime + "'") + ", "
                + "prezime = " + (prezime == null ? null : "'" + prezime + "'") + ", " + "brojPasosa = " + (brojPasosa == null ? null : "'" + brojPasosa + "'") + ", "
                + "mestoBoravka = " + (mestoBroavka == null ? null : "'" + mestoBroavka + "'") + ", " + "datumRodjenja = " + (datumRodjenja == null ? null : "'" + datumRodjenja + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "Pilot";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {

        if (ime == null && prezime == null) {
            return "idPilota = " + idPilota;
        }

        String uslov = "";
        boolean flag = true;
        if (idPilota != 0) {
            uslov = "idPilota = " + idPilota;
            flag = false;
            if (ime != null) {
                uslov = uslov + " AND ime = " + "'" + ime + "'";
            }

            if (prezime != null) {
                uslov = uslov + " AND prezime = " + "'" + prezime + "'";
            }

        } else {
            if (ime != null) {
                uslov = "ime = " + "'" + ime + "'";
                flag = false;
                if (prezime != null) {
                    uslov = uslov + " AND prezime = " + "'" + prezime + "'";
                }
            }
        }

        if (flag) {
            uslov = "prezime = " + "'" + prezime + "'";
        }

        return uslov;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        Pilot p = (Pilot) this;
        p.setIdPilota(rs.getInt("idPilota"));
        p.setIme(rs.getString("ime"));
        p.setPrezime(rs.getString("prezime"));
        p.setBrojPasosa(rs.getString("brojPasosa"));
        p.setMestoBroavka(rs.getString("mestoBoravka"));
        p.setDatumRodjenja(rs.getDate("datumRodjenja"));

    }

    @Override
    public String toString() {
        return "ID: " + idPilota + " - " + ime + " " + prezime;
    }

    @Override
    public String vratiNazivPK() {
        return "idPilota";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        this.setIdPilota(rs.getInt(vratiNazivPK()) + 1);
    }

    @Override
    public void postaviPocetniBroj() {
        this.setIdPilota(1);
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Pilot();
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
        final Pilot other = (Pilot) obj;
        if (this.idPilota != other.idPilota) {
            return false;
        }
        return true;
    }

}
