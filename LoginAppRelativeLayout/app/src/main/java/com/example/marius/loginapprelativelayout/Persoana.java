package com.example.marius.loginapprelativelayout;

public class Persoana {
    private String nume;
    private String prenume;
    private String adresa;

    public Persoana(String nume, String prenume, String adresa) {
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
    }

    public Persoana() {
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

    @Override
    public String toString() {
        return "Nume: "+ nume +"\\n"+ "Prenume: " + prenume + "\\n" + "Adresa: "+ adresa;
    }
}
