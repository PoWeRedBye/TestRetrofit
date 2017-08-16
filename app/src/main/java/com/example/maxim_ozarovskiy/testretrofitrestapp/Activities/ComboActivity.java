package com.example.maxim_ozarovskiy.testretrofitrestapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxim_ozarovskiy.testretrofitrestapp.Adapters.ComboAdapter;
import com.example.maxim_ozarovskiy.testretrofitrestapp.MainActivity;
import com.example.maxim_ozarovskiy.testretrofitrestapp.Network.RESTClient;
import com.example.maxim_ozarovskiy.testretrofitrestapp.R;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityCheck;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.WeatherByCityModel;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.WeatherForDayModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maxim_Ozarovskiy on 16.07.2017.
 */

public class ComboActivity extends AppCompatActivity implements ComboAdapter.ItemClickListener<WeatherForDayModel> {

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

    private String q;
    private String appid = "2fa8c9a46e8ac6ad4bcc4f4fc48e5865";
    private String cnt = "7";

    private CityCheck cityCheck;

    public static final String sixteenDayBundleExample = "SixteenDayBundleExample";

    private ComboAdapter comboAdapter;
    private RecyclerView recyclerView;
    private List<WeatherForDayModel> weatherForDayList;
    private WeatherByCityModel weatherByCityModel;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout relativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combo_activity);

        isValidCity();
        getSevenDayWeather();

        initUI();
        getData();
        calcData();
        setData();
        weatherForDayList = weatherByCityModel.getWeatherForDayModelList();
        comboAdapter = new ComboAdapter(this, weatherForDayList.subList(1, 7),this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(comboAdapter);
        comboAdapter.notifyDataSetChanged();
    }

    private void setData() {

        int currTemp = (int) tempCels;
        int maximalTemp = (int) tempMaxCels;
        int minimalTemp = (int) tempMinCels;

        cityName.setText(city_name);
        countryCode.setText(country_code);
        currentTemp.setText(String.valueOf(currTemp) + "°C");
        minTemp.setText(String.valueOf(minimalTemp)+"°C");
        maxTemp.setText(String.valueOf(maximalTemp)+"°C");
        humidity.setText(String.valueOf(humid));
        pressure.setText(String.valueOf(press)+" hPa");
        windSpeed.setText(String.valueOf(wind_speed)+" m/s");
        getDirection();
        windDegree.setText(direction);
        weather_image = getIcon();
        Picasso.with(getApplicationContext()).load(weather_image).into(weather_status_icon);
    }

    private void initUI() {
        setTitle("My WeatherDescriptionModel App");
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
        /*if (getIntent() != null && getIntent().hasExtra(sixteenDayBundleExample)) {
            weatherByCityModel = getIntent().getParcelableExtra(sixteenDayBundleExample);
        }*/
        city_name = weatherByCityModel.getWeatherByCity().getName();
        country_code = weatherByCityModel.getWeatherByCity().getCountry();
        curr_temp = weatherByCityModel.getWeatherForDayModelList().get(0).getTemperatureModel().getDay();
        min_temp = weatherByCityModel.getWeatherForDayModelList().get(0).getTemperatureModel().getMin();
        max_temp = weatherByCityModel.getWeatherForDayModelList().get(0).getTemperatureModel().getMax();
        humid = weatherByCityModel.getWeatherForDayModelList().get(0).getHumidity();
        press = weatherByCityModel.getWeatherForDayModelList().get(0).getPressure();
        wind_speed = weatherByCityModel.getWeatherForDayModelList().get(0).getSpeed();
        wind_degree = weatherByCityModel.getWeatherForDayModelList().get(0).getDeg();
        weatherImage = weatherByCityModel.getWeatherForDayModelList().get(0).getWeatherDescriptionModel().get(0).getIcon();
    }

    private void calcData() {
        tempCels = curr_temp - 273;
        tempMaxCels = max_temp - 273;
        tempMinCels = min_temp - 273;
    }

    //translate from EU to RUS
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

    //add background color for any status description!!!
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
        Intent intent = new Intent(getApplicationContext(), WeatherDetailsActivity.class);
        intent.putExtra("WeatherForDayModel", v);
        startActivity(intent);
    }

    private void getSevenDayWeather() {
        RESTClient.getInstance().getSevenDayWeatherExample().getWeatherExample(q, appid, cnt).enqueue(new Callback<WeatherByCityModel>() {

            @Override
            public void onResponse(@NonNull Call<WeatherByCityModel> call, @NonNull Response<WeatherByCityModel> response) {
                if (response.isSuccessful()) {
                    weatherByCityModel = response.body();
                } else {
                    Toast.makeText(ComboActivity.this, R.string.sorry_bad_city, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherByCityModel> call, @NonNull Throwable t) {
                Toast.makeText(ComboActivity.this, R.string.no_inet, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean getCityName() {
        cityCheck = getIntent().getParcelableExtra("CityCheck");

        String s = cityCheck.getCityCheckName();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(getApplicationContext(), "Please enter the WeatherByCity Name!!", Toast.LENGTH_SHORT).show();
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

}
