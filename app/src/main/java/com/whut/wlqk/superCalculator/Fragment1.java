package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Fragment1 extends Fragment implements View.OnClickListener {
    //显示框
    EditText input;
    //按钮对象
    Button clear, delete, charAdd, charDiv, charMul, charSub, point, equal;
    Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    boolean clr_flag;    //判断et中是否清空
    boolean point_flag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);
        initData(view);
        return view;
    }

    private void initData(View view) {
        //按钮对象
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
        input = (EditText) view.findViewById(R.id.input);

        //设置按钮的点击事件
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
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    input.setText("");
                }
                input.setText(str.concat(((Button) view).getText().toString()));
                break;
            case R.id.btn_Point:
                if (point_flag || str.isEmpty() || str.endsWith("+") || str.endsWith("-") || str.endsWith("×") || str.endsWith("÷"))
                    break;
                point_flag = true;
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    input.setText("");
                }
                input.setText(str.concat(((Button) view).getText().toString()));
                break;
            case R.id.btn_AC:
                if (clr_flag) {
                    clr_flag = false;
                }
                str = "";
                input.setText("");
                point_flag = false;
                break;
            case R.id.btn_Add:
            case R.id.btn_Sub:
            case R.id.btn_Mul:
            case R.id.btn_Div:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    input.setText("");
                }
                point_flag = false;
                if (str.endsWith("+") || str.endsWith("-") || str.endsWith("×") || str.endsWith("÷")) {
                    str = str.substring(0, str.length() - 1);
                }
                input.setText(str.concat(((Button) view).getText().toString()));
                break;
            case R.id.btn_Del:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    input.setText("");
                } else if (!str.isEmpty()) {
                    if (str.charAt(str.length() - 1) == '.') {
                        point_flag = false;
                    } else if (str.charAt(str.length() - 1) < '9' && '0' < str.charAt(str.length() - 1)) {
                        if (str.length() > 2 && str.charAt(str.length() - 1) > '9' && '0' > str.charAt(str.length() - 1)) {
                            point_flag = false;
                            break;
                        }
                    } else {
                        for (int i = str.length() - 2; i >= 0; i--) {
                            if (str.charAt(i) == '.') {
                                point_flag = true;
                                break;
                            }
                            if (str.charAt(i) != '.' && str.charAt(str.length() - 1) > '9' && '0' > str.charAt(str.length() - 1)) {
                                point_flag = false;
                                break;
                            }
                        }
                    }
                    input.setText(str.substring(0, str.length() - 1));
                }

                break;
            case R.id.btn_Equ:
//                getResult();
            default:
        }
    }

    private void getResult() {
        String exp = input.getText().toString();
        if (exp == null || exp.equals("")) return;
        //因为没有运算符所以不用运算
        if (!exp.contains(" ")) {
            return;
        }
        if (clr_flag) {
            clr_flag = false;
            return;
        }
        clr_flag = true;
        //截取运算符前面的字符串
        String s1 = exp.substring(0, exp.indexOf(" "));
        //截取的运算符
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        //截取运算符后面的字符串
        String s2 = exp.substring(exp.indexOf(" ") + 3);
        double cnt = 0;
        if (!s1.equals("") && !s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                cnt = d1 + d2;
            }
            if (op.equals("-")) {
                cnt = d1 - d2;
            }
            if (op.equals("×")) {
                cnt = d1 * d2;
            }
            if (op.equals("÷")) {
                if (d2 == 0) cnt = 0;
                else cnt = d1 / d2;
            }
            if (!s1.contains(".") && !s2.contains(".") && !op.equals("÷")) {
                int res = (int) cnt;
                input.setText(res);
            } else {
                input.setText(String.valueOf(cnt));
            }
        }
        //s1不为空但s2为空
        else if (!s1.equals("") && s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            if (op.equals("+")) {
                cnt = d1;
            }
            if (op.equals("-")) {
                cnt = d1;
            }
            if (op.equals("×")) {
                cnt = 0;
            }
            if (op.equals("÷")) {
                cnt = 0;
            }
            if (!s1.contains(".")) {
                int res = (int) cnt;
                input.setText(res);
            } else {
                input.setText(String.valueOf(cnt));
            }
        }
        //s1是空但s2不是空
        else if (s1.equals("") && !s2.equals("")) {
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                cnt = d2;
            }
            if (op.equals("-")) {
                cnt = 0 - d2;
            }
            if (op.equals("×")) {
                cnt = 0;
            }
            if (op.equals("÷")) {
                cnt = 0;
            }
            if (!s2.contains(".")) {
                int res = (int) cnt;
                input.setText(res);
            } else {
                input.setText(String.valueOf(cnt));
            }
        } else {
            input.setText("");
        }
    }

}
