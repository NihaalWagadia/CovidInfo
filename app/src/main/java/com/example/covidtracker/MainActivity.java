package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.covidtracker.model.Feed;
import com.example.covidtracker.model.countrylist.Countries;
import com.example.covidtracker.networkcall.PombApi;

import java.util.ArrayList;
import java.util.Arrays;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sidekick.CovidData;
/////NO USE
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL= "https://api.covid19api.com/";
    String mCountry, mNewConfirmed, mTotalConfirmed, mTotalRecovered;
    String[] mCovidHeader = {"Country", "New Confirmed", "Total Confirmed", "Total Recovered"};
    String[][] mCovidTableValues;
    ArrayList<CovidData> mCovidDataArrayList;
    TableView<String[]> tableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tableView =  findViewById(R.id.tableView);
        tableView.setColumnCount(4);
//        TableColumnWeightModel tableColumnWeightModel = new TableColumnWeightModel(4);
//        tableColumnWeightModel.setColumnWeight(0,1);
//        tableColumnWeightModel.setColumnWeight(1,1);
//        tableColumnWeightModel.setColumnWeight(2,1);
//        tableColumnWeightModel.setColumnWeight(3,1);

        tableView.setHeaderBackgroundColor(Color.parseColor("#2ecc71"));
        populateData();


    }

    private void populateData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PombApi pombApi = retrofit.create(PombApi.class);
        Call<Feed> call = pombApi.getData();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                mCovidDataArrayList = new ArrayList<>();
                Log.d(TAG, "mResponse Server responses" + response.toString());
                Log.d(TAG, "mResponse well" + response.body().toString());
                ArrayList<Countries> countriesArrayList = response.body().getCountries();
                Log.d("wait", String.valueOf(countriesArrayList.size()));

                mCovidTableValues = new String[countriesArrayList.size()][4];

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


                    mCovidTableValues[i][0]= countriesArrayList.get(i).getCountry();
                    mCovidTableValues[i][1]= countriesArrayList.get(i).getNewConfirmed();
                    mCovidTableValues[i][2]= countriesArrayList.get(i).getTotalConfirmed();
                    mCovidTableValues[i][3]= countriesArrayList.get(i).getTotalRecovered();
                    tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getApplicationContext(), mCovidHeader));
                    tableView.setDataAdapter(new SimpleTableDataAdapter(getApplicationContext(), mCovidTableValues));
                    CovidData covidData = new CovidData(mCountry, mNewConfirmed, mTotalConfirmed, mTotalRecovered);
                    mCovidDataArrayList.add(covidData);
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.d(TAG, "Abort Mission Abort" + t.getMessage());
            }
        });

    }


}
