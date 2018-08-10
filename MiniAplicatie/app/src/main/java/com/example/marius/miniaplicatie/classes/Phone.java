package com.example.marius.miniaplicatie.classes;

public class Phone {
    private int id;
    private String marca;
    private String model;
    private String so;
    private int id_Person;

    public Phone(int id, String marca, String model, String so, int id_Person) {
        this.id = id;
        this.marca = marca;
        this.model = model;
        this.so = so;
        this.id_Person = id_Person;
    }

    public Phone() {
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

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public int getId_Person() {
        return id_Person;
    }

    public void setId_Person(int id_Person) {
        this.id_Person = id_Person;
    }

    @Override
    public String toString() {
        return id + "; " + marca + "; " + model+
                "; " + so;
    }
}
