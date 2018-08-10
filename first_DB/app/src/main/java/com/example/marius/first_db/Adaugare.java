package com.example.marius.first_db;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marius.first_db.classes.Persoana;
import com.example.marius.first_db.database.MyDBHandler;


public class Adaugare extends Fragment {

    private MyDBHandler dbHandler;
    private Button buttonAdd;
    private Button buttonSelect;
    private Button buttonDelete;
    private Button buttonUpdate;
    private TextInputLayout nume;
    private TextInputLayout prenume;
    private TextInputLayout adresa;
    private ListenerInterfaceSelect listener;

    public Adaugare() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_adaugare, container, false);
        dbHandler = new MyDBHandler(getContext(),null,null,1);
        buttonAdd = v.findViewById(R.id.buttonAdd);
        buttonDelete = v.findViewById(R.id.buttonDelete);
        buttonSelect = v.findViewById(R.id.buttonSelect);
        buttonUpdate = v.findViewById(R.id.buttonUpdate);
        nume = v.findViewById(R.id.NumeInputLayout);
        prenume = v.findViewById(R.id.PrenumeInputLayout);
        adresa = v.findViewById(R.id.AdresaInputLayout);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeVal = nume.getEditText().getText().toString();
                String prenumeVal = prenume.getEditText().getText().toString();
                String adresaVal = adresa.getEditText().getText().toString();
                Persoana p = new Persoana();
                p.setName(numeVal);
                p.setPrenume(prenumeVal);
                p.setAdresa(adresaVal);
                dbHandler.addHandler(p);
            }
        });
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnActionPressed();
            }
        });
    }
    public interface ListenerInterfaceSelect
    {
        public void OnActionPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ListenerInterfaceSelect)context;
    }
}
