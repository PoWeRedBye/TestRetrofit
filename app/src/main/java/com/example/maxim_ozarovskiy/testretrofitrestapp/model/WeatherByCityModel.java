package com.example.maxim_ozarovskiy.testretrofitrestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class WeatherByCityModel {

    @SerializedName("city")
    @Expose
    private CityInformationModel cityInformationModel;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private ArrayList<WeatherForDayModel> weatherForDayModelList;

    public ArrayList<WeatherForDayModel> getWeatherForDayModelList() {
        return weatherForDayModelList;
    }

    public void setWeatherForDayModelList(ArrayList<WeatherForDayModel> weatherForDayModelList) {
        this.weatherForDayModelList = weatherForDayModelList;
    }

    public CityInformationModel getCityInformationModel() {
        return cityInformationModel;
    }

    public void setCityInformationModel(CityInformationModel cityInformationModel) {
        this.cityInformationModel = cityInformationModel;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }


    public WeatherByCityModel() {
    }


}