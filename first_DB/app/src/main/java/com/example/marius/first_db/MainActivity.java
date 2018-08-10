package com.example.marius.first_db;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marius.first_db.classes.Persoana;

public class MainActivity extends AppCompatActivity implements Adaugare.ListenerInterfaceSelect, LoadList.onUpdateListener {

    private Persoana persoana;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Adaugare adaugare = new Adaugare();
        transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.activitiFrameLayout, adaugare);
        transaction.addToBackStack(null);

        transaction.commit();

    }

    @Override
    public void OnActionPressed() {
        transaction = getSupportFragmentManager().beginTransaction();
        LoadList loadList = new LoadList();
        transaction.replace(R.id.activitiFrameLayout,loadList);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void OnActionPressed2() {
        Update update = new Update();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activitiFrameLayout,update);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setPersoana(int id, String nume, String prenume, String adresa)
    {
        persoana = new Persoana(id,nume,prenume,adresa);
    }

    public Persoana getPersoana() {
        return persoana;
    }
}
