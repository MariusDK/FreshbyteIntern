package com.example.marius.first_db;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marius.first_db.classes.Persoana;
import com.example.marius.first_db.database.MyDBHandler;


public class Update extends Fragment {
    private MyDBHandler dbHandler;
    private TextInputLayout numeTextInput;
    private TextInputLayout prenumeTextInput;
    private TextInputLayout adresaTextInput;
    private Button updateButton;
    private Persoana persoana;
    public Update() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update,container,false);
        dbHandler = new MyDBHandler(getContext(),null,null,1);
        numeTextInput = v.findViewById(R.id.NumeInputLayoutU);
        prenumeTextInput = v.findViewById(R.id.PrenumeInputLayoutU);
        adresaTextInput = v.findViewById(R.id.AdresaInputLayoutU);
        updateButton = v.findViewById(R.id.UpdateButton);
        persoana = ((MainActivity)getActivity()).getPersoana();
        numeTextInput.getEditText().setText(persoana.getName());
        prenumeTextInput.getEditText().setText(persoana.getPrenume());
        adresaTextInput.getEditText().setText(persoana.getAdresa());
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nume = numeTextInput.getEditText().getText().toString();
                String prenume = prenumeTextInput.getEditText().getText().toString();
                String adresa = adresaTextInput.getEditText().getText().toString();
                System.out.println(dbHandler.updateHandler(persoana.getId(),nume,prenume,adresa));
                System.out.println("UPDATE UPDATE");
            }
        });
    }
}
