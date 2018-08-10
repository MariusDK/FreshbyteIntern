package com.example.marius.loginapprelativelayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marius.loginapprelativelayout.Validatori.Validator;

public class Register_Activity extends AppCompatActivity {

    private TextInputLayout emailInput;
    private TextInputLayout passwordInput1;
    private TextInputLayout passwordInput2;
    private Button RegisterButton;
    private Validator validator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailInput = findViewById(R.id.Email_Input);
        passwordInput1 = findViewById(R.id.PasswordInput1);
        passwordInput2 = findViewById(R.id.PasswordInput2);
        RegisterButton = findViewById(R.id.RegisterButton);
        validator = new Validator();
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass1 = passwordInput1.getEditText().getText().toString();
                String pass2 = passwordInput2.getEditText().getText().toString();
                String email = emailInput.getEditText().getText().toString();

                if (validator.validateRegister(email, pass1, pass2)) {
                    if (validator.checkPassword(pass1,pass2))
                    {
                        Intent intent = new Intent(Register_Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register_Activity.this);
                        builder.setTitle("ERROR");
                        builder.setMessage("Passwords don't match");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.show();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register_Activity.this);
                    builder.setTitle("ERROR");
                    builder.setMessage("Please enter your info!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
        passwordInput2.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i== EditorInfo.IME_ACTION_DONE)
                {
                    String pass1 = passwordInput1.getEditText().getText().toString();
                    String pass2 = passwordInput2.getEditText().getText().toString();
                    String email = emailInput.getEditText().getText().toString();

                    if (validator.validateRegister(email, pass1, pass2)) {
                        if (validator.checkPassword(pass1,pass2))
                        {
                            Intent intent = new Intent(Register_Activity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Register_Activity.this);
                            builder.setTitle("ERROR");
                            builder.setMessage("Passwords don't match");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register_Activity.this);
                        builder.setTitle("ERROR");
                        builder.setMessage("Please enter your info!");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                    return true;
                }
                return false;
            }
        });

    }
}
