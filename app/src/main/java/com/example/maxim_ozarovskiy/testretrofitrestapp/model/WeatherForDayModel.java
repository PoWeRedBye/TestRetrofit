package com.example.maxim_ozarovskiy.testretrofitrestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class WeatherForDayModel  {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("temp")
    @Expose
    private TemperatureModel temperatureModel;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("weather")
    @Expose
    private ArrayList<WeatherDescriptionModel> weatherDescriptionModel;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Double deg;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("rain")
    @Expose
    private Double rain;
    @SerializedName("snow")
    @Expose
    private Double snow;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public TemperatureModel getTemperatureModel() {
        return temperatureModel;
    }

    public void setTemperatureModel(TemperatureModel temperatureModel) {
        this.temperatureModel = temperatureModel;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public Double getSnow() {
        return snow;
    }

    public void setSnow(Double snow) {
        this.snow = snow;
    }

    public ArrayList<WeatherDescriptionModel> getWeatherDescriptionModel() {
        return weatherDescriptionModel;
    }

    public void setWeatherDescriptionModel(ArrayList<WeatherDescriptionModel> weatherDescriptionModel) {
        this.weatherDescriptionModel = weatherDescriptionModel;
    }

    public WeatherForDayModel() {
    }
}