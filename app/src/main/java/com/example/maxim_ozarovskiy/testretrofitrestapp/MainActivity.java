package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String q = "London";
    private String appid = "b1b15e88fa797225412429c1c50c122a1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RESTClient.getInstance().getWeatherExample().getWeatherExample(q,appid).enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }



}
