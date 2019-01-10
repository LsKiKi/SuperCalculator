package com.whut.wlqk.superCalculator.utils.loan;

public class AverageCaptial extends LoanInstance implements Loan {

    /**
     * 等额本金
     * @param amountOwed 欠款额
     * @param monthNum 还款月数
     * @param rate 利率
     */
    public AverageCaptial(double amountOwed, int monthNum, double rate) {
        super(amountOwed, monthNum, rate);
    }

    @Override
    public double getTotalMoney() {
        return getTotalInterest()+amountOwed;
    }

    @Override
    public double getTotalInterest() {
        double result;
        result = (monthNum+1)*amountOwed*monthRate/2;
        //还款总利息=（还款月数+1）×贷款额×月利率/2
        return result;
    }


    @Override
    public double getMonthlyMoney() {
        return 0;
    }

    /**
     * 等额本金的首月还款额
     * @return
     */
    @Override
    public double getFirstMonth(){
        double result;
        result = (amountOwed/monthNum)+amountOwed*monthRate;
        //每月还本付息金额=（本金/还款月数）+（本金-累计已还本金）×月利率
        return result;
    }

    /**
     * 每月递减额
     * @return
     */
    @Override
    public double getDecreasedDifference(){
        double result;
        result = amountOwed/monthNum*monthRate;
                //每月本金*月利率
        return result;
    }
}
