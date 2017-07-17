package com.example.maxim_ozarovskiy.testretrofitrestapp;

import com.example.maxim_ozarovskiy.testretrofitrestapp.WeatherServiceInterface.SixteenDayWeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maxim_Ozarovskiy on 12.07.2017.
 */

public class RESTClient {

    private static final String BASE_URL = "http://api.openweathermap.org/";
    private Retrofit retrofit;
    private final OkHttpClient client;

    private static RESTClient ourInstance = new RESTClient();

    public static RESTClient getInstance() {
        return ourInstance;
    }

    public RESTClient() {
        Gson gson = new GsonBuilder()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                })
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public SixteenDayWeatherService getSixteenDayWeatherExample() {
        return retrofit.create(SixteenDayWeatherService.class);
    }


}
