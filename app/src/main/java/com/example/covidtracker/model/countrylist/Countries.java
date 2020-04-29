package com.example.covidtracker.model.countrylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Countries {


    @SerializedName("Country")
    @Expose
    private String Country;

    @SerializedName("NewConfirmed")
    @Expose
    private String NewConfirmed;

    @SerializedName("TotalConfirmed")
    @Expose
    private String TotalConfirmed;

    @SerializedName("TotalRecovered")
    @Expose
    private String TotalRecovered;


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getNewConfirmed() {
        return NewConfirmed;
    }

    public void setNewConfirmed(String newConfirmed) {
        NewConfirmed = newConfirmed;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "Country='" + Country + '\'' +
                ", NewConfirmed='" + NewConfirmed + '\'' +
                ", TotalConfirmed='" + TotalConfirmed + '\'' +
                ", TotalRecovered='" + TotalRecovered + '\'' +
                '}';
    }
}
