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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class combination extends Fragment {
    EditText business_loan, business_rate, business_times, fund_loan, fund_rate, fund_times;
    Spinner back_way, year_num;
    TextView business_real_rate, fund_real_rate, tips;
    Button btn;
    double business_default_rate, business_default_times = 1.0, fund_default_rate, fund_default_times = 1.0;
    double business_rate_rt, business_times_rt = business_default_times, fund_rate_rt, fund_times_rt = fund_default_times;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.combination, container, false);
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
        business_loan = view.findViewById(R.id.c_business_loan);
        business_rate = view.findViewById(R.id.c_business_base_rate);
        business_times = view.findViewById(R.id.c_business_rate_times);
        business_real_rate = view.findViewById(R.id.c_business_real_rate);
        fund_loan = view.findViewById(R.id.c_fund_loan);
        fund_rate = view.findViewById(R.id.c_fund_base_rate);
        fund_times = view.findViewById(R.id.c_fund_rate_times);
        fund_real_rate = view.findViewById(R.id.c_fund_real_rate);
        back_way = view.findViewById(R.id.c_back_way);
        year_num = view.findViewById(R.id.c_year_num);
        tips = view.findViewById(R.id.c_tips);
        btn = view.findViewById(R.id.c_btn_start_compute);
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
        business_real_rate.setText(new DecimalFormat("#.####%").format(business_rate_rt * business_times_rt / 100));
        fund_real_rate.setText(new DecimalFormat("#.####%").format(fund_rate_rt * fund_times_rt / 100));
    }

    /**
     * add listen to view
     */
    private void add_listener(final View view) {
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
                business_default_rate = 4.35;
                fund_default_rate = 2.75;
                if (pre_year > 5) {
                    business_default_rate = 4.9;
                    fund_default_rate = 3.25;
                } else if (pre_year > 1) {
                    business_default_rate = 4.75;
                }
                business_rate_rt = business_default_rate;
                fund_rate_rt = fund_default_rate;

                /*
                 * 修改提示语句、基准利率、准确利率
                 */
                tips.setText(String.format(view.getResources().getString(R.string.c_tips), business_default_rate, fund_default_rate));
                business_rate.setText(String.valueOf(business_default_rate));
                business_rate.setHint(String.valueOf(business_default_rate));
                fund_rate.setText(String.valueOf(fund_default_rate));
                fund_rate.setHint(String.valueOf(fund_default_rate));
                update_real_rate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
         * 商业贷款数额 EditText 修改事件
         */
        business_loan.addTextChangedListener(new TextWatcher() {
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
                    business_loan.setTextSize(30);
                    business_loan.getPaint().setFakeBoldText(true);
                } else {
                    business_loan.setTextSize(20);
                    business_loan.getPaint().setFakeBoldText(false);
                }

                et_change(s, 0, business_loan.getSelectionStart(), 2);

            }
        });

        /*
         * 公积金贷款数额 EditText 修改事件
         */
        fund_loan.addTextChangedListener(new TextWatcher() {
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
                    fund_loan.setTextSize(30);
                    fund_loan.getPaint().setFakeBoldText(true);
                } else {
                    fund_loan.setTextSize(20);
                    fund_loan.getPaint().setFakeBoldText(false);
                }
                et_change(s, 0, fund_loan.getSelectionStart(), 2);
            }
        });

        /*
         * 商业利率 EditText 修改事件
         */
        business_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                business_rate_rt = et_change(s, business_default_rate, business_rate.getSelectionStart(), 2);
                update_real_rate();
            }
        });


        /*
         * 商业利率倍数 EditText 修改事件
         */
        business_times.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                business_times_rt = et_change(s, business_default_times, business_times.getSelectionStart(), 2);
                update_real_rate();

            }
        });

        /*
         * 公积金利率 EditText 修改事件
         */
        fund_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fund_rate_rt = et_change(s, fund_default_rate, fund_rate.getSelectionStart(), 2);
                update_real_rate();
            }
        });


        /*
         * 公积金利率倍数 EditText 修改事件
         */
        fund_times.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fund_times_rt = et_change(s, fund_default_times, fund_times.getSelectionStart(), 2);
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
            double money = Double.parseDouble(business_loan.getText().toString()) * 10000;
            double rate = business_rate_rt * business_times_rt / 100;
            double money_2 = Double.parseDouble(fund_loan.getText().toString()) * 10000;
            double rate_2 = fund_rate_rt * fund_times_rt / 100;;
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("type", 2);
            bundle.putInt("ways", back_way.getSelectedItemPosition() + 1);
            bundle.putDouble("total_money", money);
            bundle.putInt("years", year_num.getSelectedItemPosition() + 1);
            bundle.putDouble("rate", rate);
            bundle.putDouble("total_money_2", money_2);
            bundle.putDouble("rate_2", rate_2);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(view.getContext(), getString(R.string.toast_error), Toast.LENGTH_SHORT).show();
        }
    }
}
