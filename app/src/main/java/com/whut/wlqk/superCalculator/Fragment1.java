package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.whut.wlqk.superCalculator.utils.calculator.Calculator;


public class Fragment1 extends Fragment implements View.OnClickListener {
    //显示框
    EditText input;
    //按钮对象
    Button clear, delete, charAdd, charDiv, charMul, charSub, point, equal;
    Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    //本次计算结束标志
    boolean compute_flag = false;
    //最后一次计算的结果
    String last_result = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);
        init_btn(view);
        return view;
    }

    /**
     * 按钮绑定和事件监听
     *
     * @param view view对象
     */
    private void init_btn(View view) {
        /*
         * 绑定按钮
         */
        clear = view.findViewById(R.id.btn_AC);
        delete = view.findViewById(R.id.btn_Del);
        charAdd = view.findViewById(R.id.btn_Add);
        charSub = view.findViewById(R.id.btn_Sub);
        charMul = view.findViewById(R.id.btn_Mul);
        charDiv = view.findViewById(R.id.btn_Div);
        point = view.findViewById(R.id.btn_Point);
        equal = view.findViewById(R.id.btn_Equ);
        num0 = view.findViewById(R.id.btn_0);
        num1 = view.findViewById(R.id.btn_1);
        num2 = view.findViewById(R.id.btn_2);
        num3 = view.findViewById(R.id.btn_3);
        num4 = view.findViewById(R.id.btn_4);
        num5 = view.findViewById(R.id.btn_5);
        num6 = view.findViewById(R.id.btn_6);
        num7 = view.findViewById(R.id.btn_7);
        num8 = view.findViewById(R.id.btn_8);
        num9 = view.findViewById(R.id.btn_9);
        input = view.findViewById(R.id.input);
        input.setEnabled(false);  // 设置输入框不可编辑

        /*
         * 设置btn的click监听
         */
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        charAdd.setOnClickListener(this);
        charSub.setOnClickListener(this);
        charMul.setOnClickListener(this);
        charDiv.setOnClickListener(this);
        point.setOnClickListener(this);
        equal.setOnClickListener(this);
    }


    public void onClick(View view) {
        String str = input.getText().toString();
        // 若按键发生在一次计算结束之后，清空文本框
        if (compute_flag)
            str = "";
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                compute_flag = false;  //输入数字，本次计算尚未结束
                input.setText(str.concat(((Button) view).getText().toString()));
                break;
            case R.id.btn_Point:
                //若文本框非空，且前一个字符非运算符，且前一个数种不含小数点
                if (str.isEmpty() || str.endsWith("+") || str.endsWith("-") || str.endsWith("×") || str.endsWith("÷"))
                    break;
                for (int i = str.length() - 1; i >= 0; i--) {
                    // 找到小数点
                    if (str.charAt(i) == '.') {
                        break;
                    }
                    // 找到非数（这个数字结束） 或最前端
                    if (i == 0 || str.charAt(i) > '9' || '0' > str.charAt(i)) {
                        input.setText(str.concat("."));
                        break;
                    }
                }
                break;
            case R.id.btn_AC:
                //清空按钮，设置文本框为空
                compute_flag = false;
                input.setText("");
                break;
            case R.id.btn_Add:
            case R.id.btn_Sub:
            case R.id.btn_Mul:
            case R.id.btn_Div:
                if (str.isEmpty()) {
                    //若文本为空，且上次计算刚刚结束，利用结果进行计算
                    if (compute_flag) {
                        //上次结算结果非法，输入无效
                        if (last_result.equals("ERROR"))
                            break;
                            //上次结果合法，保留
                        else {
                            str = last_result;
                            compute_flag = false;
                        }
                    } else
                        break;
                }
                //若前一个字符为运算符或小数点，修改运算符
                if (str.endsWith("+") || str.endsWith("-") || str.endsWith("×") || str.endsWith("÷") || str.endsWith(".")) {
                    str = str.substring(0, str.length() - 1);
                }
                input.setText(str.concat(((Button) view).getText().toString()));
                break;
            case R.id.btn_Del:
                //上次计算刚结束，清除所有
                if (compute_flag) {
                    input.setText("");
                    compute_flag = false;
                    break;
                }
                if (!str.isEmpty())
                    input.setText(str.substring(0, str.length() - 1));
                break;
            case R.id.btn_Equ:
                // 刚结束计算，无视
                if (compute_flag)
                    break;
                //输出计算结果
                String result = Calculator.calculate(str); //调用计算函数
                input.setText(str.concat('\n' + result)); //显示结果
                last_result = result; //保存结果
                compute_flag = true;  //设置标志位
            default:
        }
    }
}
