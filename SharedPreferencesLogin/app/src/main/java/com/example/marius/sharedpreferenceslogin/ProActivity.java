package com.example.marius.sharedpreferenceslogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProActivity extends AppCompatActivity {

    private TextView textView;
    private Button logout;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);
        textView = findViewById(R.id.textView);
        logout = findViewById(R.id.Logout);
        sharedPreferences = getSharedPreferences("file1",MODE_PRIVATE);
        textView.setText("Hello, "+sharedPreferences.getString("username",null));

    }

    @Override
    protected void onResume() {
        super.onResume();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("LogState",0);
                editor.commit();
                finish();
//                Intent intent = new Intent(MainActivity.this,ProActivity.class);
//                startActivity(intent);
            }
        });


    }
}
