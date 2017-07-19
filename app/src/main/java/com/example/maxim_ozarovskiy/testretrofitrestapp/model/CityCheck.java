package com.example.maxim_ozarovskiy.testretrofitrestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class CityCheck implements Parcelable {

    private String id;
    private String cityCheckName;

    public CityCheck(String id, String cityCheckName) {
        this.id = id;
        this.cityCheckName = cityCheckName;
    }

    public CityCheck() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityCheckName() {
        return cityCheckName;
    }

    public void setCityCheckName(String cityCheckName) {
        this.cityCheckName = cityCheckName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.cityCheckName);
    }

    protected CityCheck(Parcel in) {
        this.id = in.readString();
        this.cityCheckName = in.readString();
    }

    public static final Parcelable.Creator<CityCheck> CREATOR = new Parcelable.Creator<CityCheck>() {
        @Override
        public CityCheck createFromParcel(Parcel source) {
            return new CityCheck(source);
        }

        @Override
        public CityCheck[] newArray(int size) {
            return new CityCheck[size];
        }
    };
}
