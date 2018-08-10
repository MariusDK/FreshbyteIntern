package com.example.marius.httpurlconnectionapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.marius.httpurlconnectionapp.classes.Error;
import com.example.marius.httpurlconnectionapp.classes.Succes;
import com.example.marius.httpurlconnectionapp.classes.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        new SendPostRequest().execute();
    }
    public class SendPostRequest extends AsyncTask<String,Void,String>
    {
        String result = "";
        @Override
        protected void onPreExecute() { }

        @Override
        protected String doInBackground(String... strings) {

            try {
                return HttpPost();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }
    }
    private Error getError(String response)
    {
        Gson gson = new GsonBuilder().create();
        Error error = gson.fromJson(response,Error.class);
        return error;

    }
    private Succes getSucces(String response)
    {
        Gson gson = new GsonBuilder().create();
        Succes succes = gson.fromJson(response,Succes.class);
        return succes;
    }
    private String convertUserToJson(User user)
    {
        Gson gson = new Gson();

        //gson.toJson(user,new FileWriter("file.json"));
        String jsonInStringUser = gson.toJson(user);
        return jsonInStringUser;

    }
    private String HttpPost() throws IOException
    {
        URL url = new URL("https://ancient-earth-13943.herokuapp.com/api/users/register");

            try {
                /*JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", "marius367@gmail.com");
                postDataParams.put("name", "marius");
                postDataParams.put("password", "pass");
                postDataParams.put("confirm", "pass");
                Log.e("params", postDataParams.toString());
*/
                User user = new User();
                user.setEmail("marius2@gmail.com");
                user.setName("marius");
                user.setPassword("pass");
                user.setConfirm("pass");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000/*milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");


                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String str = convertUserToJson(user);
                writer.write(str);
                System.out.println(1);
                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
                System.out.println();
                System.out.println(responseCode);
                /*if (responseCode == HttpsURLConnection.HTTP_OK) {


                } else {
                    return new String("false: " + responseCode);
                }*/
                System.out.println(conn.getErrorStream());
                //int status = conn.getResponseCode();
                //in = connection.getErrorStream();
                BufferedReader in;

                if (responseCode == HttpsURLConnection.HTTP_OK)
                {

                    in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    System.out.println(in);

                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    Succes succes = getSucces(sb.toString());
                    in.close();
                    return succes.getResult();

                }
                else
                {
                    in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    System.out.println(in);
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    Error error = getError(sb.toString());
                    return error.getError();
                }
                //BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

//                StringBuffer sb = new StringBuffer("");
//                String line = "";
//                while ((line = in.readLine()) != null) {
//                    sb.append(line);
//                    break;
//                }
//                in.close();
//                System.out.println(sb.toString());
//                return sb.toString();
//
            }
        catch (Exception e) {
                System.out.println("Exception: "+e.getMessage());
                return new String("Exception: "+e.getMessage());
        }
    }
    private String getPostDataString(JSONObject params) throws Exception
    {
        int car = 0;
        StringBuilder result = new StringBuilder();
        boolean first = true;
        result.append("{");
        Iterator<String> itr = params.keys();

        while (itr.hasNext())
        {

            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append(',');
            result.append('"');
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append('"');
            result.append(":");
            result.append('"');
            result.append(URLEncoder.encode(value.toString(),"UTF-8"));
            result.append('"');

        }
        result.replace(20,23,"@");

        result.append("}");
        return result.toString();
    }
}
