package com.example.covidtracker;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchBar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentInteractionListener onFragmentInteractionListener;

    private static final String TAG = "Main";
    private static final String BASE_URL= "https://api.covid19api.com/";
    String mCountry, mNewConfirmed, mTotalConfirmed, mTotalRecovered;
    //String[] mCovidHeader = {"Country", "New Confirmed", "Total Confirmed", "Total Recovered"};
    //String[][] mCovidTableValues;
   // ArrayList<CovidData> mCovidDataArrayList;
    //TableView<String[]> tableView;
    View view;
    SearchAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    EditText searchView;
    ArrayList<CovidData >mCovidDataArrayList = new ArrayList<>();



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchBar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchBar.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchBar newInstance(String param1, String param2) {
        SearchBar fragment = new SearchBar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
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
                    Log.d(TAG, "mResponse Server responses" + response.toString());
                    Log.d(TAG, "mResponse well" + response.body().toString());
                    ArrayList<Countries> countriesArrayList = response.body().getCountries();
                    Log.d("wait", String.valueOf(countriesArrayList.size()));

                    //mCovidTableValues = new String[countriesArrayList.size()][4];

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


//                        mCovidTableValues[i][0]= countriesArrayList.get(i).getCountry();
//                        mCovidTableValues[i][1]= countriesArrayList.get(i).getNewConfirmed();
//                        mCovidTableValues[i][2]= countriesArrayList.get(i).getTotalConfirmed();
//                        mCovidTableValues[i][3]= countriesArrayList.get(i).getTotalRecovered();
//                        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), mCovidHeader));
//                        tableView.setDataAdapter(new SimpleTableDataAdapter(getContext(), mCovidTableValues));
                        CovidData covidData = new CovidData(mCountry, mNewConfirmed, mTotalConfirmed, mTotalRecovered);
                        mCovidDataArrayList.add(covidData);
                    }
                    adapter.addItems(mCovidDataArrayList);

                }

                @Override
                public void onFailure(Call<Feed> call, Throwable t) {
                    Log.d(TAG, "Abort Mission Abort" + t.getMessage());
                }
            });

        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_bar, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        searchView = view.findViewById(R.id.search_bar);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new SearchAdapter(getContext(), new ArrayList<CovidData>());
        recyclerView.setAdapter(adapter);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                adapter.
//                return false;
//            }
//        });
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });
        return view;
    }

    private void filter(String toString) {
        ArrayList<CovidData> covidDatapass = new ArrayList<>();
        for(CovidData item : mCovidDataArrayList){
            if(item.getCountry().toLowerCase().contains(toString.toLowerCase())){
                covidDatapass.add(item);
            }
        }
        adapter.filterList(covidDatapass);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//
//        inflater.inflate(R.menu.filter_data, menu);
//        Log.d("REACH","REACH");
//        super.onCreateOptionsMenu(menu,inflater);
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView)menuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                adapter.getFilter().filter(s);
//                return false;
//            }
//        });
//
//
//    }

}
