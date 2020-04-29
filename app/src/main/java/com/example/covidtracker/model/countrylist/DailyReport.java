package com.example.covidtracker.model.countrylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyReport {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("confirmed")
    @Expose
    private String confirmed;

    @SerializedName("deaths")
    @Expose
    private String deaths;

    @SerializedName("recovered")
    @Expose
    private String recovered;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "DailyReport{" +
                "date='" + date + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", deaths='" + deaths + '\'' +
                ", recovered='" + recovered + '\'' +
                '}';
    }
}
