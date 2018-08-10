package com.example.marius.proiectocupatii;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Add extends Fragment {

    private Button add;
    private TextInputLayout nume;
    private TextInputLayout prenume;
    private TextInputLayout adresa;
    private Spinner Ocupatii;
    private List<String> listOcupatii = new ArrayList(Arrays.asList("Elev","Student","Masterand","Programator","Manager"));
    private OnAddListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,container,false);

        add = view.findViewById(R.id.button6);
        nume = view.findViewById(R.id.numeId);
        prenume = view.findViewById(R.id.prenumeId);
        adresa = view.findViewById(R.id.AdresaId);
        Ocupatii = view.findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOcupatii);
        Ocupatii.setAdapter(arrayAdapter);
        return view;
    }

    public interface OnAddListener
    {
        public void onButtonPressed( String nume, String prenume, String adresa, int nr);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnAddListener) context;
        }catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString());
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nume_val = nume.getEditText().getText().toString();
                String prenume_val = prenume.getEditText().getText().toString();
                String adresa_val = adresa.getEditText().getText().toString();
                String ocupatii_val = Ocupatii.getSelectedItem().toString();

                if (ocupatii_val.equals("Elev"))
                {
                    listener.onButtonPressed(nume_val,prenume_val,adresa_val,1);

                }
                if (ocupatii_val.equals("Student"))
                {
                    listener.onButtonPressed(nume_val,prenume_val,adresa_val,2);
                }
                if (ocupatii_val.equals("Masterand"))
                {
                    listener.onButtonPressed(nume_val,prenume_val,adresa_val,3);
                }
                if (ocupatii_val.equals("Programator"))
                {
                    listener.onButtonPressed(nume_val,prenume_val,adresa_val,4);
                }
                if (ocupatii_val.equals("Manager"))
                {
                    listener.onButtonPressed(nume_val,prenume_val,adresa_val,5);
                }

            }
        });
    }

    public static Add newInstance() {

        Bundle args = new Bundle();

        Add fragment = new Add();
        fragment.setArguments(args);
        return fragment;
    }
}
