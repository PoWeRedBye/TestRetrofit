package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        initUI();
        initListeners();

//        Intent intent = getIntent();
//        String city_name = intent.getStringExtra("cityName");
//        cityName.setText(city_name);
        //тут две проблемы, с EditText я не могу взять данные т.к. инициализирует пустое поле до того как я что-то туда введу...
        // путь выходит такой: http://api.openweathermap.org/data/2.5/weather?q=&appid=2fa8c9a46e8ac6ad4bcc4f4fc48e5865
        //т.е. q = null;

        //вторая то что в модель ничего не записывается я пробовал делать модель локальной и анонимной, суть не меняется....
    }

    private void initListeners() {
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCityName();
                getWeather();
            }
        });
    }

    private void initUI() {
        cityName = (EditText) findViewById(R.id.CityCheckName);
        check = (Button) findViewById(R.id.CheckButton);
    }

    private void getCityName() {
        String s = cityName.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            q = s;
        }
    }

    private void getWeather() {
        RESTClient.getInstance().getWeatherExample().getWeatherExample(q, appid).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(@NonNull Call<Example> call, @NonNull Response<Example> response) {
                if (response.isSuccessful()) {
                    ex = response.body();
                    startTwoActivity();
                } else {
                    Toast.makeText(MainActivity.this, R.string.sorry_bad_city, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Example> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, R.string.no_inet, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startTwoActivity() {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra(SecondActivity.bundleExample, ex);
        startActivity(intent);
    }

}
