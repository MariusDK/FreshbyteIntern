package com.example.marius.miniaplicatie;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marius.miniaplicatie.classes.Car;
import com.example.marius.miniaplicatie.classes.Person;
import com.example.marius.miniaplicatie.classes.Phone;
import com.example.marius.miniaplicatie.database.DatabaseHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Activity extends AppCompatActivity implements LoginFragment.LoginOnClickListener, ProfileFragment.ListenerLogout ,CarList.ChangeSavePanelListener, PhoneList.OnPhoneAddListener, PhoneFragment.ActualizarePhone, CarFragment.CarInterfacePanel, SelectElement.UpdateCarListener{

    private FragmentManager fragmentManager;
    private SharedPreferences preferences;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TabLayout tabLayout;
    private boolean AlistPhone = false;
    private LoginFragment login;
    public int i=0;
    private Person p;
    private List<Car> carList;
    private List<Phone> phoneList;
    private int id;
    private DatabaseHelper databaseHelper;
    private boolean status;
    private int SelectedCarId;
    private SelectElement selectElement;
    private int id_user;
    private Selected_Element_Phone selectedElementPhone;
    private int id_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPager = findViewById(R.id.viewPager);
        tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        preferences = getSharedPreferences("Log",MODE_PRIVATE);
        id_user = preferences.getInt("id_user",0);
        login = new LoginFragment();
        databaseHelper = new DatabaseHelper(this,null,null,1);
        selectElement = new SelectElement();
        selectedElementPhone = new Selected_Element_Phone();
        if (id_user!=0)
        {
//            ProfileFragment profile = new ProfileFragment();
//            fragmentTransaction.replace(R.id.container,profile);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();

            AsyncTaskHelper helper = new AsyncTaskHelper();
            helper.execute(id_user);
            mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(mPagerAdapter);
            //mPagerAdapter.notifyDataSetChanged();
            tabLayout.setupWithViewPager(mPager);
        }
        else {
            fragmentTransaction.replace(R.id.container, login);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mPager);
        //mPagerAdapter.notifyDataSetChanged();

    }

    @Override
    public void pressButtonLogin(Person p) {
        id = p.getId();
        AsyncTaskHelper helper = new AsyncTaskHelper();
        helper.execute(id);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //ProfileFragment profile = new ProfileFragment();
//
//        fragmentTransaction.replace(R.id.container,profile);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        fragmentTransaction.remove(login).commit();
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mPager);


    }

    @Override
    public void pressButtonRegister() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegisterFragment register = new RegisterFragment();

        fragmentTransaction.replace(R.id.container,register);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void buttonPressLogout() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //LoginFragment loginFragment = new LoginFragment();

        fragmentTransaction.replace(R.id.container,login);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onAddCarPanel() {
        AsyncTaskHelper helper = new AsyncTaskHelper();
        helper.execute(id_user);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CarFragment carFragment = new CarFragment();

        fragmentTransaction.replace(R.id.container,carFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onUpdateCarPanel(int id) {
        AsyncTaskHelper helper = new AsyncTaskHelper();
        helper.execute(id_user);
        SelectedCarId = id;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,selectElement);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public void onPhoneAddButtonPressed() {
        AsyncTaskHelper helper = new AsyncTaskHelper();
        helper.execute(id_user);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PhoneFragment phoneFragment = new PhoneFragment();

        fragmentTransaction.replace(R.id.container,phoneFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void updatePagePhone(int id_phone) {
        this.id_phone = id_phone;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,selectedElementPhone);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void actualizarePhoneList() {
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        //mPagerAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(mPager);
    }

    public boolean isAlistPhone() {
        return AlistPhone;
    }

    @Override
    public void carAddPanel() {
        AsyncTaskHelper helper = new AsyncTaskHelper();
        helper.execute(id_user);
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        //mPagerAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(mPager);
    }

    @Override
    public void updateBackPage() {
        AsyncTaskHelper helper = new AsyncTaskHelper();
        helper.execute(id_user);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(selectElement).commit();
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mPager);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter
    {
        private String tabTitles[] = new String[] {"Profil","Telefoane","Masini"};
        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {


            switch (position) {

                case 1:
                    return PhoneList.newInstance();

                case 2:
                    return CarList.newInstance();
                default:
                    return ProfileFragment.newInstance();

            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    private class AsyncTaskHelper extends AsyncTask<Integer,String,Void>{

        @Override
        protected void onPreExecute() {
            status = true;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            carList = databaseHelper.getAllCarsFromUser(integers[0]);
            phoneList = databaseHelper.getAllPhoneFromUser(integers[0]);
            p = databaseHelper.get_Person(integers[0]);
            System.out.println("Am fost aici!");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            status = false;
        }
    }

    public List<Car> getCarList() {
        return carList;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public Person getP() {
        return p;
    }
    public boolean showProgressBar()
    {return status;}
    public int getIdCar()
    {
        return SelectedCarId;
    }

    public int getId_phone() {
        return id_phone;
    }
}
