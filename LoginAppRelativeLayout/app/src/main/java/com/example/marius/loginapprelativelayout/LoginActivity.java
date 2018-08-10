package com.example.marius.loginapprelativelayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marius.loginapprelativelayout.Validatori.Validator;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout emailInput;
    private TextInputLayout passwordInput;
    private Button loginButton;
    private Button registerButton;
    private Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = (TextInputLayout)findViewById(R.id.EmailInput);
        passwordInput = (TextInputLayout) findViewById(R.id.PasswordInput);
        loginButton = (Button) findViewById(R.id.LoginButton);
        registerButton = (Button) findViewById(R.id.RegisterButton2);
        validator = new Validator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =  emailInput.getEditText().getText().toString();
                String password =  passwordInput.getEditText().getText().toString();
                if (validator.validateLogin(email,password)){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("key",email+ " "+ password);
                startActivity(intent);}
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
        passwordInput.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i== EditorInfo.IME_ACTION_DONE)
                {
                    String email =  emailInput.getEditText().getText().toString();
                    String password =  passwordInput.getEditText().getText().toString();
                    if (validator.validateLogin(email,password)){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("key",email+ " "+ password);
                        startActivity(intent);}
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,Register_Activity.class);
                startActivity(intent);
            }
        });

    }
}
