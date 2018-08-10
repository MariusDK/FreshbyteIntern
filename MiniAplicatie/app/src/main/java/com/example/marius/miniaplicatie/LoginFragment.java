package com.example.marius.miniaplicatie;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.example.marius.miniaplicatie.classes.Person;
import com.example.marius.miniaplicatie.database.DatabaseHelper;

public class LoginFragment extends Fragment {

    private TextInputLayout usernameInput;
    private TextInputLayout passwordInput;
    private Button loginButton;
    private Button registerButton;
    private DatabaseHelper dbHelp;
    private Person p;
    private LoginOnClickListener listener;
    private SharedPreferences preferences;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    public LoginFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);

        usernameInput = view.findViewById(R.id.username_id);
        passwordInput = view.findViewById(R.id.password_id);
        loginButton = view.findViewById(R.id.login_id);
        registerButton = view.findViewById(R.id.register_id);
        dbHelp = new DatabaseHelper(getContext(),null,null,1);
        preferences = getActivity().getSharedPreferences("Log",Context.MODE_PRIVATE);
        progressBar = view.findViewById(R.id.ProgressBarId);
        progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getEditText().getText().toString();
                String password = passwordInput.getEditText().getText().toString();
                p = dbHelp.checkLogin(username,password);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("id_user",p.getId());
                editor.commit();
                Activity activity = (Activity) getActivity();
                progressBar.setVisibility(View.VISIBLE);

//                try {
//                    Thread.sleep(100000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace(); }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listener.pressButtonLogin(p);
                    }
                }, 5000);


            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.pressButtonRegister();
            }
        });
    }

    public interface LoginOnClickListener
    {
        public void pressButtonLogin(Person p);
        public void pressButtonRegister();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (LoginOnClickListener)context;
    }
}
