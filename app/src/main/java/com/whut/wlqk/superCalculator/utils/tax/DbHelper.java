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
import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbHelper";
    private static final String DB_NAME = "city.db"; //数据库名称
    private static final int DB_VERSION = 1; //版本号
    private SQLiteDatabase db = null;
    private String path;  //文件路径

    /**
     * 构造函数
     * db文件转移到sd卡
     *
     * @param context
     */
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        CopySqliteFileFromRawToDatabases(context);
        path = context.getFilesDir().getParent() + "/databases/city.db";
    }

    /**
     * 以只读方式打开数据库连接
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    /**
     * 以文件流的方式将db文件转入sd卡
     *
     * @param context
     */
    private void CopySqliteFileFromRawToDatabases(Context context) {

        File files = context.getFilesDir(); //目录
        File dir = new File(files.getParent(), "databases"); //新建文件夹 只能为databases？
        Log.i(TAG, "!dir.exists()=" + !dir.exists());
        Log.i(TAG, "!dir.isDirectory()=" + !dir.isDirectory());
        if (!dir.exists()) dir.mkdirs();  //如果不存在 创建目录
        File file = new File(dir, "city.db"); //在文件夹下新建city.db文件
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

    /**
     * 关闭数据库连接
     */
    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
            db = null;
        }
    }


    /**
     * 打开数据库连接
     *
     * @return
     */
    public SQLiteDatabase openRead() {
        if (db == null || !db.isOpen()) {
            db = this.getWritableDatabase();
        }
        return db;
    }

    /**
     * 查询所有城市
     *
     * @return List<Sting> citys
     */
    public ArrayList<String> queryAll() {
        String sql = "select city from wuxianyijin";
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = db.rawQuery(sql, null);
        Log.d(TAG, "queryAll");
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


    /**
     * 以城市名查询五险一金率
     *
     * @param city
     * @return Wuxianyijin
     */
    public Wuxianyijin queryByCity(String city) {
        Wuxianyijin wuxianyijin = new Wuxianyijin();
        Cursor cursor = db.query("wuxianyijin", null, "city=?", new String[]{city},
                null, null, null);
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
