package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


public class MortgageFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    // 负责全局的FragmentManager, 建议使用support包中的FramengManager类
    private FragmentManager fm;
    private Fragment oneFragment, twoFragment, threeFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hoursecalcul, container, false);
        //init_btn(view);

        fm = getChildFragmentManager();
        RadioGroup tabRadio = view.findViewById(R.id.loan_tab_radio);
        tabRadio.setOnCheckedChangeListener(this);
        // 如果不需要恢复之前的状态，默认显示的是第一个oneFragment
        if (savedInstanceState == null){
            if (oneFragment == null){
                oneFragment = new BusinessFragment();
            }
            fm.beginTransaction().add(R.id.fragment_container, oneFragment).commit();
        }

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (checkedId){
            case R.id.business:
                // 从容器通过标签获取相同类型的Fragment
                oneFragment = fm.findFragmentByTag("OneFragment");
                // 判断是否为空
                if (oneFragment == null){
                    oneFragment = new BusinessFragment();
                }
                // 判断是否添加这个fragment对象是否已经添加到容器中
                // 如果已经添加过了，就show，如果没有添加就add
                if (oneFragment.isAdded()){
                    transaction.show(oneFragment);
                }else{
                    transaction.add(R.id.fragment_container,oneFragment, "OneFragment");
                }
                // 隐藏掉其他的两个fragment
                if (twoFragment != null && twoFragment.isAdded()){
                    transaction.hide(twoFragment);
                }
                if (threeFragment != null && threeFragment.isAdded()){
                    transaction.hide(threeFragment);
                }
                break;

            case R.id.fund:
                // 从容器通过标签获取相同类型的Fragment
                twoFragment = fm.findFragmentByTag("TwoFragment");
                // 判断是否为空
                if (twoFragment == null){
                    twoFragment = new FundFragment();
                }
                // 判断是否添加这个fragment对象是否已经添加到容器中
                // 如果已经添加过了，就show，如果没有添加就add
                if (twoFragment.isAdded()){
                    transaction.show(twoFragment);
                }else{
                    transaction.add(R.id.fragment_container,twoFragment, "TwoFragment");
                }
                // 隐藏掉其他的两个fragment
                if (oneFragment != null && oneFragment.isAdded()){
                    transaction.hide(oneFragment);
                }
                if (threeFragment != null && threeFragment.isAdded()){
                    transaction.hide(threeFragment);
                }
                break;
            case R.id.combination:
                // 从容器通过标签获取相同类型的Fragment
                threeFragment = fm.findFragmentByTag("ThreeFragment");
                // 判断是否为空
                if (threeFragment == null){
                    threeFragment = new CombinationFragment();
                }
                // 判断是否添加这个fragment对象是否已经添加到容器中
                // 如果已经添加过了，就show，如果没有添加就add
                if (threeFragment.isAdded()){
                    transaction.show(threeFragment);
                }else{
                    transaction.add(R.id.fragment_container,threeFragment);
                }
                // 隐藏掉其他的两个fragment
                if (twoFragment != null && twoFragment.isAdded()){
                    transaction.hide(twoFragment);
                }
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
