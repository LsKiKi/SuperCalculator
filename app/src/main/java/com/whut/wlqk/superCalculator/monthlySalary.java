package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

        et_fund = view.findViewById(R.id.et_fund);
        et_medical = view.findViewById(R.id.et_medical);
        et_endowment = view.findViewById(R.id.et_endowment);
        et_unemployment = view.findViewById(R.id.et_unemployment);
        et_employment_injury = view.findViewById(R.id.et_employment_injury);
        et_maternity = view.findViewById(R.id.et_maternity);
        spinner_city = view.findViewById(R.id.spinner_city);
        input_salary = view.findViewById(R.id.input_salary);
        et_threshold = view.findViewById(R.id.input_threshold);

        final ArrayAdapter<String> city_adapter = new ArrayAdapter<>(view.getContext(), R.layout.item_spinner_select, cityData());
        city_adapter.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        spinner_city.setAdapter(city_adapter);

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Wuxianyijin wxyj = queryByCity(city_adapter.getItem(position));
                et_fund.setText(new DecimalFormat("#.##").format(wxyj.getPublicfunds() * 100));
                et_medical.setText(new DecimalFormat("#.##").format(wxyj.getMedicalTreatment() * 100));
                et_employment_injury.setText(new DecimalFormat("#.##").format(wxyj.getInjury() * 100));
                et_endowment.setText(new DecimalFormat("#.##").format(wxyj.getOldCare() * 100));
                et_maternity.setText(new DecimalFormat("#.##").format(wxyj.getProcreation() * 100));
                et_unemployment.setText(new DecimalFormat("#.##").format(wxyj.getUnempliyed() * 100));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
