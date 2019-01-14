package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whut.wlqk.superCalculator.utils.tax.DbHelper;
import com.whut.wlqk.superCalculator.utils.tax.Wuxianyijin;

import java.util.List;

public class monthlySalary extends Fragment {
    TextView insurance_housing_fund;
    LinearLayout layout;
    boolean inf_visible = false;
    DbHelper dbHelper = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.monthlysalary, container, false);
        dbHelper = new DbHelper(this.getContext());
        dbHelper.openRead();
        init(view);
        return view;
    }

    private void init(View view) {
        insurance_housing_fund = view.findViewById(R.id.textView23);
        layout = view.findViewById(R.id.ly_insurance_housing_fund);
        layout.setVisibility(View.GONE);
        insurance_housing_fund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(inf_visible ? View.GONE : View.VISIBLE);
                inf_visible = !inf_visible;
            }
        });
    }

    /**
     * 所有城市名
     *
     * @return
     */
    private List<String> cityData() {
        return dbHelper.queryAll();
    }

    /**
     * 查询相应城市五险一金数据
     *
     * @param city
     * @return
     */
    private Wuxianyijin queryByCity(String city) {
        return dbHelper.queryByCity(city);
    }


}
