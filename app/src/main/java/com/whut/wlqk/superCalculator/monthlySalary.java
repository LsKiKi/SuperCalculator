package com.whut.wlqk.superCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.whut.wlqk.superCalculator.utils.tax.DbHelper;
import com.whut.wlqk.superCalculator.utils.tax.Wuxianyijin;

import java.text.DecimalFormat;
import java.util.List;

public class monthlySalary extends Fragment implements View.OnClickListener {

    TextView insurance_housing_fund;
    LinearLayout layout;
    Spinner spinner_city;
    String city;
    EditText input_salary, et_threshold, et_fund, et_medical, et_endowment, et_unemployment, et_employment_injury, et_maternity;
    boolean inf_visible = false;
    DbHelper dbHelper = null;
    Button compute;

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

        final ArrayAdapter<String> city_adapter = new ArrayAdapter<>(view.getContext(), R.layout.item_spinner_select, cityData());
        city_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        spinner_city.setAdapter(city_adapter);

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Wuxianyijin wxyj = queryByCity(city_adapter.getItem(position));
                city = city_adapter.getItem(position);
                et_fund.setText(new DecimalFormat("#.##").format(wxyj.getPublicfunds() * 100));
                et_medical.setText(new DecimalFormat("#.##").format(wxyj.getMedicalTreatment() * 100));
                et_employment_injury.setText(new DecimalFormat("#.##").format(wxyj.getInjury() * 100));
                et_endowment.setText(new DecimalFormat("#.##").format(wxyj.getOldCare() * 100));
                et_maternity.setText(new DecimalFormat("#.##").format(wxyj.getProcreation() * 100));
                et_unemployment.setText(new DecimalFormat("#.##").format(wxyj.getUnempliyed() * 100));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                city = city_adapter.getItem(0);
            }
        });

        //按钮设置监听
        compute.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        double threshold;
        double income = Double.parseDouble(input_salary.getText().toString());
        if (et_threshold.getText().toString().equals("")) {
            threshold = 5000;
        } else {
            threshold = Double.parseDouble(et_threshold.getText().toString());
        }
        double fund = Double.parseDouble(et_fund.getText().toString()) / 100;
        double medical = Double.parseDouble(et_medical.getText().toString()) / 100;
        double endowment = Double.parseDouble(et_endowment.getText().toString()) / 100;
        double unemployment = Double.parseDouble(et_unemployment.getText().toString()) / 100;
        double maternity = Double.parseDouble(et_maternity.getText().toString()) / 100;
        double employment_injury = Double.parseDouble(et_employment_injury.getText().toString()) / 100;

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
        bundle.putString("city", city);

        intent.putExtras(bundle);
        startActivity(intent);
    }
}
