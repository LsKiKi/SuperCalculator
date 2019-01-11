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


public class fund extends Fragment implements View.OnClickListener {
    public fund() {
        // Required empty public constructor
    }

    private Spinner sp1;
    private Spinner sp2;
    private Spinner sp3;
    EditText editText;
    int id1, id3, years;
    Button admit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fund, container, false);
        init_all(view);
        return view;
    }

    private void init_all(View view) {
        editText = view.findViewById(R.id.money);
        admit = view.findViewById(R.id.admit);

        final String[] back_way_mItems = getResources().getStringArray(R.array.back_ways);
        final String[] year_num_mItems = getResources().getStringArray(R.array.year);
        final String[] interest_rate_mItems = getResources().getStringArray(R.array.interest_rate);

        sp1 = view.findViewById(R.id.sp1);
        sp2 = view.findViewById(R.id.sp2);
        sp3 = view.findViewById(R.id.sp3);
        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(view.getContext(), R.layout.item_spinner_select, back_way_mItems);
        ArrayAdapter<String> Adapter2 = new ArrayAdapter<String>(view.getContext(), R.layout.item_spinner_select, year_num_mItems);
        ArrayAdapter<String> Adapter3 = new ArrayAdapter<String>(view.getContext(), R.layout.item_spinner_select, interest_rate_mItems);

        Adapter1.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        Adapter2.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        Adapter3.setDropDownViewResource(R.layout.item_dialog_spinner_select);
        sp1.setAdapter(Adapter1);
        sp2.setAdapter(Adapter2);
        sp3.setAdapter(Adapter3);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * ids是刚刚新建的list里面的ID
                 */
                id1 = position + 1;
                System.out.println(id1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                id1 = 1;
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
                years = 1;
            }
        });
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*
                 * id是刚刚新建的list里面的ID
                 */
                id3 = position + 1;
                System.out.println(id3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                id3 = 1;
            }
        });
        admit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Double money = Double.parseDouble(editText.getText().toString());
        System.out.println(money);
        switch (id1) {
            case 1:
                Loan loan1 = new AverageCaptialPlusInterest(money, years, 0.0325);
                System.out.println(((AverageCaptialPlusInterest) loan1).getTotalMoney());
                break;
            case 2:
                Loan loan2 = new AverageCaptial(money, years, 0.0325);
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
