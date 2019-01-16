package com.whut.wlqk.superCalculator;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView item_jisuan, item_geshui, item_fangdai;
    private ViewPager vp;
    private SimpleCalculFragment oneFragment;
    private IncomeTaxFragment twoFragment;
    private MortgageFragment threeFragment;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置这个这句话所在的Activity采用R.layout下的main布局文件进行布局
        setContentView(R.layout.activity_main);
        initViews();

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(3);//ViewPager的缓存为4帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        item_jisuan.setTextColor(getResources().getColor(R.color.colorTitleSelected));


        //ViewPager的监听事件
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });
    }

    /**
     * 初始化布局View
     */
    private void initViews() {
        item_jisuan = (TextView) findViewById(R.id.item_jisuan);
        item_geshui = (TextView) findViewById(R.id.item_geshui);
        item_fangdai = (TextView) findViewById(R.id.item_fangdai);

        item_jisuan.setOnClickListener(this);
        item_geshui.setOnClickListener(this);
        item_fangdai.setOnClickListener(this);

        vp = (ViewPager) findViewById(R.id.mainViewPager);
        oneFragment = new SimpleCalculFragment();
        twoFragment = new IncomeTaxFragment();
        threeFragment = new MortgageFragment();

        //给FragmentList添加数据
        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(threeFragment);
    }


    /**
     * 点击顶部Text 动态修改ViewPager的内容
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_jisuan:
                //当滑动到计算页面的时候，加载计算页面的内容
                vp.setCurrentItem(0, true);
                break;
            case R.id.item_geshui:
                //当滑动到个税页面的时候，加载个税页面的内容
                vp.setCurrentItem(1, true);
                break;
            case R.id.item_fangdai:
                //当滑动到房贷页面的时候，加载房贷页面的内容
                vp.setCurrentItem(2, true);
                break;

        }
    }


    /*
     *由ViewPager的滑动修改顶部导航Text的颜色
     */
    private void changeTextColor(int position) {
        if (position == 0) {
            item_jisuan.setTextColor(getResources().getColor(R.color.colorTitleSelected));
            item_geshui.setTextColor(getResources().getColor(R.color.colorTitleUnSelected));
            item_fangdai.setTextColor(getResources().getColor(R.color.colorTitleUnSelected));
        } else if (position == 1) {
            item_geshui.setTextColor(getResources().getColor(R.color.colorTitleSelected));
            item_jisuan.setTextColor(getResources().getColor(R.color.colorTitleUnSelected));
            item_fangdai.setTextColor(getResources().getColor(R.color.colorTitleUnSelected));
        } else if (position == 2) {
            item_fangdai.setTextColor(getResources().getColor(R.color.colorTitleSelected));
            item_jisuan.setTextColor(getResources().getColor(R.color.colorTitleUnSelected));
            item_geshui.setTextColor(getResources().getColor(R.color.colorTitleUnSelected));
        }
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    /*
     * 点击非编辑区域收起键盘
     * 获取点击事件
     */
    @CallSuper
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isShouldHideKeyBord(view, ev)) {
                hideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /*
     * 判定当前是否需要隐藏
     */
    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
        if (v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    /*
     * 隐藏软键盘
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
