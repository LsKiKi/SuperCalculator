package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener {

    //三个fragment
    private business f1;
    private fund f2;
    private combination f3;

    //底部三个按钮
    private Button foot1;
    private Button foot2;
    private Button foot3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foot1 = (Button) findViewById(R.id.business);
        foot2 = (Button) findViewById(R.id.fund);
        foot3 = (Button) findViewById(R.id.combination);
        foot1.setOnClickListener(this);
        foot2.setOnClickListener(this);
        foot3.setOnClickListener(this);

        //第一次初始化首页默认显示第一个fragment
       // initFragment1();
    }

    @Override
    public void onClick(View view) {
        //
    }

//    //显示第一个fragment
//    private void initFragment1(){
//        //开启事务，fragment的控制是由事务来实现的
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
//        if(f1 == null){
//            f1 = new MyFragment("消息");
//            transaction.add(R.id.main_frame_layout, f1);
//        }
//        //隐藏所有fragment
//        hideFragment(transaction);
//        //显示需要显示的fragment
//        transaction.show(f1);
//
//        //第二种方式(replace)，初始化fragment
////        if(f1 == null){
////            f1 = new MyFragment("消息");
////        }
////        transaction.replace(R.id.main_frame_layout, f1);
//
//        //提交事务
//        transaction.commit();
//    }
//

//    @Override
//    public void onClick(View view) {
//
//    }
//
//    public void setChantEssayFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.layFrame, ChantEssayFragment.newInstance());
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }



}
