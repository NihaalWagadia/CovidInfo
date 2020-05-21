package com.example.covidtracker.adaptercollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.R;

import java.util.ArrayList;
import java.util.List;

import sidekick.CovidData;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>  {

    private Context mContext;
    private List<CovidData> mCovidDataList;
    private List<CovidData> mCovidDataListCopy;

    public SearchAdapter(Context mContext, List<CovidData> mCovidDataList) {
        this.mContext = mContext;
        this.mCovidDataList = mCovidDataList;
        //making a copy of list to use independently


    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.covidcard, null);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        CovidData covidData = mCovidDataList.get(position);
        holder.textViewCountry.setText(covidData.getCountry());
        holder.textViewRecovered.setText(covidData.getTotalRecovered());
        holder.textViewTotalConfirmed.setText(covidData.getTotalConfirmed());
        holder.textViewNewConfirmed.setText(covidData.getNewConfirmed());

    }

    @Override
    public int getItemCount() {
        return mCovidDataList.size();
    }

    public void addItems(List<CovidData> covidData) {
        mCovidDataList.addAll(covidData);
        notifyDataSetChanged();
    }

    public void  filterList(ArrayList<CovidData> filteredList){
        mCovidDataList = filteredList;
        notifyDataSetChanged();
    }


    public class SearchViewHolder extends  RecyclerView.ViewHolder{

        TextView textViewCountry, textViewRecovered, textViewNewConfirmed, textViewTotalConfirmed;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCountry=itemView.findViewById(R.id.country_card);
            textViewNewConfirmed= itemView.findViewById(R.id.new_confirmed_card_value);
            textViewTotalConfirmed = itemView.findViewById(R.id.total_confirmed_card_value);
            textViewRecovered = itemView.findViewById(R.id.total_recovered_card_value);

        }
    }
}
