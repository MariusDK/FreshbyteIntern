package com.example.marius.proiectocupatii;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ListCardView extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Persoana> list = new ArrayList<>();
    private static int ocupatiiId;
    private FloatingActionButton button;
    private OnHeadlineSelectedListener mCallBack;

    public ListCardView() {
    }


    public ListCardView(int ocupatiiId) {
        System.out.println("Constructor");
        this.ocupatiiId = ocupatiiId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_card_view,container,false);
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
        MainActivity m = ((MainActivity) getActivity());
        list = m.getPersoanaList();
        System.out.println(list.toString());
        //ocupatiiId = m.getOcupatieValue();
        System.out.println("id-ul este "+ocupatiiId);
        button = view.findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onButtonPressed2();
            }
        });

        return view;
    }
    public void updateList()
    {

        List<Persoana> persoanas = new ArrayList<>();
        for (int i = 0;i<list.size();i++)
        {
            System.out.println("Ocupatia data "+ocupatiiId+" ocupatia persoanei "+ list.get(i).getOcupatie());
            if (list.get(i).getOcupatie() == ocupatiiId)
            {
                persoanas.add(list.get(i));
                System.out.println(list.get(i).getOcupatie());
            }
        }

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        System.out.println(persoanas);
        mAdapter = new RecyclerAdapter(persoanas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity m = ((MainActivity) getActivity());
        list = m.getPersoanaList();
        //ocupatiiId = m.getOcupatieValue();

        updateList();
    }

    public static ListCardView newInstanceElev() {
        
        Bundle args = new Bundle();
        int id=1;
        ListCardView fragment = new ListCardView(id);
        fragment.setArguments(args);
        return fragment;
    }
    public static ListCardView newInstanceStudent() {

        Bundle args = new Bundle();
        int id=2;
        ListCardView fragment = new ListCardView(id);
        fragment.setArguments(args);
        return fragment;
    }
    public static ListCardView newInstanceMasterand() {

        Bundle args = new Bundle();
        int id=3;
        ListCardView fragment = new ListCardView(3);
        fragment.setArguments(args);
        return fragment;
    }
    public static ListCardView newInstanceProgramator() {

        Bundle args = new Bundle();
        int id=4;
        ListCardView fragment = new ListCardView(id);
        fragment.setArguments(args);
        return fragment;
    }
    public static ListCardView newInstanceManager() {

        Bundle args = new Bundle();
        int id=5;
        ListCardView fragment = new ListCardView(id);
        fragment.setArguments(args);
        return fragment;
    }
    public interface OnHeadlineSelectedListener
    {
        public void onButtonPressed2();
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
}
