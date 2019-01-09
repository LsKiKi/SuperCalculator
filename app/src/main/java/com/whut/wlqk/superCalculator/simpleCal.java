package com.whut.wlqk.superCalculator;

public class simpleCal {

    /*public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        //显示框
        EditText input;
        //按钮对象
        Button clear, delete, charAdd, charDiv, charMul, charSub, point, equal;
        Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
        boolean clr_flag;    //判断et中是否清空
        boolean point_flag = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //按钮对象
            clear = (Button) findViewById(R.id.btn_AC);
            delete = (Button) findViewById(R.id.btn_Del);
            charAdd = (Button) findViewById(R.id.btn_add);
            charSub = (Button) findViewById(R.id.btn_sub);
            charMul = (Button) findViewById(R.id.btn_mul);
            charDiv = (Button) findViewById(R.id.btn_div);
            point = (Button) findViewById(R.id.btn_point);
            equal = (Button) findViewById(R.id.btn_equ);

            num0 = (Button) findViewById(R.id.btn_0);
            num1 = (Button) findViewById(R.id.btn_1);
            num2 = (Button) findViewById(R.id.btn_2);
            num3 = (Button) findViewById(R.id.btn_3);
            num4 = (Button) findViewById(R.id.btn_4);
            num5 = (Button) findViewById(R.id.btn_5);
            num6 = (Button) findViewById(R.id.btn_6);
            num7 = (Button) findViewById(R.id.btn_7);
            num8 = (Button) findViewById(R.id.btn_8);
            num9 = (Button) findViewById(R.id.btn_9);
            input = (EditText) findViewById(R.id.input);

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


        public void simpleCal(){

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
                    input.setText(str + ((Button) view).getText());
                    break;
                case R.id.btn_point:
                    if (point_flag) break;
                    point_flag = true;
                    if (clr_flag) {
                        clr_flag = false;
                        str = "";
                        input.setText("");
                    }
                    input.setText(str + ((Button) view).getText());
                    break;
                case R.id.btn_AC:
                    if (clr_flag) {
                        clr_flag = false;
                    }
                    str = "";
                    input.setText("");
                    point_flag = false;
                    break;
                case R.id.btn_add:
                case R.id.btn_sub:
                case R.id.btn_mul:
                case R.id.btn_div:
                    if (clr_flag) {
                        clr_flag = false;
                        str = "";
                        input.setText("");
                    }
                    point_flag = false;
                    if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")) {
                        str=str.substring(0,str.indexOf(" "));
                    }
                    input.setText(str + ((Button) view).getText());
                    break;
                case R.id.btn_Del:
                    if (clr_flag) {
                        clr_flag = false;
                        str = "";
                        input.setText("");
                    } else if (str != null && !str.equals("")) {
                        if (str.charAt(str.length() - 1) == '.') {
                            point_flag = false;
                        } else if (str.charAt(str.length() - 1) < '9' && '0' < str.charAt(str.length() - 1)) {
                            if (str.length()>2 && str.charAt(str.length() - 1) > '9' && '0' > str.charAt(str.length() - 1))
                            {
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
                case R.id.btn_equ:
                    getResult();
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
                    input.setText(res + "");
                } else {
                    input.setText(cnt + "");
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
                    input.setText(res + "");
                } else {
                    input.setText(cnt + "");
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
                    input.setText(res + "");
                } else {
                    input.setText(cnt + "");
                }
            } else {
                input.setText("");
            }
        }
    }*/
}
