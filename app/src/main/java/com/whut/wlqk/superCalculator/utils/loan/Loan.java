package com.whut.wlqk.superCalculator.utils.loan;

public class Loan {
    double amountOwed;  //欠款总额
    int monthNum; //还款月数
    double rate;  //贷款利率 商业4.9 公积金3.25
    protected double monthRate; //月利率

    /**
     * @param amountOwed 借款总额
     * @param yearNum    还款年数 应前端要求修改为年数
     * @param rate       年利率
     */
    public Loan(double amountOwed, int yearNum, double rate) {
        this.amountOwed = amountOwed;
        this.monthNum = yearNum * 12;  //年份转换为月份
        this.rate = rate;
        this.monthRate = rate / 12; //年利率转换为月利率
    }

    public double getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(double amountOwed) {
        this.amountOwed = amountOwed;
    }

    public double getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
        this.monthRate = rate / 12; //年利率转换为月利率
    }
}
