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
import sidekick.CovidData;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL= "https://api.covid19api.com/";
    String mCountry, mNewConfirmed, mTotalConfirmed, mTotalRecovered;
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
                final ArrayList<CovidData> covidDataArrayList = new ArrayList<>();
                Log.d(TAG, "mResponse Server responses" + response.toString());
                Log.d(TAG, "mResponse well" + response.body().toString());
                ArrayList<Countries> countriesArrayList = response.body().getCountries();
                Log.d("wait", String.valueOf(countriesArrayList.size()));
                for(int i=0; i<countriesArrayList.size(); i++){
                    Log.d(TAG,"onResponse: \n"+
                            "Country"+  countriesArrayList.get(i).getCountry() + "\n" +
                            "NewConfirmed"+ countriesArrayList.get(i).getNewConfirmed() + "\n"+
                            "TotalConfirmed"+ countriesArrayList.get(i).getTotalConfirmed() + "\n"+
                            "TotalRecovered"+ countriesArrayList.get(i).getTotalRecovered() + "\n");
                    mCountry = countriesArrayList.get(i).getCountry();
                    mNewConfirmed= countriesArrayList.get(i).getNewConfirmed();
                    mTotalConfirmed= countriesArrayList.get(i).getTotalConfirmed();
                    mTotalRecovered= countriesArrayList.get(i).getTotalRecovered();


                    CovidData covidData = new CovidData(mCountry, mNewConfirmed, mTotalConfirmed, mTotalRecovered);
                    covidDataArrayList.add(covidData);
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.d(TAG, "Abort Mission Abort" + t.getMessage());
            }
        });
    }
}
