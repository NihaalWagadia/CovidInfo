package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.covidtracker.model.Feed;
import com.example.covidtracker.model.countrylist.Countries;
import com.example.covidtracker.networkcall.PombApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL= "https://api.covid19api.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PombApi pombApi = retrofit.create(PombApi.class);
        Call<Feed> call = pombApi.getData();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "mResponse Server responses" + response.toString());
                Log.d(TAG, "mResponse well" + response.body().toString());


            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.d(TAG, "Abort Mission Abort" + t.getMessage());
            }
        });
    }
}
