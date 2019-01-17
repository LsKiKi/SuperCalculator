package com.whut.wlqk.superCalculator.utils.tax;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonalTaxTest {
    @Test
    public void salaryIsCorrect(){
        //个税-月薪单元测试
        double rawmoney = 8950.0;
        double base = 3500;
        double publicfunds = 0.12;
        double medicalTreatment = 0.02;
        double oldCare = 0.08;
        double unempliyed = 0.005;
        double injury = 0.00;
        double procreation = 0.00;
        Wuxianyijin wuxian = new Wuxianyijin("大连",oldCare, medicalTreatment, unempliyed, injury, procreation, publicfunds);
        CalWuxianyijin calWuxianyijin = new CalWuxianyijin(wuxian,rawmoney);
        double wuxianyijin = calWuxianyijin.injuryMoney+calWuxianyijin.medicalTreatmentMoney+calWuxianyijin.oldCareMoney
                +calWuxianyijin.procreationMoney+calWuxianyijin.publicFundsMoney+calWuxianyijin.unemployedMoney;
        double rawwuxianmoney = rawmoney-wuxianyijin;//工资-五险一金
        assertEquals(wuxianyijin,2013.75,0.01);//五险一金假言
        PersonalTax personalTax = new PersonalTax(rawwuxianmoney,base);
        assertEquals(personalTax.result(),133.625,0.001);//个人所得税假言
    }

}