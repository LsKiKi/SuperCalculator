package com.whut.wlqk.superCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.whut.wlqk.superCalculator.utils.loan.AverageCaptial;
import com.whut.wlqk.superCalculator.utils.loan.AverageCaptialPlusInterest;
import com.whut.wlqk.superCalculator.utils.loan.Loan;

public class ResultActivity extends BaseActivity{

    TextView first_pay_money,pay_interest,pay_total_money,loan_total_money;
    TextView pay_years,per_month_pay,per_month_sub,pay_method;
    double f_p_m,p_i,p_t_m,l_t_m,p_y,p_m_p,p_m_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tax_result);

        first_pay_money = findViewById(R.id.first_month_pay);//首月还款
        pay_interest = findViewById(R.id.pay_interest);//支付利息
        pay_total_money = findViewById(R.id.pay_total_money);//还款总额
        loan_total_money = findViewById(R.id.loan_total_money);//贷款总额
        pay_years = findViewById(R.id.loan_year_num);//按揭年数
        per_month_pay = findViewById(R.id.per_month_money);//每月还款额
        per_month_sub = findViewById(R.id.per_month_sub);//每月递减额
        pay_method = findViewById(R.id.pay_method);
        //获取结果
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int ways=extras.getInt("ways");
        int years=extras.getInt("years");
        double money=extras.getDouble("total_money");
        double rate=extras.getDouble("rate");
        switch (ways){
            case 1:
                //等额本息
                Loan loan1 = new AverageCaptialPlusInterest(money, years, rate);
                p_t_m=((AverageCaptialPlusInterest) loan1).getTotalMoney();
                p_i=((AverageCaptialPlusInterest) loan1).getTotalInterest();
                p_m_p=((AverageCaptialPlusInterest) loan1).getMonthlyMoney();
                f_p_m=p_m_p;
                p_m_s=0;
                pay_method.setText("每月还款");
                per_month_pay.setText(String.valueOf(p_m_p));
                break;
            case 2:
                Loan loan2 = new AverageCaptial(money, years, rate);
                p_t_m=((AverageCaptial) loan2).getTotalMoney();
                p_i=((AverageCaptial) loan2).getTotalInterest();
                p_m_s=((AverageCaptial) loan2).getDecreasedDifference();
                f_p_m=((AverageCaptial) loan2).getFirstMonth();
                pay_method.setText("首月还款");
                break;
        }
        l_t_m=p_t_m+p_i;

        first_pay_money.setText(String.valueOf(f_p_m));
        pay_interest.setText(String.valueOf(p_i));
        pay_total_money.setText(String.valueOf(p_t_m));
        loan_total_money.setText(String.valueOf(l_t_m));
        pay_years.setText(String.valueOf(years));
        per_month_sub.setText(String.valueOf(p_m_s));
    }
}
