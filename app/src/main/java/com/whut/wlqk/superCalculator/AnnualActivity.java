package com.whut.wlqk.superCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.whut.wlqk.superCalculator.utils.tax.YearFinalTax;

import java.text.DecimalFormat;

public class AnnualActivity extends BaseActivity{

    TextView get_all,tax_before_inc,per_person_tax;
    double annual_bonus , annual_tax , final_annual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annual_result);

        get_all = findViewById(R.id.get_all);
        tax_before_inc = findViewById(R.id.tax_before_inc);
        per_person_tax = findViewById(R.id.per_person_tax);

        //获取结果
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //计算年终奖税额
        annual_bonus = extras.getDouble("annualbonus");
        YearFinalTax yearFinalTax = new YearFinalTax(annual_bonus);
        annual_tax = yearFinalTax.result();
        final_annual = annual_bonus-annual_tax;

        //结果显示
        get_all.setText(new DecimalFormat("#.##").format(final_annual));
        tax_before_inc.setText(new DecimalFormat("#.##").format(annual_bonus));
        per_person_tax.setText(new DecimalFormat("#.##").format(annual_tax));

    }
}
