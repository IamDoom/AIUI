package com.example.aiui;

import java.util.ArrayList;

public class Gesprek {
    private String Onderwerp;
    private ArrayList<String> GespreksData;
    private int id;
    public Gesprek(int id){
        this.id = id;
        GespreksData = new ArrayList<String>();
        Onderwerp = "Leeg onderwerp";
    }


    public ArrayList<String> getGespreksData() {
        return GespreksData;
    }

    public String getOnderwerp() {
        return Onderwerp;
    }

    public int getId() {
        return id;
    }

    public void setOnderwerp(String onderwerp) {
        Onderwerp = onderwerp;
    }
}
