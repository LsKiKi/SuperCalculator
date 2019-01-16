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
        char a = level(rawMoney / 12); //确定税档
        double result;
        if (a == '0')  //无税额
            result = 0.0;
        else {
            double rate = rateTable.get(a).get(0);
            double convenientNum = rateTable.get(a).get(1);
            result = (rawMoney - base) * rate - convenientNum;  //税额=（年终奖-起征点）*税率-速算扣除数
        }
        return result;
    }


}
