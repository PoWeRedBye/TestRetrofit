package com.example.maxim_ozarovskiy.testretrofitrestapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maxim_Ozarovskiy on 13.07.2017.
 */

public interface WeatherService {
    @GET("data/2.5/weather")
    Call<Example> getWeatherExample(@Query("q") String q,
                                    @Query("appid") String appid);
}
