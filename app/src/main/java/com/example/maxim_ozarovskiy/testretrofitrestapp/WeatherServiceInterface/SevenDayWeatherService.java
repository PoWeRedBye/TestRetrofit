package com.example.maxim_ozarovskiy.testretrofitrestapp.WeatherServiceInterface;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.WeatherByCityModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maxim_Ozarovskiy on 16.07.2017.
 */

public interface SevenDayWeatherService {

    @GET("data/2.5/forecast/daily")
    Call<WeatherByCityModel> getWeatherExample(@Query("q") String q,
                                               @Query("appid") String appid,
                                               @Query("cnt") String cnt,
                                               @Query("units") String units,
                                               @Query("lang") String lang);
}
