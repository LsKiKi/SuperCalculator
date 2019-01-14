package com.whut.wlqk.superCalculator;

import android.content.Context;
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

import com.whut.wlqk.superCalculator.utils.loan.AverageCaptial;
import com.whut.wlqk.superCalculator.utils.loan.AverageCaptialPlusInterest;
import com.whut.wlqk.superCalculator.utils.loan.Loan;

import java.io.Serializable;
import java.text.DecimalFormat;


public class fund extends Fragment implements View.OnClickListener {


    Spinner back_way, year_num;
    EditText total_loan, base_rate, times;
    TextView real_rate, tips;
    int id1, total_years;
    Button admit;
    double default_rate, default_times = 1.0;
    double rate_, times_ = default_times;

    public fund() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fund, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        total_loan = view.findViewById(R.id.money);
        admit = view.findViewById(R.id.admit);
        base_rate = view.findViewById(R.id.fund_base_rate);
        times = view.findViewById(R.id.fund_rate_times);
        real_rate = view.findViewById(R.id.fund_real_rate);
        tips = view.findViewById(R.id.fund_tips);
        back_way = view.findViewById(R.id.fund_back_way);
        year_num = view.findViewById(R.id.fund_year_num);



        /*
         *在xml资料中加载数据
         */
        final String[] back_way_mItems = getResources().getStringArray(R.array.back_ways);
        final String[] year_num_mItems = getResources().getStringArray(R.array.year);

        /*
         * 声明ArrayAdapter、填充数据、并绑定到组件中
         * 使用自定义 Spinner 样式
         */
        ArrayAdapter<String> back_way_adapter = new ArrayAdapter<>(view.getContext(), R.layout.item_spinner_select, back_way_mItems);
        ArrayAdapter<String> year_num_adapter = new ArrayAdapter<>(view.getContext(), R.layout.item_spinner_select, year_num_mItems);

        back_way_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        back_way.setAdapter(back_way_adapter);
        year_num_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        year_num.setAdapter(year_num_adapter);

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

            }
        });

        back_way.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * ids是刚刚新建的list里面的ID
                 */
                id1 = position + 1;
                System.out.println(id1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                id1 = 1;
            }
        });

        /*
         * 年份 Spinner 选中事件
         */
        year_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * 根据选择的年份判断基本利率
                 */
                int pre_year = Integer.parseInt(year_num_mItems[position]);
                default_rate = pre_year > 5 ? 3.25 : 2.75;

                /*
                 * 修改提示语句、基准利率、准确利率
                 */
                tips.setText(String.format(view.getResources().getString(R.string.fund_tip), default_rate));
                base_rate.setText(String.valueOf(default_rate));
                base_rate.setHint(String.valueOf(default_rate));
                real_rate.setText(new DecimalFormat("#.####%").format(default_rate * times_ / 100));
                total_years = Integer.parseInt(year_num.getSelectedItem().toString());
                System.out.println(total_years);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                total_years = 1;
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
                String str = s.toString();
                /*
                 * 若为空，使用默认利率
                 */
                if (str.isEmpty()) {
                    real_rate.setText(new DecimalFormat("#.####%").format(default_rate * times_ / 100));
                    rate_ = default_rate;
                }
                /*
                 * 若非空，使用输入的利率
                 */
                else {
                    /*
                     * 最多两位小数，若多于两位小数则无视本次输入
                     */
                    if (str.indexOf('.') != -1 && str.indexOf('.') < str.length() - 3) {
                        int index = base_rate.getSelectionStart();
                        s.delete(index - 1, index);
                        str = s.toString();
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
                String str = s.toString();
                /*
                 * 若为空，使用默认倍数
                 */
                if (str.isEmpty()) {
                    real_rate.setText(new DecimalFormat("#.####%").format(rate_ * default_times / 100));
                    times_ = default_times;
                }
                /*
                 * 若非空，使用输入的倍数
                 */
                else {
                    /*
                     * 最多两位小数，若多于两位小数则无视本次输入
                     */
                    if (str.indexOf('.') != -1 && str.indexOf('.') < str.length() - 3) {
                        int index = times.getSelectionStart();
                        s.delete(index - 1, index);
                        str = s.toString();
                    }
                    times_ = Double.parseDouble(str);
                    real_rate.setText(new DecimalFormat("#.####%").format(rate_ * times_ / 100));
                }
            }
        });
        admit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        double money = Double.parseDouble(total_loan.getText().toString())*10000;
        double rate = Double.parseDouble(real_rate.getText().toString().split("%")[0]) / 100;
        System.out.println(money);
//        switch (id1) {
//            case 1:
//                Loan loan1 = new AverageCaptialPlusInterest(money, total_years, rate);
//                System.out.println(((AverageCaptialPlusInterest) loan1).getTotalMoney());
//                break;
//            case 2:
//                Loan loan2 = new AverageCaptial(money, total_years, rate);
//                System.out.println(((AverageCaptial) loan2).getTotalMoney());
//                break;
//        }
        //Intent intent = new Intent(context,ResultActivity.class);
        Intent intent=new Intent(getActivity(), ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        bundle.putInt("ways",id1);
        bundle.putDouble("total_money",money);
        bundle.putInt("years",total_years);
        bundle.putDouble("rate",rate);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
