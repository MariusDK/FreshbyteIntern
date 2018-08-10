package com.example.marius.loginapprelativelayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marius.loginapprelativelayout.Validatori.Validator;

public class AddActivity extends AppCompatActivity {
    private TextInputLayout numeInput;
    private TextInputLayout prenumeInput;
    private TextInputLayout adresaInput;
    private Button button;
    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        numeInput = findViewById(R.id.nume);
        prenumeInput = findViewById(R.id.prenume);
        adresaInput = findViewById(R.id.adresa);
        //button = findViewById(R.id.button);
        validator = new Validator();




    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nume = ""+numeInput.getEditText().getText().toString();
                String prenume = ""+prenumeInput.getEditText().getText().toString();
                String adresa = ""+adresaInput.getEditText().getText().toString();
                String s = nume+" "+prenume+" "+adresa;



                if (validator.validateAdaugare(nume,prenume,adresa))
                {
                    if ((validator.ValidareDimensiuneNume(nume)&&(validator.ValidareDimeniunePrenume(prenume))&&(validator.ValidareDimeniuneAdresa(adresa))))

                    {
                        Intent ReturnIntent = new Intent();
                        ReturnIntent.putExtra("result", s);
                        setResult(Activity.RESULT_OK, ReturnIntent);
                        finish();
                    }
                    else
                    {
                        if (validator.ValidareDimensiuneNume(nume)==false)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                            builder.setTitle("ERROR Nume");
                            builder.setMessage("Invalid nume size, max 10 characters!");
                            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();
                        }
                        if (validator.ValidareDimensiuneNume(prenume)==false)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                            builder.setTitle("ERROR Prenume");
                            builder.setMessage("Invalid prenume size, max 10 characters!");
                            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();
                        }
                        if (validator.ValidareDimeniuneAdresa(adresa)==false)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                            builder.setTitle("ERROR Adresa");
                            builder.setMessage("Invalid adresa size, between 10 and 30 characters!");
                            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();

                        }
                    }
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    builder.setTitle("ERROR");
                    builder.setMessage("Please enter your info!");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
    }
}
