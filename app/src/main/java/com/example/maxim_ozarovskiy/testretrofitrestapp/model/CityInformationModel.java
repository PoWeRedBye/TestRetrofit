package com.example.maxim_ozarovskiy.testretrofitrestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityInformationModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private WeatherCoordinateModel weatherCoordinateModel;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private Integer population;
    public final static Parcelable.Creator<CityInformationModel> CREATOR = new Creator<CityInformationModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CityInformationModel createFromParcel(Parcel in) {
            CityInformationModel instance = new CityInformationModel();
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.weatherCoordinateModel = ((WeatherCoordinateModel) in.readValue((WeatherCoordinateModel.class.getClassLoader())));
            instance.country = ((String) in.readValue((String.class.getClassLoader())));
            instance.population = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public CityInformationModel[] newArray(int size) {
            return (new CityInformationModel[size]);
        }

    };


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherCoordinateModel getWeatherCoordinateModel() {
        return weatherCoordinateModel;
    }

    public void setWeatherCoordinateModel(WeatherCoordinateModel weatherCoordinateModel) {
        this.weatherCoordinateModel = weatherCoordinateModel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(weatherCoordinateModel);
        dest.writeValue(country);
        dest.writeValue(population);
    }

    public int describeContents() {
        return 0;
    }

}