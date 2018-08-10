package com.example.marius.loginapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout emailInput;
    private TextInputLayout password1Input;
    private TextInputLayout password2Input;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emailInput = (TextInputLayout) findViewById(R.id.emailInput);
        password1Input = (TextInputLayout) findViewById(R.id.password1Input);
        password2Input = (TextInputLayout) findViewById(R.id.password2Input);
        registerButton = (Button) findViewById(R.id.registerButton2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass1 = password1Input.getEditText().getText().toString();
                String pass2 = password2Input.getEditText().getText().toString();

                if (pass1.equals(pass2))
                {
                    Intent intent3 = new Intent(RegisterActivity.this,MainActivity.class);
                    intent3.putExtra("key", emailInput.getEditText().getText().toString()+ " " + pass1);
                    startActivity(intent3);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Password didn't match!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
