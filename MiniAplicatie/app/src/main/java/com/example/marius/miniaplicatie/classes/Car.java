package com.example.marius.miniaplicatie.classes;

public class Car {
    private int id;
    private String marca;
    private String model;
    private String Tip_combustibil;
    private int id_Person;

    public Car(int id, String marca, String model, String tip_combustibil, int id_Person) {
        this.id = id;
        this.marca = marca;
        this.model = model;
        Tip_combustibil = tip_combustibil;
        this.id_Person = id_Person;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTip_combustibil() {
        return Tip_combustibil;
    }

    public void setTip_combustibil(String tip_combustibil) {
        Tip_combustibil = tip_combustibil;
    }

    public int getId_Persoana() {
        return id_Person;
    }

    public void setId_Persoana(int id_Persoana) {
        this.id_Person = id_Persoana;
    }

    @Override
    public String toString() {
        return id + "; " + marca + "; " + model + "; " + Tip_combustibil;
    }
}
