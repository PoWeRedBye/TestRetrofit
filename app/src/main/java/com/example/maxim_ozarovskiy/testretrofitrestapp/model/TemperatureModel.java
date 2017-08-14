package com.example.maxim_ozarovskiy.testretrofitrestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TemperatureModel implements Parcelable {

    @SerializedName("day")
    @Expose
    private Double day;
    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("max")
    @Expose
    private Double max;
    @SerializedName("night")
    @Expose
    private Double night;
    @SerializedName("eve")
    @Expose
    private Double eve;
    @SerializedName("morn")
    @Expose
    private Double morn;
    public final static Parcelable.Creator<TemperatureModel> CREATOR = new Creator<TemperatureModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TemperatureModel createFromParcel(Parcel in) {
            TemperatureModel instance = new TemperatureModel();
            instance.day = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.min = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.max = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.night = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.eve = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.morn = ((Double) in.readValue((Double.class.getClassLoader())));
            return instance;
        }

        public TemperatureModel[] newArray(int size) {
            return (new TemperatureModel[size]);
        }

    };

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEve() {
        return eve;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public Double getMorn() {
        return morn;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(day);
        dest.writeValue(min);
        dest.writeValue(max);
        dest.writeValue(night);
        dest.writeValue(eve);
        dest.writeValue(morn);
    }

    public int describeContents() {
        return 0;
    }

}