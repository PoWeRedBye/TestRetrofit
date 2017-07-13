package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String q = "Kharkov";
    private String appid = "2fa8c9a46e8ac6ad4bcc4f4fc48e5865";

    private String city_name;
    Example ex;

    EditText cityName;
    Button check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_check);

        cityName = (EditText) findViewById(R.id.CityCheckName);
        check = (Button) findViewById(R.id.CheckButton);

        Intent intent = getIntent();
        String city_name = intent.getStringExtra("cityName");
        cityName.setText(city_name);




        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RESTClient.getInstance().getWeatherExample().getWeatherExample(q,appid).enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        ex = response.body();
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        intent.putExtra("Example",ex);
                        startActivity(intent);

                    }
                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                    }
                });

            }
        });


    }

    public void startTwoActivity(){
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("Example",ex);
        startActivity(intent);
    }

}
