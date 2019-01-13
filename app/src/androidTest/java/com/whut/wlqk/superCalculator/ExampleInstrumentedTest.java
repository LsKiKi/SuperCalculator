package com.whut.wlqk.superCalculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.whut.wlqk.superCalculator.utils.tax.DbHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.zgh.leo.calcui", appContext.getPackageName());
    }

    @Test
    public void db (){
        Context appContext = InstrumentationRegistry.getTargetContext();
        DbHelper dbHelper = new DbHelper(appContext);
        dbHelper.openRead();
        ArrayList<String> list = dbHelper.queryAll();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        dbHelper.close();
        //重复开启测试
        dbHelper.openRead();
        ArrayList<String> list2 = dbHelper.queryAll();
        for(int i=0;i<list2.size();i++){
            System.out.println(list2.get(i));
        }
        dbHelper.close();
    }
}
