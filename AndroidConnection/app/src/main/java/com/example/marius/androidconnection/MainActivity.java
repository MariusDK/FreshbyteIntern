package com.example.marius.androidconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView connectionStatusView;
    private TextView connectionResultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionStatusView = findViewById(R.id.connectionStatus);
        connectionResultView = findViewById(R.id.connectionResult);
        if (checkNetworkConnection()==true) {
            HTTPAsyncTask asyncTask = new HTTPAsyncTask();
            asyncTask.execute("http://hmkcode.com/examples/index.php");
        }
    }

    public boolean checkNetworkConnection()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null &&(isConnected = networkInfo.isConnected()))
        {
            connectionStatusView.setText("Connected "+networkInfo.getTypeName());
            connectionStatusView.setBackgroundColor(0xFF7CCC26);
        }
        else
        {
            connectionStatusView.setText("Not Connected");
            connectionStatusView.setBackgroundColor(0xFFFF0000);
        }
        return isConnected;
    }
    private String HttpGet(String myUrl) throws IOException{
        InputStream inputStream = null;
        String result = "";

        URL url = new URL(myUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        inputStream = conn.getInputStream();

        if (inputStream!=null)
            result = convertInputStreamToString(inputStream);
        else
            result = "Did not work!";

        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
    private class HTTPAsyncTask extends AsyncTask<String, Void, String>
    {


        @Override
        protected String doInBackground(String... strings) {

               try {
                    return HttpGet(strings[0]);
                } catch (IOException e) {
                    return "Unable to retrive web page. URL may be invalid.";
                }

        }

        @Override
        protected void onPostExecute(String s) {
            connectionResultView.setText(s);
        }
    }
}
