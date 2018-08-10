package com.example.marius.proiectocupatii;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Ocupatii extends Fragment {

    private Button elev;
    private Button student;
    private Button masterand;
    private Button programator;
    private Button manager;
    public int elevButtonId = 1;
    public int studentButtonId = 2;
    public int masterantButtonId = 3;
    public int programatorButtonId =4;
    public int managerButtonId = 5;
    public int adaugareButton = 6;
    public FloatingActionButton adaugare;
    private OnHeadlineSelectedListener mCallBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ocupatii,container,false);

        elev =(Button) rootView.findViewById(R.id.button);
        elev.setText("Elev");

        student = (Button)rootView.findViewById(R.id.button2);
        student.setText("Student");

        masterand = (Button)rootView.findViewById(R.id.button3);
        masterand.setText("Masterand");

        programator = (Button) rootView.findViewById(R.id.button4);
        programator.setText("Programator");

        manager = (Button) rootView.findViewById(R.id.button5);
        manager.setText("Manager");

        adaugare = (FloatingActionButton)rootView.findViewById(R.id.floatingActionButton);

        return rootView;
    }
    public interface OnHeadlineSelectedListener
    {
        public void onButtonPressed(int id_button);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallBack = (OnHeadlineSelectedListener) context;
        }catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        elev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onButtonPressed(elevButtonId);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onButtonPressed(studentButtonId);
            }
        });
        masterand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onButtonPressed(masterantButtonId);
            }
        });
        programator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onButtonPressed(programatorButtonId);
            }
        });
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onButtonPressed(managerButtonId);
            }
        });
        adaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onButtonPressed(adaugareButton);
            }
        });
    }

    public static Ocupatii newInstance() {
        Bundle args = new Bundle();
        Ocupatii fragment = new Ocupatii();
        fragment.setArguments(args);
        return fragment;
    }
}
