package com.example.covidtracker.networkcall;

import android.preference.PreferenceActivity;

import com.example.covidtracker.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PombApi {

    String BASE_API = "https://api.covid19api.com/";
    @Headers("Content-Type: application/json")
    @GET("summary")
    Call<Feed> getData();

}
