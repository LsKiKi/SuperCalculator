package com.whut.wlqk.superCalculator.utils.loan;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AverageCaptialPlusInterestTest {
    @Test
    public void businessIsCorrect(){
        //等额本息的商业贷款单元测试
        LoanInterface loanInterface = new AverageCaptialPlusInterest(8500000,30, 0.049);
        assertEquals(loanInterface.getTotalMoney()/10000,1624.02,0.01);
        assertEquals(loanInterface.getFirstMonth(),45111.77,0.01);
        assertEquals(loanInterface.getTotalInterest()/10000,774.02,0.01);
        assertEquals(loanInterface.getDecreasedDifference(),0,0.01);
    }
    @Test
    public void fundIsCorrect(){
        //等额本金的公积金贷款单元测试
        LoanInterface loanInterface = new AverageCaptialPlusInterest(550000,2, 0.0275);
        assertEquals(loanInterface.getTotalMoney()/10000,56.58,0.01);
        assertEquals(loanInterface.getFirstMonth(),23578.89,0.01);
        assertEquals(loanInterface.getTotalInterest()/10000,1.58,0.01);
        assertEquals(loanInterface.getDecreasedDifference(),0,0.01);
    }
    @Test
    public void businessfundIsCorrect(){
        //等额本金的组合贷款单元测试
        LoanInterface loanInterface = new AverageCaptialPlusInterest(2030000,24, 0.049);//商业贷款
        LoanInterface loanInterface2 = new AverageCaptialPlusInterest(590000,24,0.0325);//公积金贷款
        assertEquals(loanInterface.getTotalMoney()/10000+loanInterface2.getTotalMoney()/10000,430.65,0.01);
        assertEquals(loanInterface.getFirstMonth()+loanInterface2.getFirstMonth(),14953.27,0.01);
        assertEquals(loanInterface.getTotalInterest()/10000+loanInterface2.getTotalInterest()/10000,168.65,0.01);
        assertEquals(loanInterface.getDecreasedDifference()+loanInterface2.getDecreasedDifference(),0,0.01);
    }
}