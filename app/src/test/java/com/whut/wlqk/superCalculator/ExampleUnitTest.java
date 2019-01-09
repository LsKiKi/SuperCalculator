package com.whut.wlqk.superCalculator;

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
        System.out.println(tax.result());

    }
}