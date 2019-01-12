package com.whut.wlqk.superCalculator.utils.loan;

public class AverageCaptialPlusInterest extends Loan implements LoanInterface {

    public AverageCaptialPlusInterest(double amountOwed, int monthNum, double rate) {
        super(amountOwed, monthNum, rate);
    }

    @Override
    public double getTotalMoney() {

        return getMonthlyMoney() * monthNum;
    }

    @Override
    public double getTotalInterest() {
        return getMonthlyMoney() * monthNum - amountOwed;
    }

    /**
     * 等额本息 每月还款额
     *
     * @return
     */
    @Override
    public double getMonthlyMoney() {
        double result;
        result = (amountOwed * monthRate * Math.pow(1 + monthRate, monthNum)) / (Math.pow(1 + monthRate, monthNum) - 1);
        // 每月还款金额 = [总贷款 × 月利率 × (1 + 月利率)^总月数] ÷ [(1 + 月利率)^总月数 - 1]
        return result;
    }

    @Override
    public double getFirstMonth() {
        return getMonthlyMoney();
    }

    @Override
    public double getDecreasedDifference() {
        return 0;
    }
}
