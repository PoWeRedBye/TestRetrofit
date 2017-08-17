package com.example.maxim_ozarovskiy.testretrofitrestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherByCityModel implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.cityInformationModel, flags);
        dest.writeString(this.cod);
        dest.writeValue(this.message);
        dest.writeValue(this.cnt);
        dest.writeTypedList(this.weatherForDayModelList);
    }

    public WeatherByCityModel() {
    }

    protected WeatherByCityModel(Parcel in) {
        this.cityInformationModel = in.readParcelable(CityInformationModel.class.getClassLoader());
        this.cod = in.readString();
        this.message = (Double) in.readValue(Double.class.getClassLoader());
        this.cnt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weatherForDayModelList = in.createTypedArrayList(WeatherForDayModel.CREATOR);
    }

    public static final Parcelable.Creator<WeatherByCityModel> CREATOR = new Parcelable.Creator<WeatherByCityModel>() {
        @Override
        public WeatherByCityModel createFromParcel(Parcel source) {
            return new WeatherByCityModel(source);
        }

        @Override
        public WeatherByCityModel[] newArray(int size) {
            return new WeatherByCityModel[size];
        }
    };
}