package com.example.marius.first_db.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.marius.first_db.classes.Persoana;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "persoaneDB.db";
    public static final String TABLE_NAME = "Persoana";
    public static final String COLUMN_ID = "PersoanaID";
    public static final String COLUMN_NAME = "PersoanaName";
    public static final String COLUMN_PRENUME = "PersoanaPrenume";
    public static final String COLUMN_ADRESA = "PersoanaAdresa";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME + "("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+COLUMN_NAME+" TEXT, "+COLUMN_PRENUME+ " TEXT, "+COLUMN_ADRESA+")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public List<Persoana> loadHandler()
    {
        String result = "";
        List<Persoana> list = new ArrayList<>();
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        while (cursor.moveToNext())
        {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            Persoana p = new Persoana(result_0,result_1,result_2,result_3);
            list.add(p);
            //result += String.valueOf(result_0)+" "+result_1+" "+result_2+" "+result_3+System.getProperty("line.separator");
        }
        cursor.close();
        database.close();
        return list;
    }
    public void addHandler(Persoana persoana){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,persoana.getName());
        values.put(COLUMN_PRENUME,persoana.getPrenume());
        values.put(COLUMN_ADRESA,persoana.getAdresa());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Persoana findHandler(Persoana nume){
        String query = "SELECT * FROM "+TABLE_NAME+"WHERE"+COLUMN_NAME+" = "+"'"+nume+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Persoana persoana = new Persoana();
        if (cursor.moveToFirst()) {
            //cursor.moveToFirst();
            persoana.setId(Integer.parseInt(cursor.getString(0)));
            persoana.setName(cursor.getString(1));
            persoana.setPrenume(cursor.getString(2));
            persoana.setAdresa(cursor.getString(3));
            cursor.close();
        }
        else {
            persoana = null;
        }
        db.close();
        return persoana;
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query ="SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" ='" + String.valueOf(ID)+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Persoana persoana = new Persoana();

        if (cursor.moveToFirst()) {
            persoana.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(persoana.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;

    }
    public boolean updateHandler(int id, String name, String prenume, String adresa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, id);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_PRENUME, prenume);
        args.put(COLUMN_ADRESA, adresa);

        return db.update(TABLE_NAME, args, COLUMN_ID + "="+id, null)>0;
    }
}
