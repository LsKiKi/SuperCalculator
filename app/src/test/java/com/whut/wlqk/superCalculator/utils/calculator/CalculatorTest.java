package com.whut.wlqk.superCalculator.utils.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void addisCorrect(){
        //对加法单元测试
        Calculator cal = new Calculator();
        String expression = "34.67+56.89";
        double result = Double.valueOf(cal.calculate(expression));
        assertEquals(result,91.56,0.000001);
    }

    @Test
    public void substrationisCorrect(){
        //对减法单元测试
        Calculator cal = new Calculator();
        String expression = "85.75-78.42";
        double result = Double.valueOf(cal.calculate(expression));
        assertEquals(result,7.33,0.000001);
    }
    @Test
    public void divisionisCorrect(){
        //对除法单元测试
        Calculator cal = new Calculator();
        String expression = "85.649/56.43";
        double result = Double.valueOf(cal.calculate(expression));
        assertEquals(result,1.5177919,0.000001);
    }
    @Test
    public void multiplicationisCorrect(){
        //对乘法单元测试
        Calculator cal = new Calculator();
        String expression = "84.5531*62.35";
        double result = Double.valueOf(cal.calculate(expression));
        assertEquals(result,5271.885785,0.000001);
    }

}