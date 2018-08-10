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

import com.example.marius.miniaplicatie.classes.Car;
import com.example.marius.miniaplicatie.database.DatabaseHelper;

import org.w3c.dom.Text;


public class SelectElement extends Fragment {

    private TextInputLayout brandInput;
    private TextInputLayout modelInput;
    private TextInputLayout combustibil_typeInput;
    private Button updateButton;
    private ProgressBar progressBar;
    private DatabaseHelper dbHelper;
    private Car car;
    private int id_car;
    private UpdateCarListener listener;
    private SharedPreferences preferences;

    public SelectElement() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_element, container, false);
        brandInput = v.findViewById(R.id.brandId);
        modelInput = v.findViewById(R.id.modelId);
        combustibil_typeInput = v.findViewById(R.id.combustibil_typeId);
        updateButton = v.findViewById(R.id.updateCar);
        progressBar = v.findViewById(R.id.progressBarId);
        car = new Car();
        preferences = getActivity().getSharedPreferences("Log",Context.MODE_PRIVATE);
        int id_person=preferences.getInt("id_user",0);
        car.setId_Persoana(id_person);
        Activity activity = (Activity) getActivity();
        id_car = activity.getIdCar();
        dbHelper = new DatabaseHelper(getContext(), null, null, 1);
        //progressBar.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AsyncTaskGet asyncTaskGet = new AsyncTaskGet();
                asyncTaskGet.execute(id_car);
            }
        }, 5000);

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AsyncTaskUpdate asyncTaskUpdate = new AsyncTaskUpdate();
                asyncTaskUpdate.execute();
                listener.updateBackPage();
            }
        });


    }
    private class AsyncTaskGet extends AsyncTask<Integer,String,Car>
    {
        private Car c;
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Car doInBackground(Integer... integers) {
            c = dbHelper.get_Car(integers[0]);
            return c;
        }

        @Override
        protected void onPostExecute(Car car) {
            progressBar.setVisibility(View.INVISIBLE);
            car = c;
            brandInput.getEditText().setText(c.getMarca());
            modelInput.getEditText().setText(c.getModel());
            combustibil_typeInput.getEditText().setText(c.getTip_combustibil());

        }
    }
    private class AsyncTaskUpdate extends AsyncTask<Void,String,Car>
    {
        private Car c = new Car();

        @Override
        protected Car doInBackground(Void... voids) {
            dbHelper.updateCar(c.getId(),c.getMarca(),c.getModel(),c.getTip_combustibil(),c.getId_Persoana());
            return car;
        }

        @Override
        protected void onPreExecute() {
            String brand = brandInput.getEditText().getText().toString();
            String model = modelInput.getEditText().getText().toString();
            String combustibil = combustibil_typeInput.getEditText().getText().toString();


            c.setId(id_car);
            c.setMarca(brand);
            c.setModel(model);
            c.setTip_combustibil(combustibil);
            c.setId_Persoana(car.getId_Persoana());
        }

        @Override
        protected void onPostExecute(Car car) {
            listener.updateBackPage();
        }
    }
    public interface UpdateCarListener
    {
        public void updateBackPage();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (UpdateCarListener)context;
    }
}
