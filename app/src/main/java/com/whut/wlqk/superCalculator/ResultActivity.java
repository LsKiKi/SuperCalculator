package com.whut.wlqk.superCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.whut.wlqk.superCalculator.utils.loan.AverageCaptial;
import com.whut.wlqk.superCalculator.utils.loan.AverageCaptialPlusInterest;
import com.whut.wlqk.superCalculator.utils.loan.LoanInterface;

public class ResultActivity extends BaseActivity {

    TextView first_pay_money, pay_interest, pay_total_money, loan_total_money;
    TextView pay_years, per_month_pay, per_month_sub, pay_method;
    double f_p_m, p_i, p_t_m, l_t_m, p_m_p, p_m_s;
    int ways, years, type;
    double money, rate, money_2 = 0.0, rate_2 = 0.0;

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

        type = extras.getInt("type");
        ways = extras.getInt("ways");
        years = extras.getInt("years");
        money = extras.getDouble("total_money");
        rate = extras.getDouble("rate");
        if (type == 2) {
            money_2 = extras.getDouble("total_money_2");
            rate_2 = extras.getDouble("rate_2");

        }

        //判断付款方式
        switch (ways) {
            case 1:
                //等额本息
                LoanInterface loan1 = new AverageCaptialPlusInterest(money, years, rate);
                LoanInterface loan1_2 = new AverageCaptialPlusInterest(money_2, years, rate_2);
                p_t_m = loan1.getTotalMoney() + loan1_2.getTotalMoney();
                p_i = loan1.getTotalInterest() + loan1_2.getTotalInterest();
                p_m_p = loan1.getMonthlyMoney() + loan1_2.getMonthlyMoney();
                f_p_m = p_m_p;
                p_m_s = 0;
                pay_method.setText("每月还款");
                per_month_pay.setText(String.valueOf(p_m_p));
                break;
            case 2:
                //等额本金
                LoanInterface loan2 = new AverageCaptial(money, years, rate);
                LoanInterface loan2_2 = new AverageCaptial(money_2, years, rate_2);
                p_t_m = loan2.getTotalMoney() + loan2_2.getTotalMoney();
                p_i = loan2.getTotalInterest() + loan2_2.getTotalInterest();
                p_m_s = loan2.getDecreasedDifference() + loan2_2.getDecreasedDifference();
                f_p_m = loan2.getFirstMonth() + loan2_2.getFirstMonth();
                pay_method.setText("首月还款");
                break;
        }

        //贷款总额
        l_t_m = p_t_m + p_i;

        first_pay_money.setText(String.valueOf(f_p_m));
        pay_interest.setText(String.valueOf(p_i));
        pay_total_money.setText(String.valueOf(p_t_m));
        loan_total_money.setText(String.valueOf(l_t_m));
        pay_years.setText(String.valueOf(years));
        per_month_sub.setText(String.valueOf(p_m_s));
    }
}
