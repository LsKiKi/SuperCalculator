package com.whut.wlqk.superCalculator.utils.loan;

public interface LoanInterface {
    //还款总额
    double getTotalMoney();

    //总利息
    double getTotalInterest();

    // 等额本息下 每月还款额
    double getMonthlyMoney();

    // 等额本金下 首月还款额
    double getFirstMonth();

    // 等额本金下 每月递减额
    double getDecreasedDifference();
}
