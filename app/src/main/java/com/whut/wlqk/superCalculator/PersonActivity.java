package com.whut.wlqk.superCalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.whut.wlqk.superCalculator.utils.tax.PersonalTax;
import com.whut.wlqk.superCalculator.utils.tax.TaxInterface;

public class PersonActivity extends BaseActivity{

    TextView tax_before_income,person_tax,total_ihf;
    double income,threshold,person_get_tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_tax_result);

        tax_before_income = findViewById(R.id.tax_before_income);
        person_tax = findViewById(R.id.person_tax);
        total_ihf = findViewById(R.id.ihf);

        //获取结果
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //税前收入
        income = extras.getDouble("income");
        tax_before_income.setText(String.valueOf(income));

        //起征点
        threshold = extras.getDouble("threshold");

        TaxInterface taxInterface = new PersonalTax(income,threshold);
        //个人所得税
        person_get_tax = taxInterface.result();
        person_tax.setText(String.valueOf(person_get_tax));



    }
}
