package com.example.maxim_ozarovskiy.testretrofitrestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import io.realm.RealmModel;

public class WeatherForDayModel implements Parcelable {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("temperatureModel")
    @Expose
    private TemperatureModel temperatureModel;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("weatherDescriptionModel")
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.dt);
        dest.writeParcelable(this.temperatureModel, flags);
        dest.writeValue(this.pressure);
        dest.writeValue(this.humidity);
        dest.writeTypedList(this.weatherDescriptionModel);
        dest.writeValue(this.speed);
        dest.writeValue(this.deg);
        dest.writeValue(this.clouds);
        dest.writeValue(this.rain);
        dest.writeValue(this.snow);
    }

    protected WeatherForDayModel(Parcel in) {
        this.dt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.temperatureModel = in.readParcelable(TemperatureModel.class.getClassLoader());
        this.pressure = (Double) in.readValue(Double.class.getClassLoader());
        this.humidity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weatherDescriptionModel = in.createTypedArrayList(WeatherDescriptionModel.CREATOR);
        this.speed = (Double) in.readValue(Double.class.getClassLoader());
        this.deg = (Double) in.readValue(Double.class.getClassLoader());
        this.clouds = (Integer) in.readValue(Integer.class.getClassLoader());
        this.rain = (Double) in.readValue(Double.class.getClassLoader());
        this.snow = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<WeatherForDayModel> CREATOR = new Parcelable.Creator<WeatherForDayModel>() {
        @Override
        public WeatherForDayModel createFromParcel(Parcel source) {
            return new WeatherForDayModel(source);
        }

        @Override
        public WeatherForDayModel[] newArray(int size) {
            return new WeatherForDayModel[size];
        }
    };
}