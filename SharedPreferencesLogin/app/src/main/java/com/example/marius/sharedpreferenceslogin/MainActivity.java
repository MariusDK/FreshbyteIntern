package com.example.marius.sharedpreferenceslogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout usernameInput;
    private TextInputLayout passwordInput;
    private Button loginButton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("file1",Context.MODE_PRIVATE);
        usernameInput = findViewById(R.id.usernameID);
        passwordInput = findViewById(R.id.passwordID);
        loginButton = findViewById(R.id.loginID);
        int status = sharedPreferences.getInt("LogState",0);
        if (status==1)
        {
            Intent intent = new Intent(MainActivity.this,ProActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getEditText().getText().toString();
                String password = passwordInput.getEditText().getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",username);
                editor.putString("password",password);
                editor.putInt("LogState",1);
                editor.commit();
                Intent intent = new Intent(MainActivity.this,ProActivity.class);
                startActivity(intent);
            }
        });
    }
}
