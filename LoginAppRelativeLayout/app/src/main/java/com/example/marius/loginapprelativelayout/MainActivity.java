package com.example.marius.loginapprelativelayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.marius.loginapprelativelayout.Validatori.Validator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExempleDialog.ExampleDialogListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton button;
    private List<Persoana> list = new ArrayList<>();
    private Persoana p;
    private Validator validator;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //aici
        mAdapter = new RecyclerAdapter(list);

        mRecyclerView.setAdapter(mAdapter);
        button = findViewById(R.id.Add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK)
            {
                String result = data.getStringExtra("result");
                String[] resultList = result.split(" ");
                Persoana p = new Persoana(resultList[0],resultList[1],resultList[2]);
                list.add(p);
                updateList();
            }
            if (resultCode == Activity.RESULT_CANCELED)
            {
                super.onActivityResult(requestCode, resultCode, data);
            }

        }
    }
    private void updateList()
    {
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }
    public void openDialog()
    {
        ExempleDialog exampleDialog = new ExempleDialog();
        exampleDialog.show(getSupportFragmentManager(),"Exemplu");
    }

    @Override
    public void applyTexts(String nume, String prenume, String adresa) {
        Persoana p = new Persoana(nume,prenume,adresa);
        list.add(p);
        updateList();
    }
}
