package com.whut.wlqk.superCalculator.utils.tax;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbHelper";
    private static final String DB_NAME = "city.db";
    private static final int DB_VERSION = 1;
    private static DbHelper dbHelper = null;
    private SQLiteDatabase db = null;
    private String path = null;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        CopySqliteFileFromRawToDatabases(context);
        path = context.getFilesDir().getParent() + "/databases/city.db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE main.wuxianyijin(city TEXT NOT NULL ,oldcare REAL,medicaltreatment REAL," +
//                "unemployed  REAL, injury REAL, procreation REAL, publicfunds REAL ," +
//                "PRIMARY KEY (city))";
//        Log.d(TAG, "create table");
//        db.execSQL(sql);
        System.out.println("BBBBBBBBBBB" + path);
        db.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        System.out.println("BBBBBBBBBBB");
        System.out.println("BBBBBBBBBBB" + db.toString());
    }

    private void CopySqliteFileFromRawToDatabases(Context context) {

        // 第一次运行应用程序时，加载数据库到data/data/当前包的名称/database/<db_name>
        File files = context.getFilesDir(); //目录
        File dir = new File(files.getParent(), "databases"); //新建文件夹
        Log.i(TAG, "!dir.exists()=" + !dir.exists());
        Log.i(TAG, "!dir.isDirectory()=" + !dir.isDirectory());
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, "city.db");
        System.out.println("AAAAAAAA");
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        //通过IO流的方式，将assets目录下的数据库文件，写入到SD卡中。
        if (!file.exists()) {
            try {
                file.createNewFile();
                AssetManager assets = context.getAssets();
                inputStream = assets.open("city.db");
                outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int len;

                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                    System.out.println(buffer);
                    System.out.println(len);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
            db = null;
        }
    }

    public SQLiteDatabase openRead() {
        if (db == null || !db.isOpen()) {
            db = this.getWritableDatabase();
        }
        return db;
    }

    public ArrayList<String> queryAll() {
        String sql = "select city from wuxianyijin";//"SELECT name FROM sqlite_master WHERE type='table' ORDER BY name; ";//
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = db.rawQuery(sql, null);
        Log.d(TAG, "queryAll");
        // System.out.println(cursor.getString(0));
        System.out.println("CCCCCCCCCC");
        if (cursor.moveToFirst()) {
            for (; ; cursor.moveToNext()) {

                String city = cursor.getString(0);
                list.add(city);
                if (cursor.isLast() == true) {
                    break;
                }
            }
        }
        cursor.close();
        return list;
    }


    public Wuxianyijin queryByCity(String city) {
        String sql = String.format("select * from wuxianyijin where city='%s'", city);
        Wuxianyijin wuxianyijin = new Wuxianyijin();
        Cursor cursor = db.query("wuxianyijin",null,"city=?",new String[]{city},
                null,null,null );
        Log.d(TAG, "queryByCity" + city);
        if (cursor.moveToFirst()) {
            for (; ; cursor.moveToNext()) {
                wuxianyijin.setCity(city);
                wuxianyijin.setOldCare(cursor.getDouble(1));
                wuxianyijin.setMedicalTreatment(cursor.getDouble(2));
                wuxianyijin.setUnempliyed(cursor.getDouble(3));
                wuxianyijin.setInjury(cursor.getDouble(4));
                wuxianyijin.setProcreation(cursor.getDouble(5));
                wuxianyijin.setPublicfunds(cursor.getDouble(6));
                if (cursor.isLast() == true) {
                    break;
                }
            }
        }
        cursor.close();
        return wuxianyijin;
    }
}
