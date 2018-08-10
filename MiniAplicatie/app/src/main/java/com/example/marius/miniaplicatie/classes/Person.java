package com.example.marius.miniaplicatie.classes;

public class Person {
    private int id;
    private String name;
    private String adresa;
    private String username;
    private String password;

    public Person(int id, String name, String adresa, String username, String password) {
        this.id = id;
        this.name = name;
        this.adresa = adresa;
        this.username = username;
        this.password = password;
    }

    public Person() {
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
