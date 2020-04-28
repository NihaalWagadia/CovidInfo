package com.example.covidtracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Feed {

@SerializedName("Afghanistan")
    @Expose
    private ArrayList<afghanistan> Afghanistan;

    public ArrayList<afghanistan> getAfghanistan() {
        return Afghanistan;
    }

    public void setAfghanistan(ArrayList<afghanistan> afghanistan) {
        Afghanistan = afghanistan;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "Afghanistan=" + Afghanistan +
                '}';
    }
}
