package com.example.aiui;

import java.util.ArrayList;
import java.util.List;

public class modesData implements modes{
    private boolean darkmode;
    private boolean lightmode;
    private  boolean colormode1;
    private boolean colormode2;
    private List<Observer> observers;
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
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(darkmode, lightmode, colormode1, colormode2);
        }

    }
}
