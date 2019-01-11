package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


public class personCalcul extends Fragment implements RadioGroup.OnCheckedChangeListener {
    // 负责全局的FragmentManager, 建议使用support包中的FramengManager类
    private FragmentManager fm;
    private Fragment oneFragment, twoFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.personcalcul, container, false);

        fm = getChildFragmentManager();
        RadioGroup tabRadio = (RadioGroup) view.findViewById(R.id.tax_tab_radio);
        tabRadio.setOnCheckedChangeListener(this);
        // 如果不需要恢复之前的状态，默认显示的是第一个oneFragment
        if (savedInstanceState == null){
            if (oneFragment == null){
                oneFragment = new monthlySalary();
            }
            fm.beginTransaction().add(R.id.fragment_container, oneFragment).commit();
        }

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (checkedId){
            case R.id.monthlysalary:
                // 从容器通过标签获取相同类型的Fragment
                oneFragment = fm.findFragmentByTag("OneFragment");
                // 判断是否为空
                if (oneFragment == null){
                    oneFragment = new monthlySalary();
                }
                // 判断是否添加这个fragment对象是否已经添加到容器中
                // 如果已经添加过了，就show，如果没有添加就add
                if (oneFragment.isAdded()){
                    transaction.show(oneFragment);
                }else{
                    transaction.add(R.id.fragment_container,oneFragment, "OneFragment");
                }
                // 隐藏掉其他的fragment
                if (twoFragment != null && twoFragment.isAdded()){
                    transaction.hide(twoFragment);
                }
                break;

            case R.id.annualbonus:
                // 从容器通过标签获取相同类型的Fragment
                twoFragment = fm.findFragmentByTag("TwoFragment");
                // 判断是否为空
                if (twoFragment == null){
                    twoFragment = new annualBonus();
                }
                // 判断是否添加这个fragment对象是否已经添加到容器中
                // 如果已经添加过了，就show，如果没有添加就add
                if (twoFragment.isAdded()){
                    transaction.show(twoFragment);
                }else{
                    transaction.add(R.id.fragment_container,twoFragment, "TwoFragment");
                }
                // 隐藏掉其他的fragment
                if (oneFragment != null && oneFragment.isAdded()){
                    transaction.hide(oneFragment);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }


}
