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

import java.text.DecimalFormat;


public class combination extends Fragment implements View.OnClickListener {
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

        btn.setOnClickListener(this);

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

        /*
         * 年份 Spinner 选中事件
         */
        year_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * 根据选择的年份判断基本利率
                 */
                int pre_year = Integer.parseInt(year_num_mItems[position]);
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
                business_real_rate.setText(new DecimalFormat("#.####%").format(business_default_rate * business_times_rt / 100));
                fund_rate.setText(String.valueOf(fund_default_rate));
                fund_rate.setHint(String.valueOf(fund_default_rate));
                fund_real_rate.setText(new DecimalFormat("#.####%").format(fund_default_rate * fund_times_rt / 100));

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
                String str = s.toString();
                /*
                 * 若为空，使用默认利率
                 */
                if (str.isEmpty()) {
                    business_real_rate.setText(new DecimalFormat("#.####%").format(business_default_rate * business_times_rt / 100));
                    business_rate_rt = business_default_rate;
                }
                /*
                 * 若非空，使用输入的利率
                 */
                else {
                    /*
                     * 最多两位小数，若多于两位小数则无视本次输入
                     */
                    if (str.indexOf('.') != -1 && str.indexOf('.') < str.length() - 3) {
                        int index = business_rate.getSelectionStart();
                        s.delete(index - 1, index);
                        str = s.toString();
                    }
                    business_rate_rt = Double.parseDouble(str);
                    business_real_rate.setText(new DecimalFormat("#.####%").format(business_rate_rt * business_times_rt / 100));
                }
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
                String str = s.toString();
                /*
                 * 若为空，使用默认倍数
                 */
                if (str.isEmpty()) {
                    business_real_rate.setText(new DecimalFormat("#.####%").format(business_rate_rt * business_default_times / 100));
                    business_times_rt = business_default_times;
                }
                /*
                 * 若非空，使用输入的倍数
                 */
                else {
                    /*
                     * 最多两位小数，若多于两位小数则无视本次输入
                     */
                    if (str.indexOf('.') != -1 && str.indexOf('.') < str.length() - 3) {
                        int index = business_times.getSelectionStart();
                        s.delete(index - 1, index);
                        str = s.toString();
                    }
                    business_times_rt = Double.parseDouble(str);
                    business_real_rate.setText(new DecimalFormat("#.####%").format(business_rate_rt * business_times_rt / 100));
                }
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
                String str = s.toString();
                /*
                 * 若为空，使用默认利率
                 */
                if (str.isEmpty()) {
                    fund_real_rate.setText(new DecimalFormat("#.####%").format(fund_default_rate * fund_times_rt / 100));
                    fund_rate_rt = fund_default_rate;
                }
                /*
                 * 若非空，使用输入的利率
                 */
                else {
                    /*
                     * 最多两位小数，若多于两位小数则无视本次输入
                     */
                    if (str.indexOf('.') != -1 && str.indexOf('.') < str.length() - 3) {
                        int index = fund_rate.getSelectionStart();
                        s.delete(index - 1, index);
                        str = s.toString();
                    }
                    fund_rate_rt = Double.parseDouble(str);
                    fund_real_rate.setText(new DecimalFormat("#.####%").format(fund_rate_rt * fund_times_rt / 100));
                }
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
                String str = s.toString();
                /*
                 * 若为空，使用默认倍数
                 */
                if (str.isEmpty()) {
                    fund_real_rate.setText(new DecimalFormat("#.####%").format(fund_rate_rt * fund_default_times / 100));
                    fund_times_rt = fund_default_times;
                }
                /*
                 * 若非空，使用输入的倍数
                 */
                else {
                    /*
                     * 最多两位小数，若多于两位小数则无视本次输入
                     */
                    if (str.indexOf('.') != -1 && str.indexOf('.') < str.length() - 3) {
                        int index = fund_times.getSelectionStart();
                        s.delete(index - 1, index);
                        str = s.toString();
                    }
                    fund_times_rt = Double.parseDouble(str);
                    fund_real_rate.setText(new DecimalFormat("#.####%").format(fund_rate_rt * fund_times_rt / 100));
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        double money = Double.parseDouble(business_loan.getText().toString()) * 10000;
        double rate = Double.parseDouble(business_real_rate.getText().toString().split("%")[0]) / 100;
        double money_2 = Double.parseDouble(fund_loan.getText().toString()) * 10000;
        double rate_2 = Double.parseDouble(fund_real_rate.getText().toString().split("%")[0]) / 100;
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
    }
}
