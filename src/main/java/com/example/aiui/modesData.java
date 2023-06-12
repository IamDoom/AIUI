package com.example.aiui;

import java.util.ArrayList;
import java.util.List;

public class modesData implements modes{
    private boolean darkmode;
    private boolean lightmode;
    private  boolean colormode1;
    private boolean colormode2;
    private List<observer> observers;
    public modesData(){
        observers = new ArrayList<>();
    }
    public void setmode(boolean darkmode, boolean lightmode, boolean colormode1, boolean colormode2){
        this.darkmode = darkmode;
        this.lightmode = lightmode;
        this.colormode1 = colormode1;
        this.colormode2 = colormode2;
        notifyObservers();
    }
    public boolean getdarkmode() {
        return true;
    }
    public boolean getlightmode() {
        return lightmode;
    }
    public boolean getcolor1() {
        return colormode1;
    }
    public boolean getcolor2() {
        return colormode2;
    }
    @Override
    public void registerObserver(observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (observer observer : observers) {
            observer.update(darkmode, lightmode, colormode1, colormode2);
        }

    }
}
