package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class CityAdd extends AppCompatActivity {

    EditText cityAdd;
    Button citySave;

    private String cityName;

    private CityCheckDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_add);

        initUI();

        dbAdapter = new CityCheckDBAdapter(this);
        dbAdapter.open();

        citySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = cityAdd.getText().toString();
                if (isValidData()) {
                    dbAdapter.createCityField(cityName);
                    Toast.makeText(getApplicationContext(), "You added a WeatherByCity", Toast.LENGTH_SHORT).show();
                    returnHome();
                } else {
                    Toast.makeText(getApplicationContext(), "WeatherByCity is not added!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isValidData() {
        if (TextUtils.isEmpty(cityName)) {
            Toast.makeText(getApplicationContext(), "Please enter the WeatherByCity!!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void initUI() {
        setTitle("WeatherByCity Add");
        cityAdd = (EditText) findViewById(R.id.city_add_text);
        citySave = (Button) findViewById(R.id.city_add_button);
    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), CityCheckListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}
