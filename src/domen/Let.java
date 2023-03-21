/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 *
 * @author Božidar
 */
public class Let implements Serializable, OpstiDomenskiObjekat {

    private int sifraLeta;
    private java.sql.Timestamp datumIVremePolaska;
    private int trajanje;
    private Aerodrom aerodromPolazak;
    private Aerodrom aerodromDolazak;
    private Avion avion;
    private Pilot pilot;

    public Let() {

    }

    public Let(int sifraLeta, java.sql.Timestamp datumIVremePolaska, int trajanje, Aerodrom aerodromPolazak, Aerodrom aerodromDolazka, Avion avion, Pilot pilot) {
        this.sifraLeta = sifraLeta;
        this.datumIVremePolaska = datumIVremePolaska;
        this.trajanje = trajanje;
        this.aerodromPolazak = aerodromPolazak;
        this.aerodromDolazak = aerodromDolazka;
        this.avion = avion;
        this.pilot = pilot;
    }

    public int getSifraLeta() {
        return sifraLeta;
    }

    public void setSifraLeta(int sifraLeta) {
        this.sifraLeta = sifraLeta;
    }

    public java.sql.Timestamp getDatumIVremePolaska() {
        return datumIVremePolaska;
    }

    public void setDatumIVremePolaska(java.util.Date datumIVremePolaska1) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        datumIVremePolaska = java.sql.Timestamp.valueOf(sm.format(datumIVremePolaska1));
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Aerodrom getAerodromPolazak() {
        return aerodromPolazak;
    }

    public void setAerodromPolazak(Aerodrom aerodromPolazak) {
        this.aerodromPolazak = aerodromPolazak;
    }

    public Aerodrom getAerodromDolazak() {
        return aerodromDolazak;
    }

    public void setAerodromDolazak(Aerodrom aerodromDolazak) {
        this.aerodromDolazak = aerodromDolazak;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return sifraLeta + ", " + (datumIVremePolaska == null ? null : "'" + datumIVremePolaska + "'") + ", "
                + (trajanje == 0 ? null : trajanje) + ", " + (aerodromPolazak == null ? null : aerodromPolazak.getSifraAerodroma())
                + ", " + (aerodromDolazak == null ? null : aerodromDolazak.getSifraAerodroma())
                + ", " + (avion == null ? null : avion.getSifraAviona())
                + ", " + (pilot == null ? null : pilot.getIdPilota());

    }

    @Override
    public String postaviVrednostiAtributa() {
        return "sifraLeta = " + sifraLeta + ", " + "datumIVremePolaska = " + (datumIVremePolaska == null ? null : "'" + datumIVremePolaska + "'") + ", "
                + "trajanje = " + (trajanje == 0 ? null : "'" + trajanje + "'") + ", " + "aerodromPolazak = " + (aerodromPolazak == null ? null : aerodromPolazak.getSifraAerodroma())
                + ", " + "aerodromDolazak = " + (aerodromDolazak == null ? null : aerodromDolazak.getSifraAerodroma())
                + ", " + "avion = " + (avion == null ? null : avion.getSifraAviona())
                + ", " + "pilot = " + (pilot == null ? null : pilot.getIdPilota());
    }

    @Override
    public String vratiImeKlase() {
        return "Let";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraLeta = " + sifraLeta;
    }

    @Override
    public String toString() {
        return aerodromPolazak.getGrad() + "," + aerodromPolazak.getDrzava() + " - " + aerodromDolazak.getGrad() + "," + aerodromDolazak.getDrzava();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        //return "tipStaze LIKE '" + tipStaze + "'";

        String uslov = "";
        boolean flag = true;
        if (sifraLeta != 0) {
            uslov = "(sifraLeta = " + sifraLeta + " )";
            flag = false;
            if (aerodromPolazak != null) {
                uslov = uslov + " AND (aerodromPolazak IN (SELECT sifraAerodroma FROM aerodrom WHERE grad = '" + aerodromPolazak.getGrad() + "' ) )";

            }

            if (aerodromDolazak != null) {
                uslov = uslov + " AND (aerodromDolazak IN (SELECT sifraAerodroma FROM aerodrom WHERE grad = '" + aerodromDolazak.getGrad() + "' ) )";
            }

        } else {
            if (aerodromPolazak != null) {
                uslov = "(aerodromPolazak IN (SELECT sifraAerodroma FROM aerodrom WHERE grad = '" + aerodromPolazak.getGrad() + "' ) )";
                flag = false;

                if (aerodromDolazak != null) {
                    uslov = uslov + " AND (aerodromDolazak IN (SELECT sifraAerodroma FROM aerodrom WHERE grad = '" + aerodromDolazak.getGrad() + "' ) )";
                }
            }
        }

        if (flag) {
            uslov = "(aerodromDolazak IN (SELECT sifraAerodroma FROM aerodrom WHERE grad = '" + aerodromDolazak.getGrad() + "' ) )";
        }

        return "sifraLeta IN (SELECT sifraLeta FROM let WHERE" + uslov + ")";

    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {

        Let l = (Let) this;
        l.setSifraLeta(rs.getInt("sifraLeta"));
        l.setDatumIVremePolaska(rs.getTimestamp("datumIVremePolaska"));
        l.setTrajanje(rs.getInt("trajanje"));

        Aerodrom ap = new Aerodrom();
        ap.setSifraAerodroma(rs.getInt("aerodromPolazak"));
        l.setAerodromPolazak(ap);

        Aerodrom ad = new Aerodrom();
        ad.setSifraAerodroma(rs.getInt("aerodromDolazak"));
        l.setAerodromDolazak(ad);

        Avion a = new Avion();
        a.setSifraAviona(rs.getInt("avion"));
        l.setAvion(a);

        Pilot p = new Pilot();
        p.setIdPilota(rs.getInt("pilot"));
        l.setPilot(p);

    }

    @Override
    public String vratiNazivPK() {
        return "sifraLeta";
    }

    @Override
    public void povecajBroj(ResultSet rs) throws SQLException {
        this.setSifraLeta(rs.getInt("sifraLeta") + 1);
    }

    @Override
    public void postaviPocetniBroj() {
        setSifraLeta(1);
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Let();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 4;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i
    ) {
        if (i == 0) {
            return aerodromPolazak;
        }
        if (i == 1) {
            return aerodromDolazak;
        }
        if (i == 2) {
            return avion;
        }
        if (i == 3) {
            return pilot;
        }
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i
    ) {
        if (i == 0) {
            this.setAerodromPolazak((Aerodrom) vezo);
        }
        if (i == 1) {
            this.setAerodromDolazak((Aerodrom) vezo);
        }
        if (i == 2) {
            this.setAvion((Avion) vezo);
        }
        if (i == 3) {
            this.setPilot((Pilot) vezo);
        }

    }

    @Override
    public boolean equals(Object obj
    ) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Let other = (Let) obj;
        if (this.sifraLeta != other.sifraLeta) {
            return false;
        }
        return true;
    }

}
