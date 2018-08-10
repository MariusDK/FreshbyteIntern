package com.example.marius.miniaplicatie;

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

import com.example.marius.miniaplicatie.classes.Person;
import com.example.marius.miniaplicatie.database.DatabaseHelper;

public class RegisterFragment extends Fragment {
    private TextInputLayout numeInput;
    private TextInputLayout adresaInput;
    private TextInputLayout usernameInput;
    private TextInputLayout passwordInput;
    private Button registerButton;
    private DatabaseHelper databaseHelper;
    public RegisterFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register,container,false);
        numeInput = v.findViewById(R.id.numeId);
        adresaInput = v.findViewById(R.id.adresaId);
        usernameInput = v.findViewById(R.id.usernameId);
        passwordInput = v.findViewById(R.id.passwordId);
        registerButton = v.findViewById(R.id.registerId);
        databaseHelper = new DatabaseHelper(getContext(),null,null,1);
        return v;
    }

    @Override
    public void onResume() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nume = numeInput.getEditText().getText().toString();
                String adresa = adresaInput.getEditText().getText().toString();
                String username = usernameInput.getEditText().getText().toString();
                String password = passwordInput.getEditText().getText().toString();
                Person p = new Person();
                p.setName(nume);
                p.setAdresa(adresa);
                p.setUsername(username);
                p.setPassword(password);
                databaseHelper.addPerson(p);

            }
        });

        super.onResume();
    }
}
