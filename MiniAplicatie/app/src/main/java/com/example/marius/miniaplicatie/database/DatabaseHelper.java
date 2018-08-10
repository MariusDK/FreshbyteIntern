package com.example.marius.miniaplicatie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.example.marius.miniaplicatie.classes.Car;
import com.example.marius.miniaplicatie.classes.Person;
import com.example.marius.miniaplicatie.classes.Phone;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personDB.db";
    public static final String TABLE_NAME1 = "Person";
    public static final String TABLE_NAME2 = "Car";
    public static final String TABLE_NAME3 = "Phone";
    public static final String COLUMN_Person_ID = "ID";
    public static final String COLUMN_Person_NAME = "PersonName";
    public static final String COLUMN_Person_ADDRESS = "PersonAddress";
    public static final String COLUMN_Person_USERNAME = "PersonUsername";
    public static final String COLUMN_Person_PASSWORD = "PersonPassword";

    public static final String COLUMN_CAR_ID = "ID";
    public static final String COLUMN_CAR_BRANDS = "Brand";
    public static final String COLUMN_CAR_MODEL = "Model";
    public static final String COLUMN_CAR_TIP = "Tip_Combustibil";
    public static final String COLUMN_CAR_ID_PERSON = "PersonID";

    public static final String COLUMN_Phone_ID = "ID";
    public static final String COLUMN_Phone_BRANDS = "Brand";
    public static final String COLUMN_Phone_MODEL = "Version";
    public static final String COLUMN_Phone_SO = "SO";
    public static final String COLUMN_Phone_ID_PERSON = "PersonID";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_PERSON = "CREATE TABLE " + TABLE_NAME1 + " ( " + COLUMN_Person_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_Person_NAME + " TEXT, " + COLUMN_Person_ADDRESS + " TEXT, " + COLUMN_Person_USERNAME + " TEXT, " + COLUMN_Person_PASSWORD + " TEXT)";
        String CREATE_TABLE_CAR = "CREATE TABLE " + TABLE_NAME2 + " ( " + COLUMN_CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_CAR_BRANDS + " TEXT, " + COLUMN_CAR_MODEL + " TEXT, " + COLUMN_CAR_TIP + " TEXT, "+COLUMN_CAR_ID_PERSON+" INTEGER, " + "FOREIGN KEY(" + COLUMN_CAR_ID_PERSON + ") REFERENCES " + TABLE_NAME1 + "(" + COLUMN_Person_ID + "))";
        String CREATE_TABLE_PHONE = "CREATE TABLE " + TABLE_NAME3 + " ( " + COLUMN_Phone_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_Phone_BRANDS + " TEXT, " + COLUMN_Phone_MODEL + " TEXT, " + COLUMN_Phone_SO + " TEXT, "+COLUMN_Phone_ID_PERSON+" INTEGER, "+ "FOREIGN KEY(" + COLUMN_Phone_ID_PERSON + ") REFERENCES " + TABLE_NAME1 + "(" + COLUMN_Person_ID + "))";

        sqLiteDatabase.execSQL(CREATE_TABLE_PERSON);
        sqLiteDatabase.execSQL(CREATE_TABLE_CAR);
        sqLiteDatabase.execSQL(CREATE_TABLE_PHONE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addPerson(Person person) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_Person_NAME, person.getName());
        values.put(COLUMN_Person_ADDRESS, person.getAdresa());
        values.put(COLUMN_Person_USERNAME, person.getUsername());
        values.put(COLUMN_Person_PASSWORD, person.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME1, null, values);
        db.close();
    }

    public void addPhone(Phone phone) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_Phone_BRANDS, phone.getMarca());
        values.put(COLUMN_Phone_MODEL, phone.getModel());
        values.put(COLUMN_Phone_SO, phone.getSo());
        values.put(COLUMN_Phone_ID_PERSON, phone.getId_Person());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME3, null, values);
        db.close();
        System.out.println("Merge");
    }

    public void addCar(Car car) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAR_BRANDS, car.getMarca());
        values.put(COLUMN_CAR_MODEL, car.getModel());
        values.put(COLUMN_CAR_TIP, car.getTip_combustibil());
        values.put(COLUMN_CAR_ID_PERSON, car.getId_Persoana());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME2, null, values);
        db.close();
    }

    public List<Car> getAllCarsFromUser(int id) {
        System.out.println(id);
        String query = "SELECT * FROM " + TABLE_NAME2 + " c INNER JOIN " + TABLE_NAME1 + " p ON c.PersonID=p.ID WHERE p.ID='" + id+"';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        List<Car> list = new ArrayList<>();
        while (c.moveToNext()) {
            int result_0 = c.getInt(0);
            String result_1 = c.getString(1);
            String result_2 = c.getString(2);
            String result_3 = c.getString(3);
            int result_4 = c.getInt(4);
            Car car = new Car(result_0, result_1, result_2, result_3, result_4);
            System.out.println(car.toString());
            list.add(car);
        }
        c.close();
        db.close();
        return list;
    }

    public List<Phone> getAllPhones()
    {
        String query = "SELECT * FROM "+TABLE_NAME3;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        List<Phone> list = new ArrayList<>();
        while (c.moveToNext()) {
            int result_0 = c.getInt(0);
            String result_1 = c.getString(1);
            String result_2 = c.getString(2);
            String result_3 = c.getString(3);
            int result_4 = c.getInt(4);
            Phone phone = new Phone(result_0, result_1, result_2, result_3, result_4);
            System.out.println(phone.toString());
            list.add(phone);
        }
        c.close();
        db.close();
        return list;
    }

    public List<Phone> getAllPhoneFromUser(int id) {
        String query = "SELECT * FROM " + TABLE_NAME3 + " ph INNER JOIN " + TABLE_NAME1 + " p ON ph.PersonID=p.ID WHERE ph.PersonID='" + id+"';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        List<Phone> list = new ArrayList<>();
        System.out.println("Am ajuns aici");
        while (c.moveToNext()) {
            int result_0 = c.getInt(0);
            String result_1 = c.getString(1);
            String result_2 = c.getString(2);
            String result_3 = c.getString(3);
            int result_4 = c.getInt(4);
            Phone phone = new Phone(result_0, result_1, result_2, result_3, result_4);
            System.out.println(phone.toString());
            list.add(phone);
        }
        c.close();
        db.close();
        return list;
    }

    public Person checkLogin(String username, String password) {
        Person p = new Person();
        String query = "SELECT * FROM " + TABLE_NAME1 + " p WHERE p.PersonUsername = '"+username +"'  AND p.PersonPassword = '"+password+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int result_0 = c.getInt(0);
        String result_1 = c.getString(1);
        String result_2 = c.getString(2);
        String result_3 = c.getString(3);
        String result_4 = c.getString(4);

        p = new Person(result_0, result_1, result_2, result_3, result_4);
        return p;
    }

    public Person get_Person(int id_Person)
    {
        Person p = new Person();
        String query = "SELECT * FROM "+TABLE_NAME1+" p WHERE p.ID='"+id_Person+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int result_0 = c.getInt(0);
        String result_1 = c.getString(1);
        String result_2 = c.getString(2);
        String result_3 = c.getString(3);
        String result_4 = c.getString(4);

        p = new Person(result_0, result_1, result_2, result_3, result_4);
        return p;
    }
    public Car get_Car(int id_car)
    {
        String query = "SELECT * FROM "+TABLE_NAME2+" c WHERE c.ID='"+id_car+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int result_0 = cursor.getInt(0);
        String result_1 = cursor.getString(1);
        String result_2 = cursor.getString(2);
        String result_3 = cursor.getString(3);
        int result_4 = cursor.getInt(4);

        Car car = new Car(result_0,result_1,result_2,result_3,result_4);
        return car;
    }
    public boolean updateCar(int id,String brand,String model,String Combustibil,int id_person)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_CAR_ID,id);
        args.put(COLUMN_CAR_BRANDS,brand);
        args.put(COLUMN_CAR_MODEL,model);
        args.put(COLUMN_CAR_TIP,Combustibil);
        args.put(COLUMN_CAR_ID_PERSON,id_person);
        return db.update(TABLE_NAME2, args,COLUMN_CAR_ID+"="+id,null)>0;
    }

}
