package com.example.marius.miniaplicatie;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marius.miniaplicatie.classes.Car;
import com.example.marius.miniaplicatie.database.DatabaseHelper;


public class CarFragment extends Fragment {

    private TextInputLayout brandInput;
    private TextInputLayout modelInput;
    private TextInputLayout combustibilTypeInput;
    private Button addCarButton;
    private DatabaseHelper db;
    private SharedPreferences preferences;
    private CarInterfacePanel carInterfacePanel;


    public CarFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car,container,false);
        brandInput = v.findViewById(R.id.brand_id);
        modelInput = v.findViewById(R.id.model_id);
        combustibilTypeInput = v.findViewById(R.id.combustibil_type_id);
        addCarButton = v.findViewById(R.id.addCar);
        db = new DatabaseHelper(getContext(),null,null,1);
        preferences = getActivity().getSharedPreferences("Log",Context.MODE_PRIVATE);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        addCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Car car = new Car();
                int id = preferences.getInt("id_user",0);
                car.setMarca(brandInput.getEditText().getText().toString());
                car.setModel(modelInput.getEditText().getText().toString());
                car.setTip_combustibil(combustibilTypeInput.getEditText().getText().toString());
                car.setId_Persoana(id);
                db.addCar(car);
                carInterfacePanel.carAddPanel();
            }
        });
    }
    public interface CarInterfacePanel
    {
        public void carAddPanel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        carInterfacePanel = (CarInterfacePanel)context;
    }
}
