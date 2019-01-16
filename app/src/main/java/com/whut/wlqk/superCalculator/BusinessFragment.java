package com.whut.wlqk.superCalculator;

import android.content.Intent;
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
import android.widget.Toast;

import java.text.DecimalFormat;


public class BusinessFragment extends Fragment {
    EditText total_loan, base_rate, times;
    TextView real_rate, tips;
    Spinner back_way, year_num;
    Button btn;
    double default_rate, default_times = 1.0;
    double rate_rt, times_rt = default_times;

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
        bind_view(view);

        /*
         * 从xml中填充数据
         */
        padding_data(view);

        /*
         * 添加监听
         */
        add_listener(view);
    }

    /**
     * bind view with layout
     *
     * @param view parent
     */
    private void bind_view(View view) {
        total_loan = view.findViewById(R.id.business_total_loan);
        base_rate = view.findViewById(R.id.business_base_rate);
        times = view.findViewById(R.id.business_rate_times);
        real_rate = view.findViewById(R.id.business_real_rate);
        tips = view.findViewById(R.id.business_tips);
        back_way = view.findViewById(R.id.business_back_way);
        year_num = view.findViewById(R.id.spinner_city);
        btn = view.findViewById(R.id.btn_start_compute_business_loan);
    }

    /**
     * padding data from xml resource
     *
     * @param view parent
     */
    private void padding_data(View view) {
        /*
         * 在xml资源中加载数据
         */
        final String[] back_way_mItems = getResources().getStringArray(R.array.back_ways);
        final String[] year_num_mItems = getResources().getStringArray(R.array.year);

        /*
         * 声明ArrayAdapter、填充数据、并绑定到组件中
         * 使用自定义 Spinner 样式
         */
        final ArrayAdapter<String> back_way_adapter = new ArrayAdapter<>(view.getContext(), R.layout.item_spinner_select, back_way_mItems);
        back_way_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        back_way.setAdapter(back_way_adapter);
        final ArrayAdapter<String> year_num_adapter = new ArrayAdapter<>(view.getContext(), R.layout.item_spinner_select, year_num_mItems);
        year_num_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        year_num.setAdapter(year_num_adapter);
    }

    /**
     * edit text event
     *
     * @param s        editable
     * @param df_value default value
     * @param pointer  mouse pointer
     * @return real time value
     */
    private double et_change(Editable s, double df_value, int pointer, int precision) {
        double rt_value;
        String str = s.toString();
        /*
         * 若为空，使用默认值
         */
        if (str.isEmpty()) {
            rt_value = df_value;
        }
        /*
         * 若存在小数点
         */
        else {
            if (str.indexOf('.') != -1) {
                /*
                 * 最多两位小数，若多于两位小数则无视本次输入
                 */
                if (str.indexOf('.') < str.length() - precision - 1) {
                    s.delete(pointer - 1, pointer);
                } else
                    /*
                     * 若第一个字符为小数点
                     */
                    if (str.equals(".")) {
                        s.insert(0, "0");
                    }
            }
            /*
             * 0开始的整数
             */
            if (str.startsWith("0") && !str.startsWith("0.") && !str.equals("0")) {
                s.delete(0, 1);
            }
            str = s.toString();
            rt_value = Double.parseDouble(str);
        }
        return rt_value;
    }

    /**
     * update real time rate
     */
    private void update_real_rate() {
        real_rate.setText(new DecimalFormat("#.####%").format(rate_rt * times_rt / 100));
    }

    /**
     * add listen to view
     */
    private void add_listener(final View view) {
        /*
         * 贷款数额 EditText 修改事件
         */
        total_loan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                /*
                 * 根据是否已输入，修改字号
                 */
                if (!str.isEmpty()) {
                    total_loan.setTextSize(30);
                    total_loan.getPaint().setFakeBoldText(true);
                } else {
                    total_loan.setTextSize(24);
                    total_loan.getPaint().setFakeBoldText(false);
                }

                et_change(s, 0, total_loan.getSelectionStart(), 2);

            }
        });

        /*
         * 年份 Spinner 选中事件
         */
        year_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * 根据选择的年份判断基本利率
                 */
                int pre_year = Integer.parseInt(String.valueOf(parent.getItemAtPosition(position)));
                default_rate = 4.35;
                if (pre_year > 5) {
                    default_rate = 4.9;
                } else if (pre_year > 1) {
                    default_rate = 4.75;
                }
                rate_rt = default_rate;

                /*
                 * 修改提示语句、基准利率、准确利率
                 */
                tips.setText(String.format(view.getResources().getString(R.string.business_tip), default_rate));
                base_rate.setText(String.valueOf(default_rate));
                base_rate.setHint(String.valueOf(default_rate));
                update_real_rate();

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
                rate_rt = et_change(s, default_rate, base_rate.getSelectionStart(), 2);
                update_real_rate();
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
                times_rt = et_change(s, default_times, times.getSelectionStart(), 2);
                update_real_rate();
            }
        });

        /*
         * 计算按钮事件
         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_click(view);
            }
        });
    }


    /**
     * click compute button
     */
    public void btn_click(View view) {
        try {
            double money = Double.parseDouble(total_loan.getText().toString()) * 10000;
            double rate = rate_rt * times_rt / 100;
            Intent intent = new Intent(getActivity(), LoanResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("type", 1);
            bundle.putInt("ways", back_way.getSelectedItemPosition() + 1);
            bundle.putDouble("total_money", money);
            bundle.putInt("years", Integer.parseInt(String.valueOf(year_num.getSelectedItem())));
            bundle.putDouble("rate", rate);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(view.getContext(), getString(R.string.toast_error), Toast.LENGTH_SHORT).show();
        }
    }
}
