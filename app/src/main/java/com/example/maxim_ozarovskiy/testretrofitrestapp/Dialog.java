package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityCheck;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class Dialog extends DialogFragment implements View.OnClickListener {

    private int id;
    private CityCheck cityCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().setTitle("Delete City");
        View v = inflater.inflate(R.layout.dialog, null);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);

        return v;



    }

    @Override
    public void onClick(View v) {

    }
}
