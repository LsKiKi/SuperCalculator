package com.whut.wlqk.superCalculator.utils.tax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE NewTable(city varchar(30) NOT NULL ,oldcare double,medicaltreatment double,"+
                "unemployed  double, injury double, procreation double, publicfunds double ," +
                "PRIMARY KEY (city))";
        Log.i(TAG,"create db");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
