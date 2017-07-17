package com.example.maxim_ozarovskiy.testretrofitrestapp.WeatherServiceInterface;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maxim_Ozarovskiy on 16.07.2017.
 */

public interface SixteenDayWeatherService {
    @GET("data/2.5/forecast/daily")
    Call<Example> getWeatherExample(@Query("q") String q,
                                    @Query("appid") String appid,
                                    @Query("cnt") String cnt);
}
