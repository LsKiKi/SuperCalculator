package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;


public class business extends Fragment {
    EditText total_loan, base_rate, times;
    TextView real_rate, tips;
    Spinner back_way, year_num;
    Button btn;
    double default_rate, default_times = 1.0;
    double rate_, times_ = default_times;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.business, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        /*
         * 查找绑定资源
         */
        total_loan = view.findViewById(R.id.business_total_loan);
        base_rate = view.findViewById(R.id.business_base_rate);
        times = view.findViewById(R.id.business_rate_times);
        real_rate = view.findViewById(R.id.business_real_rate);
        tips = view.findViewById(R.id.business_tips);
        back_way = view.findViewById(R.id.business_back_way);
        year_num = view.findViewById(R.id.business_year_num);
        btn = view.findViewById(R.id.btn_start_compute_business_loan);

        /*
         * 在xml资源中加载数据
         */
        String[] back_way_mItems = getResources().getStringArray(R.array.back_ways);
        final String[] year_num_mItems = getResources().getStringArray(R.array.year);

        /*
         * 声明ArrayAdapter、填充数据、并绑定到组件中
         */
        final ArrayAdapter<String> back_way_adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner_select, back_way_mItems);
        back_way_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        back_way.setAdapter(back_way_adapter);
        final ArrayAdapter<String> year_num_adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner_select, year_num_mItems);
        year_num_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        year_num.setAdapter(year_num_adapter);

        /*
         * 年份Spinner选中事件
         */
        year_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * 根据选择的年份判断基本利率
                 */
                int pre_year = Integer.parseInt(year_num_mItems[position]);
                default_rate = 4.35;
                if (pre_year > 5) {
                    default_rate = 4.9;
                } else if (pre_year > 1) {
                    default_rate = 4.75;
                }
                rate_ = default_rate;

                /*
                 * 修改提示语句、基准利率、准确利率
                 */
                tips.setText(String.format(view.getResources().getString(R.string.business_tip), default_rate));
                base_rate.setText(String.valueOf(default_rate));
                base_rate.setHint(String.valueOf(default_rate));
                real_rate.setText(new DecimalFormat("#.####%").format(default_rate * times_ / 100));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
         * 利率 EditText 修改事件
         */
        base_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = base_rate.getText().toString();
                if (str.isEmpty()) {
                    real_rate.setText(new DecimalFormat("#.####%").format(default_rate * times_ / 100));
                    rate_ = default_rate;
                } else {
                    /*
                     * 最多两位小数，若多于两位小数则无视本次输入
                     */
                    if (str.indexOf('.') != -1 && str.indexOf('.') < str.length() - 3) {
                        int index = base_rate.getSelectionStart();
                        base_rate.getText().delete(index - 1, index);
                        str = base_rate.getText().toString();
                    }
                    rate_ = Double.parseDouble(str);
                    real_rate.setText(new DecimalFormat("#.####%").format(rate_ * times_ / 100));
                }
            }
        });


        /*
         * 倍数 EditText 修改事件
         */
        times.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = times.getText().toString();
                if (str.isEmpty()) {
                    real_rate.setText(new DecimalFormat("#.####%").format(rate_ * default_times / 100));
                    times_ = default_times;
                } else {
                    /*
                     * 最多两位小数，若多于两位小数则无视本次输入
                     */
                    if (str.indexOf('.') != -1 && str.indexOf('.') < str.length() - 3) {
                        int index = times.getSelectionStart();
                        times.getText().delete(index - 1, index);
                        str = times.getText().toString();
                    }
                    times_ = Double.parseDouble(str);
                    real_rate.setText(new DecimalFormat("#.####%").format(rate_ * times_ / 100));
                }
            }
        });

    }

}
