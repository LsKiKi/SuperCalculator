package com.whut.wlqk.superCalculator;

import com.whut.wlqk.superCalculator.utils.loan.AverageCaptial;
import com.whut.wlqk.superCalculator.utils.loan.AverageCaptialPlusInterest;
import com.whut.wlqk.superCalculator.utils.loan.LoanInterface;
import com.whut.wlqk.superCalculator.utils.tax.PersonalTax;
import com.whut.wlqk.superCalculator.utils.tax.TaxInterface;
import com.whut.wlqk.superCalculator.utils.tax.YearFinalTax;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void finalTax(){
        TaxInterface taxInterface = new YearFinalTax(320080);
        TaxInterface taxInterface2 = new PersonalTax(6000,5000);
        System.out.println(taxInterface.result());
        System.out.println(taxInterface2.result());
    }

    @Test
    public void ACLoan(){
        LoanInterface loanInterface = new AverageCaptial(340000,120, 0.049);
        System.out.println(loanInterface.getTotalMoney());
        System.out.println(loanInterface.getTotalInterest());
        System.out.println(((AverageCaptial) loanInterface).getFirstMonth());
        System.out.println(((AverageCaptial) loanInterface).getDecreasedDifference());
    }

    @Test
    public void ACPLoan(){
        LoanInterface loanInterface = new AverageCaptialPlusInterest(340000,120, 0.0325);
        System.out.println(loanInterface.getTotalMoney());
        System.out.println(loanInterface.getTotalInterest());
        System.out.println(((AverageCaptialPlusInterest) loanInterface).getMonthlyMoney());
    }
}