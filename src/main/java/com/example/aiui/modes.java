package com.example.aiui;
import java.util.ArrayList;
import java.util.List;


public interface modes {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
