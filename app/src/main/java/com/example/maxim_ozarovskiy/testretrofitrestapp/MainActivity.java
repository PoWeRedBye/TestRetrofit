package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityCheck;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.WeatherByCityModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String q;
    private String appid = "2fa8c9a46e8ac6ad4bcc4f4fc48e5865";
    private String cnt = "7";

    private CityCheck cityCheck;

    WeatherByCityModel weatherByCityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isValidCity();
        getSevenDayWeather();
        finish();
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

    public void startThirdActivity() {
        Intent intent = new Intent(getApplicationContext(), ComboActivity.class);
        intent.putExtra(ComboActivity.sixteenDayBundleExample, weatherByCityModel);
        startActivity(intent);
    }

    private void getSevenDayWeather() {
        RESTClient.getInstance().getSevenDayWeatherExample().getWeatherExample(q, appid, cnt).enqueue(new Callback<WeatherByCityModel>() {

            @Override
            public void onResponse(@NonNull Call<WeatherByCityModel> call, @NonNull Response<WeatherByCityModel> response) {
                if (response.isSuccessful()) {
                    weatherByCityModel = response.body();
                    startThirdActivity();
                } else {
                    Toast.makeText(MainActivity.this, R.string.sorry_bad_city, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherByCityModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, R.string.no_inet, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
