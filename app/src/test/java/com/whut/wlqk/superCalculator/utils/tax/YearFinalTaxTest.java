package com.whut.wlqk.superCalculator.utils.tax;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YearFinalTaxTest {
    @Test
    public void finalIsCorrect(){
        //个税-年终奖单元测试
        double rawMoney = 5000;
        YearFinalTax yearMoney = new YearFinalTax(rawMoney);
        assertEquals(yearMoney.result(),150,0.01);
        double rawMoney2 = 189000;
        YearFinalTax yearMoney2 = new YearFinalTax(rawMoney2);
        assertEquals(yearMoney2.result(),36390,0.01);
    }
}