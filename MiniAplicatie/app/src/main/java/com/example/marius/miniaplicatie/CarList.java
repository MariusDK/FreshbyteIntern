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
import com.example.marius.miniaplicatie.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CarList extends Fragment {
    private ListView list;
    private TextView carView;
    private FloatingActionButton add_car;
    private List<Car> carList;
    private DatabaseHelper db;
    SharedPreferences sharedPreferences;
    private ArrayAdapter<Car> adapter;
    private ChangeSavePanelListener listener;

    public CarList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_car_list, container, false);

        list = (ListView) v.findViewById(R.id.id_list_cars);
        add_car = v.findViewById(R.id.add_car);
        db = new DatabaseHelper(getContext(), null, null, 1);
        sharedPreferences = getActivity().getSharedPreferences("Log", Context.MODE_PRIVATE);
        int id_user = sharedPreferences.getInt("id_user", 0);
        //carList = db.getAllCarsFromUser(id_user);
        Activity activity = (Activity) getActivity();
        carList = activity.getCarList();

        adapter = new ArrayAdapter<Car>(getContext(), R.layout.activity_listview, carList);
        list.setAdapter(adapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAddCarPanel();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView lv = (ListView) adapterView;
                TextView tv = (TextView) lv.getChildAt(i);
                String s = tv.getText().toString();
                String[] listS = s.split(";");
                int id = Integer.parseInt(listS[0]);
                listener.onUpdateCarPanel(id);
            }
        });
        updateList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ChangeSavePanelListener) context;
    }

    public interface ChangeSavePanelListener {
        public void onAddCarPanel();
        public void onUpdateCarPanel(int id);
    }

    public static CarList newInstance()
    {
        Bundle args = new Bundle();
        CarList fragment = new CarList();
        fragment.setArguments(args);
        return fragment;
    }

    public void updateList()
    {
//        carList = new ArrayList<>();
//        int id_user = sharedPreferences.getInt("id_user", 0);
//        carList = db.getAllCarsFromUser(id_user);
        Activity activity = (Activity) getActivity();
        carList = activity.getCarList();
        adapter = new ArrayAdapter<Car>(getContext(), R.layout.activity_listview, carList);
        list.setAdapter(adapter);
    }

}
