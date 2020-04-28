package com.example.covidtracker.networkcall;

import android.preference.PreferenceActivity;

import com.example.covidtracker.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PombApi {

    String BASE_API = "https://pomber.github.io/covid19/";
    @Headers("Content-Type: application/json")
    @GET("timeseries.json")
    Call<Feed> getData();

}
