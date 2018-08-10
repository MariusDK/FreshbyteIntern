package com.example.marius.miniaplicatie;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marius.miniaplicatie.classes.Person;
import com.example.marius.miniaplicatie.classes.Phone;
import com.example.marius.miniaplicatie.database.DatabaseHelper;


public class PhoneFragment extends Fragment {
    private TextInputLayout brandInput;
    private TextInputLayout modelInput;
    private TextInputLayout soInput;
    private Button addPhoneButton;
    private SharedPreferences sharedPreferences;
    private DatabaseHelper databaseHelper;
    private Person p;
    private int id_user;
    private ActualizarePhone actualizarePhone;

    public PhoneFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phone,container,false);
        brandInput = (TextInputLayout)v.findViewById(R.id.brand_id);
        modelInput = (TextInputLayout)v.findViewById(R.id.model_id);
        soInput = (TextInputLayout)v.findViewById(R.id.so_id);
        addPhoneButton = (Button)v.findViewById(R.id.add_phone_id);

        sharedPreferences = getActivity().getSharedPreferences("Log",Context.MODE_PRIVATE);
        id_user = sharedPreferences.getInt("id_user",0);
        databaseHelper = new DatabaseHelper(getContext(),null,null,1);
        if (id_user != 0)
        {
           p = databaseHelper.get_Person(id_user);
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        addPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = brandInput.getEditText().getText().toString();
                String model = modelInput.getEditText().getText().toString();
                String so = soInput.getEditText().getText().toString();
                Phone p = new Phone();
                p.setMarca(brand);
                p.setModel(model);
                p.setSo(so);
                p.setId_Person(id_user);
                databaseHelper.addPhone(p);
                actualizarePhone.actualizarePhoneList();
            }
        });
    }
    public interface ActualizarePhone
    {
        public void actualizarePhoneList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        actualizarePhone = (ActualizarePhone)context;
    }
}
