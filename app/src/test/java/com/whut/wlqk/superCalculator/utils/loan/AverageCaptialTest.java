package com.whut.wlqk.superCalculator.utils.loan;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AverageCaptialTest {

    @Test
    public void businessIsCorrect(){
        //等额本金的商业贷款单元测试
        LoanInterface loanInterface = new AverageCaptial(1490000,7, 0.049);
        assertEquals(loanInterface.getTotalMoney()/10000,174.85,0.01);
        assertEquals(loanInterface.getFirstMonth(),23822.26,0.01);
        assertEquals(loanInterface.getTotalInterest()/10000,25.85,0.01);
        assertEquals(loanInterface.getDecreasedDifference(),72.43,0.01);
    }
    @Test
    public void fundIsCorrect(){
        //等额本金的公积金贷款单元测试
        LoanInterface loanInterface = new AverageCaptial(1560000,12, 0.0325);
        assertEquals(loanInterface.getTotalMoney()/10000,186.63,0.01);
        assertEquals(loanInterface.getFirstMonth(),15058.33,0.01);
        assertEquals(loanInterface.getTotalInterest()/10000,30.63,0.01);
        assertEquals(loanInterface.getDecreasedDifference(),29.34,0.01);
    }
    @Test
    public void businessfundIsCorrect(){
        //等额本金的组合贷款单元测试
        LoanInterface loanInterface = new AverageCaptial(760000,3, 0.0475);//商业贷款
        LoanInterface loanInterface2 = new AverageCaptial(210000,3,0.0275);//公积金贷款
        assertEquals(loanInterface.getTotalMoney()/10000+loanInterface2.getTotalMoney()/10000,103.45,0.01);
        assertEquals(loanInterface.getFirstMonth()+loanInterface2.getFirstMonth(),30434.03,0.01);
        assertEquals(loanInterface.getTotalInterest()/10000+loanInterface2.getTotalInterest()/10000,6.45,0.01);
        assertEquals(loanInterface.getDecreasedDifference()+loanInterface2.getDecreasedDifference(),96.93,0.01);
    }
}