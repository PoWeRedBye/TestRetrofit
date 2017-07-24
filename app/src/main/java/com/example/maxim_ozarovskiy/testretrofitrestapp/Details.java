package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.ListModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 18.07.2017.
 */

public class Details extends AppCompatActivity {

    TextView data_details;
    TextView temp_day_details;
    TextView temp_night_details;
    TextView directon_details;
    TextView humidity_details;
    TextView pressure_details;
    TextView weather_status_text_details;
    ImageView weather_status_icon_details;

    private long date;
    private Double tempDay;
    private Double tempNight;
    private Integer humid;
    private Double press;
    private Double wind_degree;
    private String status;

    private double tempDayCels;
    private double tempNightCels;

    private double degree;
    private String direction;
    private String weatherImage;
    private int weather_image;
    private String formattedDate;

    private ListModel list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list);
        initUI();
        getData();
        calcData();
        setData();

    }

    private void initUI() {
        data_details = (TextView) findViewById(R.id.date_text_detail);
        temp_day_details = (TextView) findViewById(R.id.temp_day_text_detail);
        temp_night_details = (TextView) findViewById(R.id.temp_night_text_detail);
        directon_details = (TextView) findViewById(R.id.weather_direction_text);
        humidity_details = (TextView) findViewById(R.id.humidity_text_detail);
        pressure_details = (TextView) findViewById(R.id.pressure_text_detail);
        weather_status_text_details = (TextView) findViewById(R.id.weather_status_description);
        weather_status_icon_details = (ImageView) findViewById(R.id.weather_status_detail);
    }

    private void setData() {

        int dayTemp = (int) tempDayCels;
        int nightTemp = (int) tempNightCels;

        data_details.setText(formattedDate);
        temp_day_details.setText(String.valueOf(dayTemp) + "°C");
        temp_night_details.setText(String.valueOf(nightTemp) + "°C");
        humidity_details.setText(String.valueOf(humid));
        pressure_details.setText(String.valueOf(press) + " hPa");
        getDirection();
        directon_details.setText(direction);
        weather_status_text_details.setText(status);
        weather_image = getIcon();
        Picasso.with(getApplicationContext()).load(weather_image).into(weather_status_icon_details);
    }

    private void calcData() {
        tempDayCels = tempDay - 273;
        tempNightCels = tempNight - 273;
    }

    private void getData() {
        list = getIntent().getParcelableExtra("ListModel");

        date = list.getDt();
        Date time = new Date();
        time.setTime(date * 1000);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");
        formattedDate = format.format(time);
        tempDay = list.getTemp().getDay();
        tempNight = list.getTemp().getNight();
        humid = list.getHumidity();
        press = list.getPressure();
        wind_degree = list.getDeg();
        weatherImage = list.getWeather().get(0).getIcon();
        status = list.getWeather().get(0).getMain();
    }

    private void getDirection() {
        direction = "Unknown";
        if (wind_degree >= 337.5 || wind_degree < 22.5) {
            direction = "North";
        } else if (wind_degree >= 22.5 && wind_degree < 67.5) {
            direction = "North-East";
        } else if (wind_degree >= 67.5 && wind_degree < 112.5) {
            direction = "East";
        } else if (wind_degree >= 112.5 && wind_degree < 157.5) {
            direction = "South-East";
        } else if (wind_degree >= 157.5 && wind_degree < 202.5) {
            direction = "South";
        } else if (wind_degree >= 202.5 && wind_degree < 247.5) {
            direction = "South-West";
        } else if (wind_degree >= 247.5 && wind_degree < 292.5) {
            direction = "West";
        } else if (wind_degree >= 292.5 || wind_degree < 22.5) {
            direction = "North-West";
        }
    }

    public int getIcon() {
        switch (weatherImage) {
            case "01d":
                return R.drawable.one_day;
            case "01n":
                return R.drawable.one_night;
            case "02d":
                return R.drawable.two_day;
            case "02n":
                return R.drawable.two_night;
            case "03d":
                return R.drawable.three_day;
            case "03n":
                return R.drawable.three_night;
            case "04d":
                return R.drawable.four;
            case "04n":
                return R.drawable.four;
            case "09d":
                return R.drawable.five_day_night;
            case "09n":
                return R.drawable.five_day_night;
            case "10d":
                return R.drawable.six_day;
            case "10n":
                return R.drawable.six_night;
            case "11d":
                return R.drawable.seven_day_night;
            case "11n":
                return R.drawable.seven_day_night;
            case "13d":
                return R.drawable.eleven_day;
            case "13n":
                return R.drawable.eleven_night;
            case "50d":
                return R.drawable.fog_day;
            case "50n":
                return R.drawable.fog_night;
        }
        return weather_image;
    }
}
