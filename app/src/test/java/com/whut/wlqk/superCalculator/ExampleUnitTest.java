package com.whut.wlqk.superCalculator;

import com.whut.wlqk.superCalculator.utils.loan.AverageCaptial;
import com.whut.wlqk.superCalculator.utils.loan.AverageCaptialPlusInterest;
import com.whut.wlqk.superCalculator.utils.loan.Loan;
import com.whut.wlqk.superCalculator.utils.tax.PersonalTax;
import com.whut.wlqk.superCalculator.utils.tax.Tax;
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
        Tax tax = new YearFinalTax(320080);
        Tax tax2 = new PersonalTax(6000,5000);
        System.out.println(tax.result());
        System.out.println(tax2.result());
    }

    @Test
    public void ACLoan(){
        Loan loan = new AverageCaptial(340000,120, 0.049);
        System.out.println(loan.getTotalMoney());
        System.out.println(loan.getTotalInterest());
        System.out.println(((AverageCaptial) loan).getFirstMonth());
        System.out.println(((AverageCaptial) loan).getDecreasedDifference());
    }

    @Test
    public void ACPLoan(){
        Loan loan = new AverageCaptialPlusInterest(340000,120, 0.0325);
        System.out.println(loan.getTotalMoney());
        System.out.println(loan.getTotalInterest());
        System.out.println(((AverageCaptialPlusInterest) loan).getMonthlyMoney());
    }
}