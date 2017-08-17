package com.example.maxim_ozarovskiy.testretrofitrestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class CityModel implements Parcelable {

    private String id;
    private String cityName;

    public CityModel(String id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public CityModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.cityName);
    }

    protected CityModel(Parcel in) {
        this.id = in.readString();
        this.cityName = in.readString();
    }

    public static final Parcelable.Creator<CityModel> CREATOR = new Parcelable.Creator<CityModel>() {
        @Override
        public CityModel createFromParcel(Parcel source) {
            return new CityModel(source);
        }

        @Override
        public CityModel[] newArray(int size) {
            return new CityModel[size];
        }
    };
}
