package com.example.marius.first_db;

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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marius.first_db.classes.Persoana;
import com.example.marius.first_db.database.MyDBHandler;

import java.util.ArrayList;
import java.util.List;

public class LoadList extends Fragment implements AdapterView.OnItemClickListener {

    private MyDBHandler handler;
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private List<Persoana> persoanaList;
    private ArrayAdapter<Persoana> adapter;
    private FloatingActionButton floatingActionButtonUpdate;
    private int id;
    private onUpdateListener listener;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_load_list,container,false);
        persoanaList = new ArrayList<>();
        handler = new MyDBHandler(getContext(),null,null,1);
        persoanaList = handler.loadHandler();

        for (Persoana p:persoanaList)
        {
            System.out.println(p.toString());
        }

        adapter = new ArrayAdapter<Persoana>(getContext(),R.layout.activity_listview,persoanaList);
        listView = (ListView)v.findViewById(R.id.list_id);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        floatingActionButton = v.findViewById(R.id.deleteActionButton);
        floatingActionButtonUpdate = v.findViewById(R.id.updateActionButton);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.deleteHandler(id);
                persoanaList = handler.loadHandler();

                adapter = new ArrayAdapter<Persoana>(getContext(), R.layout.activity_listview, persoanaList);
                listView.setAdapter(adapter);
            }
        });
        floatingActionButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.OnActionPressed2();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView text = (TextView) view;
        String Value = listView.getItemAtPosition(i).toString();
        String[] list = Value.split(" ");
        id = Integer.parseInt(list[0]);
        MainActivity mainActivity = ((MainActivity) getActivity());
        mainActivity.setPersoana(id,list[1],list[2],list[3]);
    }

    public interface onUpdateListener
    {
        public void OnActionPressed2();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (onUpdateListener)context;
    }
}
