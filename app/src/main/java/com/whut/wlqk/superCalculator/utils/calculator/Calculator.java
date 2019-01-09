package com.whut.wlqk.superCalculator.utils.calculator;

import java.util.Stack;

/**
 * Created by bingo.lee on 2019/1/9.
 */
public class Calculator {
    private Stack<Double> optr = null;
    private Stack<Character> opnd = null;

    /**
     * 初始化栈
     */
    private void Init() {
        optr = new Stack<>();
        opnd = new Stack<>();
        opnd.push('#');
    }

    /**
     * 主算法
     *
     * @param str 算术表达式
     * @throws Exception 异常
     */
    private void prase(String str) throws Exception {
        char[] str1 = str.toCharArray();
        boolean flag = false;
        int i = 0;
        while (i < str.length()) {
            int k = 0;
            while ((str1[i] <= '9' && str1[i] >= '0') || str1[i] == '.') {
                flag = true;
                k++;
                i++;
                if (i == str.length())
                    break;
            }
            if (flag) {
                String db = new String(str1, i - k, k);
                double s = Double.parseDouble(db);
                optr.push(s);
                flag = false;
            }
            char ch = str1[i];
            char priority = getPriority(ch);
            switch (priority) {
                case '<':
                    opnd.push(ch);
                    break;
                case '>':
                    char c = opnd.pop();
                    double b = optr.pop();
                    double a = optr.pop();
                    double db = calu(c, a, b);
                    optr.push(db);
                    continue;
                case '=':
                    opnd.pop();
                    break;
                default:
                    throw new Exception("ERROR");
            }
            i++;
        }
    }

    /**
     * 比较运算符的优先级
     *
     * @param ch2 运算符
     * @return 优先级
     */
    private char getPriority(char ch2) {
        char ch1 = opnd.peek();
        if (ch1 == '+' || ch1 == '-') {
            if (ch2 == '*' || ch2 == '/' || ch2 == '(')
                return '<';
            return '>';
        }
        if (ch1 == '*' || ch1 == '/') {
            if (ch2 == '(')
                return '<';
            return '>';
        }
        if (ch1 == '(') {
            if (ch2 == '#')
                return 'E';
            if (ch2 == ')')
                return '=';
            return '<';
        }
        if (ch1 == ')') {
            if (ch2 == '(')
                return 'E';
            return '>';
        }
        if (ch1 == '#') {
            if (ch2 == '#')
                return '=';
            if (ch2 == ')')
                return 'E';
            return '<';
        }
        return 'E';
    }

    private double calu(char ch, double a, double b) {
        if (ch == '+') return a + b;
        if (ch == '-') return a - b;
        if (ch == '*') return a * b;
        else return a / b;
    }

    /**
     * 根据表达式计算值
     *
     * @param str 字符串形式的表达式
     * @return 计算表达式得到的值 若表达式出错或无法计算则返回"ERROR" type:String
     */
    public static String calculate(String str) {
        try {
            Calculator ca = new Calculator();
            ca.Init();
            if (str.charAt(0) == '-') {
                str = "0" + str;
            }
            str = str.replace('×', '*').replace("(-", "(0-").replace('÷', '/');
            ca.prase(str + '#');
            double sum = ca.optr.peek();
            if (sum - (int) sum == 0.0)
                return Integer.toString((int) sum);
            else return Double.toString(sum);
        } catch (Exception ex) {
            return "ERROR";
        }
    }
}
