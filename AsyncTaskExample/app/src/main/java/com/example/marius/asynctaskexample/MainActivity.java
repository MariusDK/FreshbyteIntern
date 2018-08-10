package com.example.marius.asynctaskexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText time;
    private TextView finalResult;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = (EditText) findViewById(R.id.in_time);
        button = (Button) findViewById(R.id.btn_run);
        finalResult = (TextView) findViewById(R.id.tv_result);
        progressBar = findViewById(R.id.determinateBar);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = time.getText().toString();
                runner.execute(sleepTime);
            }
        });


    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String>
    {
        private String resp;
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            dialog= ProgressDialog.show(MainActivity.this,"ProgressDialog","Wait for "+time.getText().toString()+" seconds");
        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Sleeping...");

            int time = Integer.parseInt(strings[0])*1000;

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e)
            {
                e.printStackTrace();
                resp = e.getMessage();
            }
           return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.INVISIBLE);
            finalResult.setText(s);
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            finalResult.setText(values[0]);
        }
    }
}
