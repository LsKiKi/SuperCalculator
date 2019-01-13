package com.whut.wlqk.superCalculator.utils.tax;


public class YearFinalTax extends PersonalTax implements TaxInterface {


    /**
     * @param rawMoney 年终奖
     */
    public YearFinalTax(double rawMoney) {
        super(rawMoney, 0);
    }

    /**
     * 年终奖计算
     * raw/12确定税档
     *
     * @return 年终奖税额
     */
    @Override
    public double result() {
        char a = level(rawMoney / 12);
        double result = 0.0;
        if (a == '0')
            result = 0.0;
        else {
            double rate = rateTable.get(a).get(0);
            double convenientNum = rateTable.get(a).get(1);
            result = (rawMoney - base) * rate - convenientNum;
        }
        return result;
    }


}
