package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.whut.wlqk.superCalculator.utils.SpinnerData;
import com.whut.wlqk.superCalculator.utils.loan.AverageCaptial;
import com.whut.wlqk.superCalculator.utils.loan.AverageCaptialPlusInterest;
import com.whut.wlqk.superCalculator.utils.loan.Loan;

import java.util.ArrayList;
import java.util.List;


public class fund extends Fragment implements View.OnClickListener{
    public fund() {
        // Required empty public constructor
    }
    private Spinner sp1;
    private Spinner sp2;
    private Spinner sp3;
    EditText editText;
    int id1,id3,years;
    Button admit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fund, container, false);
        init_all(view);
        return view;
    }
    private void init_all(View view) {
        editText=view.findViewById(R.id.money);
        admit = view.findViewById(R.id.admit);

        sp1 = view.findViewById(R.id.sp1);
        sp2 = view.findViewById(R.id.sp2);
        sp3 = view.findViewById(R.id.sp3);
        List<SpinnerData> lst1 = new ArrayList<SpinnerData>();
        List<SpinnerData> lst3 = new ArrayList<SpinnerData>();
        SpinnerData c1 = new SpinnerData(1, "等额本息(每月等额还款)");
        lst1.add(c1);
        SpinnerData c2 = new SpinnerData(2, "等额本金(每月递减还款)");
        lst1.add(c2);
        c1 = new SpinnerData(1, "基准利率(3.25%)");
        lst3.add(c1);
        c2 = new SpinnerData(2, "1.1倍(3.575%)");
        lst3.add(c2);
        SpinnerData c3 = new SpinnerData(3, "1.2倍(3.9%)");
        lst3.add(c3);
        ArrayAdapter<SpinnerData> Adapter1 = new ArrayAdapter<SpinnerData>(view.getContext(), android.R.layout.simple_spinner_item, lst1);
        ArrayAdapter<SpinnerData> Adapter3 = new ArrayAdapter<SpinnerData>(view.getContext(), android.R.layout.simple_spinner_item, lst3);

        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(Adapter1);
        sp3.setAdapter(Adapter3);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * ids是刚刚新建的list里面的ID
                 */
                id1 = ((SpinnerData) sp1.getSelectedItem()).GetID();
                System.out.println(id1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                id1 =1;
            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * ids是刚刚新建的list里面的ID
                 */
                years = Integer.parseInt(sp2.getSelectedItem().toString());
                System.out.println(years);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                years =1;
            }
        });
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * id是刚刚新建的list里面的ID
                 */
                id3 = ((SpinnerData) sp3.getSelectedItem()).GetID();
                System.out.println(id3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                id3 =1;
            }
        });
        admit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Double money=Double.parseDouble(editText.getText().toString());
        System.out.println(money);
        switch (id1){
            case 1:
                Loan loan1= new AverageCaptialPlusInterest(money,years,0.0325);
                System.out.println(((AverageCaptialPlusInterest) loan1).getTotalMoney());
                break;
            case 2:
                Loan loan2= new AverageCaptial(money,years,0.0325);
                System.out.println(((AverageCaptial) loan2).getTotalMoney());
                break;
        }
    }
//        /*spinnerID为R.id.xxx*/
////取得value
//        public int getSpinnerSelid(View view,int spinnerID){
//            Spinner sp = view.findViewById(spinnerID);
//            return ((SpinnerData)sp.getSelectedItem()).GetID();
//        }
////取得text
//        public String getSpinnerSelvalue(View view,Integer spinnerID){
//            Spinner sp = view.findViewById(spinnerID);
//            return ((SpinnerData)sp.getSelectedItem()).GetValue();
//        }



}
