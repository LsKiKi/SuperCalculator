package com.whut.wlqk.superCalculator.utils.tax;

/**
 * Created by 84074 on 2019/1/9.
 */
public interface Tax {

    /**
     *
     * @return 税额
     * 个税和年终奖税额
     * eg: Tax person = new PersonalTax(6000,5000);
     *     Tax year = new YearFinalTax(20000,0);
     */
    public double result();
}

