package com.example.marius.first_db.classes;

public class Persoana {
    private int id;
    private String name;
    private String prenume;
    private String adresa;

    public Persoana() {
    }

    public Persoana(int id, String name, String prenume, String adresa) {
        this.id = id;
        this.name = name;
        this.prenume = prenume;
        this.adresa = adresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return id + " " + name +
                " " + prenume +
                " " + adresa;
    }
}
