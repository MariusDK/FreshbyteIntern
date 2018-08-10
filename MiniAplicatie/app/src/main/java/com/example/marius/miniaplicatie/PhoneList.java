package com.example.marius.miniaplicatie;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.marius.miniaplicatie.classes.Car;
import com.example.marius.miniaplicatie.classes.Phone;
import com.example.marius.miniaplicatie.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PhoneList extends Fragment {
    private ListView listView;
    private TextView textView;
    private FloatingActionButton floatingActionButton;
    private SharedPreferences preferences;
    private List<Phone> list;
    private DatabaseHelper databaseHelper;
    private OnPhoneAddListener listener;
    private ArrayAdapter<Phone> adapter;
    public PhoneList()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phone_list,container,false);
        listView = (ListView)v.findViewById(R.id.lista_telefoane);
        floatingActionButton = (FloatingActionButton)v.findViewById(R.id.floatingActionButton2);
        preferences = getActivity().getSharedPreferences("Log",Context.MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(getContext(),null,null,1);
        int id_user = preferences.getInt("id_user",0);
        System.out.println("Id_userul este"+id_user);
        list = new ArrayList<>();
        if (id_user != 0)
        {
            //list = databaseHelper.getAllPhoneFromUser(id_user);
            //list = databaseHelper.getAllPhones();
            Activity activity = (Activity) getActivity();
            list = activity.getPhoneList();
        }
        System.out.println("Lista este "+list);
        adapter = new ArrayAdapter<Phone>(getContext(), R.layout.activity_listview, list);
        listView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listView = (ListView) adapterView;
                TextView textView = (TextView) listView.getChildAt(i);
                String s = textView.getText().toString();
                String[] listS = s.split(";");
                int id_phone = Integer.parseInt(listS[0]);
                listener.updatePagePhone(id_phone);
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPhoneAddButtonPressed();
            }
        });
        update();
    }

    public void update()
    {
        int id_user = preferences.getInt("id_user",0);
        System.out.println("Id_userul este"+id_user);
        list = new ArrayList<>();
        if (id_user != 0)
        {
            //list = databaseHelper.getAllPhoneFromUser(id_user);
            //list = databaseHelper.getAllPhones();
            Activity activity = (Activity) getActivity();
            list = activity.getPhoneList();
        }
        adapter = new ArrayAdapter<Phone>(getContext(), R.layout.activity_listview, list);
        listView.setAdapter(adapter);

    }
    public interface OnPhoneAddListener
    {
        public void onPhoneAddButtonPressed();
        public void updatePagePhone(int id_phone);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnPhoneAddListener)context;
    }

    public static PhoneList newInstance()
    {

        Bundle args = new Bundle();
        PhoneList fragment = new PhoneList();
        fragment.setArguments(args);
        return fragment;

    }
}
