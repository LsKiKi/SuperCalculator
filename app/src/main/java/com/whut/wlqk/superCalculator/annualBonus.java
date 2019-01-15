package com.whut.wlqk.superCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class annualBonus  extends Fragment implements View.OnClickListener{
    EditText annualbonus;
    double annualbonus_;
    Button btn;
    public annualBonus() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.annualbonus, container, false);
        init(view);
        return view;
    }
    private void init(View view){
        annualbonus = view.findViewById(R.id.business_total_loan2);
        btn = view.findViewById(R.id.btn_start_compute);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        annualbonus_ = Double.parseDouble(annualbonus.getText().toString());
        Intent intent = new Intent(getActivity(),AnnualActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("annualbonus",annualbonus_);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
