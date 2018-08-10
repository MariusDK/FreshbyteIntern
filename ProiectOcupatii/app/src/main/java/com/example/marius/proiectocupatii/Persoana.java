package com.example.marius.proiectocupatii;

public class Persoana {
    private String nume;
    private String prenume;
    private String adresa;
    private int ocupatie;

    public Persoana() {
    }

    public Persoana(String nume, String prenume, String adresa, int ocupatie) {
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.ocupatie = ocupatie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getOcupatie() {
        return ocupatie;
    }

    public void setOcupatie(int ocupatie) {
        this.ocupatie = ocupatie;
    }
}
