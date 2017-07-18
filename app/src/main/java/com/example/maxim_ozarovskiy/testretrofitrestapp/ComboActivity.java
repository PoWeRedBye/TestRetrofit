package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.Example;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.ListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 16.07.2017.
 */

public class ComboActivity extends AppCompatActivity implements MyAdapter.ItemClickListener<ListModel> {

    TextView cityName;
    TextView currentTemp;
    TextView minTemp;
    TextView maxTemp;
    TextView humidity;
    TextView pressure;
    TextView windSpeed;
    TextView windDegree;
    TextView countryCode;
    ImageView weather_status_icon;

    private String city_name;
    private Double curr_temp;
    private Double min_temp;
    private Double max_temp;
    private Integer humid;
    private Double press;
    private Double wind_speed;
    private Double wind_degree;
    private String country_code;

    private double tempCels;
    private double tempMaxCels;
    private double tempMinCels;
    private String deg1;
    private String speed1;

    private double degree;
    private String direction;
    private String weatherImage;
    private int weather_image;

    public static final String sixteenDayBundleExample = "SixteenDayBundleExample";

    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private List<ListModel> list;
    private Example example;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout relativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combo_activity_1);

        initUI();
        getData();
        calcData();
        setData();
        list = example.getList();
        myAdapter = new MyAdapter(this, list.subList(1, 7),this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    private void setData() {

        int currTemp = (int) tempCels;
        int maximalTemp = (int) tempMaxCels;
        int minimalTemp = (int) tempMinCels;

        cityName.setText(city_name);
        countryCode.setText(country_code);
        currentTemp.setText(String.valueOf(currTemp) + " °C");
        minTemp.setText(String.valueOf(minimalTemp)+" °C");
        maxTemp.setText(String.valueOf(maximalTemp)+" °C");
        humidity.setText(String.valueOf(humid));
        pressure.setText(String.valueOf(press)+" hPa");
        windSpeed.setText(String.valueOf(wind_speed)+" m/s");
        getDirection();
        windDegree.setText(direction);
        weather_image = getIcon();
        Picasso.with(getApplicationContext()).load(weather_image).into(weather_status_icon);
    }

    private void initUI() {
        cityName = (TextView) findViewById(R.id.city_name_combo_1);
        currentTemp = (TextView) findViewById(R.id.current_temp_combo_1);
        minTemp = (TextView) findViewById(R.id.temp_min_text_combo_1);
        maxTemp = (TextView) findViewById(R.id.temp_max_text_combo_1);
        humidity = (TextView) findViewById(R.id.humidity_text_combo_1);
        pressure = (TextView) findViewById(R.id.pressure_text_combo_1);
        windSpeed = (TextView) findViewById(R.id.wind_speed_text_combo_1);
        windDegree = (TextView) findViewById(R.id.wind_degree_text_combo_1);
        countryCode = (TextView) findViewById(R.id.country_code_combo_1);

        weather_status_icon = (ImageView) findViewById(R.id.weather_status_combo_1);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_combo);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_combo);
    }

    private void getData() {
        if (getIntent() != null && getIntent().hasExtra(sixteenDayBundleExample)) {
            example = getIntent().getParcelableExtra(sixteenDayBundleExample);
        }
        city_name = example.getCity().getName();
        country_code = example.getCity().getCountry();
        curr_temp = example.getList().get(0).getTemp().getDay();
        min_temp = example.getList().get(0).getTemp().getMin();
        max_temp = example.getList().get(0).getTemp().getMax();
        humid = example.getList().get(0).getHumidity();
        press = example.getList().get(0).getPressure();
        wind_speed = example.getList().get(0).getSpeed();
        wind_degree = example.getList().get(0).getDeg();
        weatherImage = example.getList().get(0).getWeather().get(0).getIcon();
    }

    private void calcData() {
        tempCels = curr_temp - 273;
        tempMaxCels = max_temp - 273;
        tempMinCels = min_temp - 273;
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

    @Override
    public void ItemClick(ListModel v, int position) {
        Intent intent = new Intent(getApplicationContext(), Details.class);
        intent.putExtra("ListModel", v);
        startActivity(intent);
    }
}
