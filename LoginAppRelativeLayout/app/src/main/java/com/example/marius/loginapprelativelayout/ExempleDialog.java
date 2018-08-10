package com.example.marius.loginapprelativelayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marius.loginapprelativelayout.MainActivity;
import com.example.marius.loginapprelativelayout.R;

public class ExempleDialog extends AppCompatDialogFragment {
    private TextInputLayout numeInput;
    private TextInputLayout prenumeInput;
    private TextInputLayout adresaInput;
    private String nume;
    private String prenume;
    private String adresa;
    private boolean val;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add, null);
        val = false;
        builder.setView(view);
        builder.setTitle("Adaugare persoana");
        builder.setCancelable(true);
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nume = numeInput.getEditText().getText().toString();
                prenume = prenumeInput.getEditText().getText().toString();
                adresa = adresaInput.getEditText().getText().toString();
                listener.applyTexts(nume,prenume,adresa);
                dismiss();
            }
        });
        numeInput = view.findViewById(R.id.nume);
        prenumeInput = view.findViewById(R.id.prenume);
        adresaInput = view.findViewById(R.id.adresa);
        adresaInput.getEditText().setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE){
                    nume = numeInput.getEditText().getText().toString();
                    prenume = prenumeInput.getEditText().getText().toString();
                    adresa = adresaInput.getEditText().getText().toString();
                    listener.applyTexts(nume,prenume,adresa);
                    dismiss();



//                    Button yesButton =builder.show().getButton(AlertDialog.BUTTON_POSITIVE);
//                    yesButton.performClick();
                    return true;
                }
                return false;
            }
        });

        return builder.create();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ExampleDialogListener) context;
    }

    public interface ExampleDialogListener{
        void applyTexts(String nume, String prenume, String adresa);

    }
}
