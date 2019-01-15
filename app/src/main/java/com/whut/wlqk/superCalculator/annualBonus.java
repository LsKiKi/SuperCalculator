package com.whut.wlqk.superCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class annualBonus extends Fragment implements View.OnClickListener {
    EditText annualbonus;
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

    private void init(View view) {
        annualbonus = view.findViewById(R.id.total_annual);
        btn = view.findViewById(R.id.btn_start_compute);
        btn.setOnClickListener(this);
        annualbonus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();

                /*
                 * 0开始的整数
                 */
                if (str.startsWith("0") && !str.equals("0")) {
                    s.delete(0, 1);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent(getActivity(), AnnualActivity.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("annualbonus", Double.parseDouble(annualbonus.getText().toString()));
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(v.getContext(), getString(R.string.toast_error), Toast.LENGTH_SHORT).show();
        }
    }
}
