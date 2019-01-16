package com.whut.wlqk.superCalculator.utils.loan;

public class AverageCaptialPlusInterest extends Loan implements LoanInterface {

    /**
     * 等额本息方式
     *
     * @param amountOwed
     * @param yearNum
     * @param rate
     */
    public AverageCaptialPlusInterest(double amountOwed, int yearNum, double rate) {
        super(amountOwed, yearNum, rate);
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
        if (rate == 0.0)  //利率为零？？？？？？
            return amountOwed / monthNum;
        result = (amountOwed * monthRate * Math.pow(1 + monthRate, monthNum)) / (Math.pow(1 + monthRate, monthNum) - 1);
        // 每月还款金额 = [总贷款 × 月利率 × (1 + 月利率)^总月数] ÷ [(1 + 月利率)^总月数 - 1]
        return result;
    }

    /**
     * 无用
     *
     * @return
     */
    @Override
    public double getFirstMonth() {
        return getMonthlyMoney();
    }

    /**
     * 无用
     *
     * @return
     */
    @Override
    public double getDecreasedDifference() {
        return 0;
    }
}
