package com.whut.wlqk.superCalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.whut.wlqk.superCalculator.utils.tax.CalWuxianyijin;
import com.whut.wlqk.superCalculator.utils.tax.PersonalTax;
import com.whut.wlqk.superCalculator.utils.tax.TaxInterface;
import com.whut.wlqk.superCalculator.utils.tax.Wuxianyijin;

import java.text.DecimalFormat;

public class PersonActivity extends BaseActivity{

    TextView tax_before_income,person_tax,total_ihf,total_get;
    TextView fund_public,medical_care,old_care,no_job,job_injury,procreation;
    double income,threshold,person_get_tax;
    double fund,medical,endowment,unemployment,maternity,employment_injury;
    double fund_,medical_,endowment_,unemployment_,maternity_,employment_injury_,ihf_;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_tax_result);

        total_get = findViewById(R.id.total_get);
        tax_before_income = findViewById(R.id.tax_before_income);
        person_tax = findViewById(R.id.person_tax);
        total_ihf = findViewById(R.id.ihf);
        fund_public = findViewById(R.id.fund);
        medical_care = findViewById(R.id.medical);
        old_care = findViewById(R.id.endowment);
        no_job = findViewById(R.id.unemployment);
        job_injury = findViewById(R.id.employment_injury);
        procreation = findViewById(R.id.maternity);

        //获取结果
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //税前收入
        income = extras.getDouble("income");
        tax_before_income.setText(String.valueOf(income));

        city = extras.getString("city");
        fund = extras.getDouble("fund");
        medical = extras.getDouble("medical");
        endowment = extras.getDouble("endowment");
        unemployment = extras.getDouble("unemployment");
        maternity = extras.getDouble("maternity");
        employment_injury = extras.getDouble("employment_injury");

        //计算五险一金
        Wuxianyijin wxyj = new Wuxianyijin(city,endowment,medical,unemployment,employment_injury,maternity,fund);
        CalWuxianyijin calWuxianyijin = new CalWuxianyijin(wxyj,income);
        ihf_ = calWuxianyijin.wuxianyijinTotal();
        total_ihf.setText(new DecimalFormat("#.##").format(ihf_));

        //扣除五险一金后的工资
        income =income-ihf_;

        //起征点
        threshold = extras.getDouble("threshold");

        TaxInterface taxInterface = new PersonalTax(income,threshold);

        //个人所得税
        person_get_tax = taxInterface.result();
        person_tax.setText(new DecimalFormat("#.##").format(person_get_tax));

        //到手所得
        total_get.setText(new DecimalFormat("#.##").format(income-person_get_tax));
        //五险一金详细
        fund_ = calWuxianyijin.getPublicFundsMoney();
        fund_public.setText(new DecimalFormat("#.##").format(fund_));
        employment_injury_ = calWuxianyijin.getInjuryMoney();
        job_injury.setText(new DecimalFormat("#.##").format(employment_injury_));
        endowment_ = calWuxianyijin.getOldCareMoney();
        old_care.setText(new DecimalFormat("#.##").format(endowment_));
        maternity_ = calWuxianyijin.getProcreationMoney();
        procreation.setText(new DecimalFormat("#.##").format(maternity_));
        medical_ = calWuxianyijin.getMedicalTreatmentMoney();
        medical_care.setText(new DecimalFormat("#.##").format(medical_));
        unemployment_ = calWuxianyijin.getUnemployedMoney();
        no_job.setText(new DecimalFormat("#.##").format(unemployment_));
    }
}
