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
import android.widget.TextView;

import com.example.marius.miniaplicatie.classes.Person;
import com.example.marius.miniaplicatie.database.DatabaseHelper;


public class ProfileFragment extends Fragment {

    private TextView numeView;
    private TextView adresaView;
    private TextView username;
    private Button button;
    private DatabaseHelper db;
    private Person p;
    private ListenerLogout listenerLogout;

    private SharedPreferences preferences;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        preferences = getActivity().getSharedPreferences("Log",Context.MODE_PRIVATE);
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        numeView = view.findViewById(R.id.id_nume);
        adresaView = view.findViewById(R.id.id_adresa);
        username = view.findViewById(R.id.id_username);
        button = view.findViewById(R.id.id_logout);
        p=new Person();
        db = new DatabaseHelper(getContext(),null,null,1);


        int id_user = preferences.getInt("id_user",0);

        if (id_user!=0)
        {
            //p = db.get_Person(id_user);
            Activity activity = (Activity) getActivity();
            p = activity.getP();
            numeView.setText(p.getName());
            adresaView.setText(p.getAdresa());
            username.setText(p.getUsername());
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("id_user",0);
                editor.commit();
                listenerLogout.buttonPressLogout();
            }
        });
    }
    public interface ListenerLogout
    {
        public void buttonPressLogout();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerLogout = (ListenerLogout) context;
    }
    public static ProfileFragment newInstance()
    {
        Bundle args = new Bundle();
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(args);
        return profileFragment;

    }
}
