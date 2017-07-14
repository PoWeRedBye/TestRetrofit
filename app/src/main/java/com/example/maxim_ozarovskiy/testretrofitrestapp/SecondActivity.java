package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Maxim_Ozarovskiy on 13.07.2017.
 */

public class SecondActivity extends AppCompatActivity {

    public static final String bundleExample= "Example";

    private Example example;
    TextView cityName;
    TextView country;
    TextView temp;
    TextView pressure;
    TextView humidity;
    TextView temp_min;
    TextView temp_max;
    TextView speed;
    TextView deg;

    Button back;

    private String city;
    private String place;
    private Double temperature;
    private Integer press;
    private Integer humid;
    private Double tempMin;
    private Double tempMax;


    private Double tempCels;
    private Double tempMaxCels;
    private Double tempMinCels;
    private String deg1;
    private String speed1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        initListeners();
        getData();
        calcData();
        setData();
    }

    private void initListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setData() {
        //double не хочет заносить в текст вью, по этому делаю перевод в стрингу...
        String tempCelsiy = String.valueOf(tempCels);
        String tempMaxCelsiy = String.valueOf(tempMaxCels);
        String tempMinCelsiy = String.valueOf(tempMinCels);

        cityName.setText(city);
        country.setText(place);
        temp.setText(tempCelsiy);
        pressure.setText(String.valueOf(press));
        humidity.setText(String.valueOf(humid));
        temp_min.setText(tempMinCelsiy);
        temp_max.setText(tempMaxCelsiy);
        speed1 = example.getWind().getSpeed();
        if (!TextUtils.isEmpty(speed1)) {
            this.speed.setText(speed1);
        }

        deg1 = example.getWind().getDeg();
        if (!TextUtils.isEmpty(deg1)) {
            this.deg.setText(deg1);
        }
    }

    private void calcData() {
        //перевод из фаренгейтов в цельсий
        tempCels = (temperature - 32) / 1.8;
        tempMaxCels = (tempMax - 32) / 1.8;
        tempMinCels = (tempMin - 32) / 1.8;
    }

    private void getData() {
        example = getIntent().getParcelableExtra(bundleExample);
        city = example.getName();
        place = example.getSys().getCountry();
        temperature = example.getMain().getTemp();
        press = example.getMain().getPressure();
        humid = example.getMain().getHumidity();
        tempMin = example.getMain().getTempMin();
        tempMax = example.getMain().getTempMax();
    }

    private void initUi() {
        cityName = (TextView) findViewById(R.id.CityName);
        country = (TextView) findViewById(R.id.Country);
        temp = (TextView) findViewById(R.id.Temperature);
        pressure = (TextView) findViewById(R.id.pressure);
        humidity = (TextView) findViewById(R.id.humidity);
        temp_min = (TextView) findViewById(R.id.temp_min);
        temp_max = (TextView) findViewById(R.id.temp_max);
        speed = (TextView) findViewById(R.id.wind_speed);
        deg = (TextView) findViewById(R.id.wind_deg);
        back = (Button) findViewById(R.id.go_to_check);
    }
}
