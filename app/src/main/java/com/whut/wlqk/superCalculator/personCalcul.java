package com.whut.wlqk.superCalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class personCalcul extends Fragment {
    public personCalcul() {
        // Required empty public constructor
    }


    //@Override
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.personCalcul, container, false);
    }

}
