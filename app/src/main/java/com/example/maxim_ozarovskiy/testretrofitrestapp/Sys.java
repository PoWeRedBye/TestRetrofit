package com.example.maxim_ozarovskiy.testretrofitrestapp;

/**
 * Created by Maxim_Ozarovskiy on 13.07.2017.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys implements Parcelable {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.type);
        dest.writeValue(this.id);
        dest.writeValue(this.message);
        dest.writeString(this.country);
        dest.writeValue(this.sunrise);
        dest.writeValue(this.sunset);
    }

    public Sys() {
    }

    protected Sys(Parcel in) {
        this.type = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.message = (Double) in.readValue(Double.class.getClassLoader());
        this.country = in.readString();
        this.sunrise = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sunset = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Sys> CREATOR = new Parcelable.Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel source) {
            return new Sys(source);
        }

        @Override
        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };
}