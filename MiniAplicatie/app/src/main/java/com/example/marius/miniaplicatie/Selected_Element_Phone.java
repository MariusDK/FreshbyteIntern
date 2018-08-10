package com.example.marius.miniaplicatie;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.marius.miniaplicatie.classes.Phone;
import com.example.marius.miniaplicatie.database.DatabaseHelper;


public class Selected_Element_Phone extends Fragment {

    private TextInputLayout brandInput;
    private TextInputLayout modelInput;
    private TextInputLayout soInput;
    private Button updateButton;
    private ProgressBar progressBar;
    private SharedPreferences preferences;
    private int id_phone;
    private DatabaseHelper dbHelper;
    public Selected_Element_Phone()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_element,container,false);
        brandInput = v.findViewById(R.id.brandId);
        modelInput = v.findViewById(R.id.modelId);
        soInput = v.findViewById(R.id.soId);
        updateButton = v.findViewById(R.id.update_phoneId);
        progressBar = v.findViewById(R.id.progressBar);
        preferences = getActivity().getSharedPreferences("Log",Context.MODE_PRIVATE);
        int id_person=preferences.getInt("id_user",0);
        Activity activity = (Activity)getActivity();
        id_phone = activity.getId_phone();
        dbHelper = new DatabaseHelper(getContext(),null,null,1);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AsyncTaskGet asyncTaskGet = new AsyncTaskGet();
                asyncTaskGet.execute(id_phone);
            }
        }, 5000);


        return v;
    }
    private class AsyncTaskGet extends AsyncTask<Integer,String,Phone>
    {
        private Phone p;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Phone doInBackground(Integer... integers) {
            return null;
        }

        @Override
        protected void onPostExecute(Phone phone) {

        }
    }
}
