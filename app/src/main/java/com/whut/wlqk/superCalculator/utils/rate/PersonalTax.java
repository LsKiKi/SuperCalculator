package com.whut.wlqk.superCalculator.utils.rate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 84074 on 2019/1/9.
 */
public class PersonalTax implements Tax {
    double rawMoney;
    double base;
   //2018 旧税表
    Map<Character,ArrayList<Double>> rateTable=new HashMap<Character,ArrayList<Double>>(){
        {
            put('1',new ArrayList<Double>(){
                {
                    add(0.03);
                    add(0.0);
                }
            });
            put('2',new ArrayList<Double>(){
                {
                    add(0.1);
                    add(210.0);
                }
            });
            put('3',new ArrayList<Double>(){
                {
                    add(0.2);
                    add(1410.0);
                }
            });
            put('4',new ArrayList<Double>(){
                {
                    add(0.25);
                    add(2620.0);
                }
            });
            put('5',new ArrayList<Double>(){
                {
                    add(0.3);
                    add(4410.0);
                }
            });
            put('6',new ArrayList<Double>(){
                {
                    add(0.35);
                    add(0.0);
                }
            });
            put('7',new ArrayList<Double>(){
                {
                    add(0.45);
                    add(15160.0);
                }
            });
        }
    };
    public double getRawMoney() {
        return rawMoney;
    }

    public void setRawMoney(double baseMoney) {
        this.rawMoney = baseMoney;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public PersonalTax(double rawMoney, double base) {
        this.rawMoney = rawMoney;
        this.base = base;
    }

    /**
     *
     * @return 返回个税
     */
    @Override
    public double result() {
        char a = level();
        double raw = rawMoney;
        double result = 0.0;
        System.out.println(a);
        if ( a=='0')
            result = 0.0;
        else{
            double rate = rateTable.get(a).get(0);
            double convenientNum = rateTable.get(a).get(1);
            result = (raw - base)*rate - convenientNum;
            System.out.println(rate);
            System.out.println(convenientNum);
        }

        return result;
    }


    private char level(){
        double money = rawMoney-base;
        if ( money <= 0 )
            return '0';
        else if( money<=3000 )
            return '1';
        else if ( money<=12000 )
            return '2';
        else if( money<=25000 )
            return '3';
        else if ( money<=35000 )
            return '4';
        else if ( money<=55000 )
            return '5';
        else if ( money<=80000 )
            return '6';
        else return '7';
    }
}
