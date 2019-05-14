package com.cynmjcn.p04quizcar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "cars.db";

    private static final String TABLE_CAR = "Car";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_LITRE = "litre";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CREATE TABLE
        String createTableSql = "CREATE TABLE " + TABLE_CAR + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BRAND + " TEXT, "
                + COLUMN_LITRE + " TEXT )";

        sqLiteDatabase.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        onCreate(sqLiteDatabase);

    }

    public void insertCar(String brand, String litre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COLUMN_BRAND, brand);
        value.put(COLUMN_LITRE, litre);
        db.insert(TABLE_CAR, null, value);
        db.close();
    }

    public ArrayList<String> getCarBrand(){
        ArrayList<String> cars = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_BRAND +", " + COLUMN_LITRE +" FROM " + TABLE_CAR;
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor =  db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                cars.add(cursor.getString(0));
                cars.add(cursor.getString(1));

            }while(cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return  cars;
    }

    public ArrayList<Car> getCar(){
        ArrayList<Car> cars = new ArrayList<Car>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_BRAND + ", "
                + COLUMN_LITRE + " FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String brand = cursor.getString(1);
                String litre = cursor.getString(2);
                Car car = new Car(id, brand, Double.parseDouble(litre));
                cars.add(car);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  cars;
    }
}
