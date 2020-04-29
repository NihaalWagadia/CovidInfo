package com.example.covidtracker.model;

import com.example.covidtracker.model.countrylist.Countries;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Feed {

    @SerializedName("Countries")
    @Expose
    public ArrayList<Countries> countries;

    public ArrayList<Countries> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Countries> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "countries=" + countries +
                '}';
    }
}
