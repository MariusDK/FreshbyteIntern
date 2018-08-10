package com.example.marius.fragmentbasic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BlankFragment fragment = new BlankFragment();
        fragmentTransaction.add(R.id.fragment_id,fragment);


        Fragment fragment1 = new ExampleFragment();
        fragmentTransaction.replace(R.id.fragment_id,fragment1);
        fragmentTransaction.addToBackStack(null);
        ExampleFragment fragment2 =(ExampleFragment) fragmentManager.findFragmentById(R.id.fra_id);



        fragmentTransaction.commit();
    }
    
}
