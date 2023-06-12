package com.example.aiui;
import java.util.ArrayList;
import java.util.List;


public interface modes {

    void registerObserver(observer observer);
    void removeObserver(observer observer);
    void notifyObservers();
}
