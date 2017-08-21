package com.example.maxim_ozarovskiy.testretrofitrestapp.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxim_ozarovskiy.testretrofitrestapp.Adapters.WeatherInfoActivityAdapter;
import com.example.maxim_ozarovskiy.testretrofitrestapp.Network.RESTClient;
import com.example.maxim_ozarovskiy.testretrofitrestapp.R;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityModel;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.WeatherByCityModel;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.WeatherForDayModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maxim_Ozarovskiy on 16.07.2017.
 */

public class WeatherInfoActivity extends AppCompatActivity implements WeatherInfoActivityAdapter.ItemClickListener<WeatherForDayModel> {

    TextView cityName;
    TextView currentTemp;
    TextView humidity;
    TextView pressure;
    TextView windSpeed;
    ImageView windDegree;
    TextView countryCode;
    ImageView weatherStatusIcon;
    TextView weatherDescription;
    TextView dateInfo;

    private String city_name;
    private Double curr_temp;
    private Integer humid;
    private Double press;
    private Double wind_speed;
    private Double wind_degree;
    private String country_code;
    private String weather_description;

    private long date;
    private double tempCels;
    private double pressure1;

    private String weatherImage;
    private int weather_image;
    private int wind_direction;
    private String formattedDate;

    private String q;
    private String appid = "2fa8c9a46e8ac6ad4bcc4f4fc48e5865";
    private String cnt = "7";
    private String units = "metric";
    private String lang = "ru";

    private CityModel cityModel;

    private WeatherInfoActivityAdapter weatherInfoActivityAdapter;
    private RecyclerView recyclerView;
    private List<WeatherForDayModel> weatherForDayList;
    private WeatherByCityModel weatherByCityModel;
    private LinearLayoutManager mLayoutManager;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initUI();
        if (isValidCity()) {
            getSevenDayWeather();
        }
    }

    private void setData() {

        int currTemp = (int) tempCels;
        int pressureText = (int) pressure1;

        cityName.setText(city_name);
        countryCode.setText(country_code);
        currentTemp.setText(String.valueOf(currTemp) + "°C");
        humidity.setText(String.valueOf(humid) + "%");
        pressure.setText(String.valueOf(pressureText));
        windSpeed.setText(String.valueOf(wind_speed) + " М/С");
        weatherDescription.setText(weather_description);
        dateInfo.setText(formattedDate);
        wind_direction = getDirection();
        Picasso.with(getApplicationContext()).load(wind_direction).into(windDegree);
        weather_image = getIcon();
        Picasso.with(getApplicationContext()).load(weather_image).into(weatherStatusIcon);
    }

    private void initUI() {

        cityName = (TextView) findViewById(R.id.weather_info_activity_city_name_text);
        currentTemp = (TextView) findViewById(R.id.weather_info_activity_current_temperature);
        humidity = (TextView) findViewById(R.id.weather_info_activity_humidity_text);
        pressure = (TextView) findViewById(R.id.weather_info_activity_pressure_text);
        windSpeed = (TextView) findViewById(R.id.weather_info_activity_wind_speed_text);
        dateInfo = (TextView) findViewById(R.id.weather_info_activity_date_text);
        countryCode = (TextView) findViewById(R.id.weather_info_activity_country_code_text);
        weatherDescription = (TextView) findViewById(R.id.weather_info_activity_weather_status_description);

        windDegree = (ImageView) findViewById(R.id.weather_info_activity_wind_direction_icon);
        weatherStatusIcon = (ImageView) findViewById(R.id.weather_info_activity_status_icon);

        linearLayout = (LinearLayout) findViewById(R.id.weather_info_activity_linear_layout);
        recyclerView = (RecyclerView) findViewById(R.id.weather_info_activity_recycler_view);
        relativeLayout = (RelativeLayout) findViewById(R.id.weather_info_activity_relative_layout);
    }

    private void getData() {

        date = weatherByCityModel.getWeatherForDayModelList().get(0).getDt();
        Date time = new Date();
        time.setTime(date * 1000);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");
        formattedDate = format.format(time);

        city_name = weatherByCityModel.getCityInformationModel().getName();
        country_code = weatherByCityModel.getCityInformationModel().getCountry();
        curr_temp = weatherByCityModel.getWeatherForDayModelList().get(0).getTemperatureModel().getDay();
        humid = weatherByCityModel.getWeatherForDayModelList().get(0).getHumidity();
        press = weatherByCityModel.getWeatherForDayModelList().get(0).getPressure();
        wind_speed = weatherByCityModel.getWeatherForDayModelList().get(0).getSpeed();
        wind_degree = weatherByCityModel.getWeatherForDayModelList().get(0).getDeg();
        weatherImage = weatherByCityModel.getWeatherForDayModelList().get(0).getWeatherDescriptionModel().get(0).getIcon();
        weather_description = weatherByCityModel.getWeatherForDayModelList().get(0).getWeatherDescriptionModel().get(0).getDescription();
    }

    private int getDirection() {
        if (wind_degree >= 337.5 || wind_degree < 22.5) {
            wind_direction = R.drawable.north;
        } else if (wind_degree >= 22.5 && wind_degree < 67.5) {
            wind_direction = R.drawable.north_east;
        } else if (wind_degree >= 67.5 && wind_degree < 112.5) {
            wind_direction = R.drawable.east;
        } else if (wind_degree >= 112.5 && wind_degree < 157.5) {
            wind_direction = R.drawable.south_east;
        } else if (wind_degree >= 157.5 && wind_degree < 202.5) {
            wind_direction = R.drawable.south;
        } else if (wind_degree >= 202.5 && wind_degree < 247.5) {
            wind_direction = R.drawable.south_west;
        } else if (wind_degree >= 247.5 && wind_degree < 292.5) {
            wind_direction = R.drawable.west;
        } else if (wind_degree >= 292.5 || wind_degree < 22.5) {
            wind_direction = R.drawable.north_west;
        }
        return wind_direction;
    }

    private void calcData() {
        pressure1 = press / 1.333224;
        tempCels = curr_temp;
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
    public void ItemClick(WeatherForDayModel v, int position) {

        date = weatherByCityModel.getWeatherForDayModelList().get(position).getDt();
        Date time = new Date();
        time.setTime(date * 1000);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");
        formattedDate = format.format(time);

        city_name = weatherByCityModel.getCityInformationModel().getName();
        country_code = weatherByCityModel.getCityInformationModel().getCountry();
        curr_temp = weatherByCityModel.getWeatherForDayModelList().get(position).getTemperatureModel().getDay();
        humid = weatherByCityModel.getWeatherForDayModelList().get(position).getHumidity();
        press = weatherByCityModel.getWeatherForDayModelList().get(position).getPressure();
        wind_speed = weatherByCityModel.getWeatherForDayModelList().get(position).getSpeed();
        wind_degree = weatherByCityModel.getWeatherForDayModelList().get(position).getDeg();
        weatherImage = weatherByCityModel.getWeatherForDayModelList().get(position).getWeatherDescriptionModel().get(0).getIcon();
        weather_description = weatherByCityModel.getWeatherForDayModelList().get(position).getWeatherDescriptionModel().get(0).getDescription();

        calcData();
        setData();

    }

    private void getSevenDayWeather() {
        RESTClient.getInstance().getSevenDayWeatherExample().getWeatherExample(q, appid, cnt, units, lang).enqueue(new Callback<WeatherByCityModel>() {

            @Override
            public void onResponse(@NonNull Call<WeatherByCityModel> call, @NonNull Response<WeatherByCityModel> response) {
                if (response.isSuccessful()) {
                    weatherByCityModel = response.body();
                    updateUI();
                } else {
                    Toast.makeText(WeatherInfoActivity.this, R.string.sorry_bad_city, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherByCityModel> call, @NonNull Throwable t) {
                Toast.makeText(WeatherInfoActivity.this, R.string.no_inet, Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void updateUI() {
        setTitle(weatherByCityModel.getCityInformationModel().getName());
        getData();
        calcData();
        setData();
        weatherForDayList = weatherByCityModel.getWeatherForDayModelList();
        weatherInfoActivityAdapter = new WeatherInfoActivityAdapter(this, weatherForDayList, this);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(weatherInfoActivityAdapter);
        weatherInfoActivityAdapter.notifyDataSetChanged();

    }

    private boolean getCityName() {
        cityModel = getIntent().getParcelableExtra("CityModel");

        String s = cityModel.getCityName();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(getApplicationContext(), R.string.sorry_bad_city, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            q = s;
            return true;
        }
    }

    private boolean isValidCity() {
        if (getCityName()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
