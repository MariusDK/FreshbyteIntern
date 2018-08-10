package com.example.marius.proiectocupatii;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListCardView.OnHeadlineSelectedListener, Add.OnAddListener{
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private static final int NUM_PAGES = 5;
    private List<Persoana> persoanaList = new ArrayList<>();
    private int ocupatieValue = 0;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ocupatii ocupatii = new Ocupatii();

        mPager = findViewById(R.id.viewPager);
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());


        mPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mPager);
//        Add add = new Add();
        fragmentManager = getSupportFragmentManager();
//        transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.viewPager,add).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    @Override
//    public void onButtonPressed(int id_button) {
//        if (id_button != 6) {
//
//            ListCardView listCardView = new ListCardView();
//            transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.fragment_container, listCardView, null);
//            transaction.addToBackStack(null);
//            transaction.commit();
//            ocupatieValue = id_button;
//
//        }
//        else
//        {
//            Add add = new Add();
//            transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.fragment_container, add, null);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }
//    }

    @Override
    public void onButtonPressed(String nume, String prenume, String adresa, int nr) {
        Persoana p = new Persoana(nume,prenume,adresa,nr);
        System.out.println(p.toString());
        persoanaList.add(p);
    }

    public List<Persoana> getPersoanaList() {
        return persoanaList;
    }

    public int getOcupatieValue() {
        return ocupatieValue;
    }

    @Override
    public void onButtonPressed2() {

        Add add = new Add();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, add, null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private class MyPagerAdapter extends FragmentStatePagerAdapter
    {
        private String tabTitles[] = new String[] {"Manageri","Elevi","Studenti","Masteranzi","Programatori"};
        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {


            switch (position) {

                case 1:
                    return ListCardView.newInstanceElev();

                case 2:
                    return ListCardView.newInstanceStudent();
                case 3:
                    return ListCardView.newInstanceMasterand();
                case 4:
                    return ListCardView.newInstanceProgramator();
                default:
                    return ListCardView.newInstanceManager();

            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
