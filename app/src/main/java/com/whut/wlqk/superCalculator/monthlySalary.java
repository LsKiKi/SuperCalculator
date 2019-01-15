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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.whut.wlqk.superCalculator.utils.tax.DbHelper;
import com.whut.wlqk.superCalculator.utils.tax.Wuxianyijin;

import java.text.DecimalFormat;
import java.util.List;

public class monthlySalary extends Fragment {

    TextView insurance_housing_fund;
    LinearLayout layout;
    Spinner spinner_city;
    EditText input_salary, et_threshold, et_fund, et_medical, et_endowment, et_unemployment, et_employment_injury, et_maternity;
    boolean inf_visible = false;
    DbHelper dbHelper = null;
    Button compute;
    Wuxianyijin wxyj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.monthlysalary, container, false);
        dbHelper = new DbHelper(this.getContext());
        dbHelper.openRead();
        init(view);
        return view;
    }

    private void init(final View view) {

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
        et_fund = view.findViewById(R.id.et_fund);
        et_medical = view.findViewById(R.id.et_medical);
        et_endowment = view.findViewById(R.id.et_endowment);
        et_unemployment = view.findViewById(R.id.et_unemployment);
        et_employment_injury = view.findViewById(R.id.et_employment_injury);
        et_maternity = view.findViewById(R.id.et_maternity);
        spinner_city = view.findViewById(R.id.spinner_city);
        input_salary = view.findViewById(R.id.input_salary);
        et_threshold = view.findViewById(R.id.input_threshold);
        compute = view.findViewById(R.id.btn_start_compute2);
        insurance_housing_fund = view.findViewById(R.id.textView23);
        layout = view.findViewById(R.id.ly_insurance_housing_fund);
        layout.setVisibility(View.GONE);
    }

    /**
     * padding data from db resource
     *
     * @param view parent
     */
    private void padding_data(View view) {
        /*
         * 在数据库资源中加载数据
         */
        final ArrayAdapter<String> city_adapter = new ArrayAdapter<>(view.getContext(), R.layout.item_spinner_select, cityData());
        city_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        spinner_city.setAdapter(city_adapter);
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
    }

    /**
     * add listen to view
     */
    private void add_listener(final View view) {
        /*
         * 五险一金隐藏显示
         */
        insurance_housing_fund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inf_visible) {
                    layout.setVisibility(View.GONE);
                    insurance_housing_fund.setBackground(getResources().getDrawable(R.drawable.down));
                } else {
                    layout.setVisibility(View.VISIBLE);
                    insurance_housing_fund.setBackground(getResources().getDrawable(R.drawable.up));
                }
                inf_visible = !inf_visible;
            }
        });

        /*
         * 城市 Spinner 选择事件
         */
        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = String.valueOf(parent.getItemAtPosition(position));
                wxyj = queryByCity(city);
                et_fund.setHint(new DecimalFormat("#.##").format(wxyj.getPublicfunds() * 100));
                et_medical.setHint(new DecimalFormat("#.##").format(wxyj.getMedicalTreatment() * 100));
                et_employment_injury.setHint(new DecimalFormat("#.##").format(wxyj.getInjury() * 100));
                et_endowment.setHint(new DecimalFormat("#.##").format(wxyj.getOldCare() * 100));
                et_maternity.setHint(new DecimalFormat("#.##").format(wxyj.getProcreation() * 100));
                et_unemployment.setHint(new DecimalFormat("#.##").format(wxyj.getUnempliyed() * 100));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
         * 计算按钮事件
         */
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_click(view);
            }
        });

        /*
         * 文本框编辑事件
         */
        input_salary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_change(s, 0, input_salary.getSelectionStart(), 0);
            }
        });
        et_threshold.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_change(s, 5000, input_salary.getSelectionStart(), 0);
            }
        });
        et_employment_injury.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_change(s, wxyj.getInjury(), et_employment_injury.getSelectionStart(), 0);
            }
        });
        et_unemployment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_change(s, wxyj.getUnempliyed(), et_unemployment.getSelectionStart(), 0);
            }
        });
        et_endowment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_change(s, wxyj.getOldCare(), et_endowment.getSelectionStart(), 0);
            }
        });
        et_medical.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_change(s, wxyj.getMedicalTreatment(), et_medical.getSelectionStart(), 0);
            }
        });
        et_fund.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_change(s, wxyj.getPublicfunds(), et_fund.getSelectionStart(), 0);
            }
        });
        et_maternity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_change(s, wxyj.getProcreation(), et_maternity.getSelectionStart(), 0);
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


    public void btn_click(View v) {
        try {
            double threshold, fund, medical, endowment, unemployment, maternity, employment_injury;
            double income = Double.parseDouble(input_salary.getText().toString());
            if (et_threshold.getText().toString().equals("")) {
                threshold = 5000;
            } else {
                threshold = Double.parseDouble(et_threshold.getText().toString());
            }
            try {
                fund = Double.parseDouble(et_fund.getText().toString()) / 100;
            } catch (Exception ex) {
                fund = wxyj.getPublicfunds();
            }
            try {
                medical = Double.parseDouble(et_medical.getText().toString()) / 100;
            } catch (Exception ex) {
                medical = wxyj.getMedicalTreatment();
            }
            try {
                endowment = Double.parseDouble(et_endowment.getText().toString()) / 100;
            } catch (Exception ex) {
                endowment = wxyj.getOldCare();
            }
            try {
                unemployment = Double.parseDouble(et_unemployment.getText().toString()) / 100;
            } catch (Exception ex) {
                unemployment = wxyj.getUnempliyed();
            }
            try {
                maternity = Double.parseDouble(et_maternity.getText().toString()) / 100;
            } catch (Exception ex) {
                maternity = wxyj.getProcreation();
            }
            try {
                employment_injury = Double.parseDouble(et_employment_injury.getText().toString()) / 100;
            } catch (Exception ex) {
                employment_injury = wxyj.getInjury();
            }


            Intent intent = new Intent(getActivity(), PersonActivity.class);
            Bundle bundle = new Bundle();

            bundle.putDouble("income", income);
            bundle.putDouble("threshold", threshold);
            bundle.putDouble("fund", fund);
            bundle.putDouble("medical", medical);
            bundle.putDouble("endowment", endowment);
            bundle.putDouble("unemployment", unemployment);
            bundle.putDouble("maternity", maternity);
            bundle.putDouble("employment_injury", employment_injury);
            bundle.putString("city", String.valueOf(spinner_city.getSelectedItem()));
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(v.getContext(), getString(R.string.toast_error), Toast.LENGTH_SHORT).show();
        }
    }
}
