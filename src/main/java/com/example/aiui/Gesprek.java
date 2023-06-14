package com.example.aiui;

import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

interface ResponseGenerator{
    String messageReceiver(String message);
}

class conceptResponseGenerator implements ResponseGenerator{
    private String defaultGreeting = "Hi! How can I assist you?";
    private String defaultHowAreYou = "As an AI, I don't have feelings or emotions, but I'm functioning as intended and ready to help you. How can I assist you today?";
    private String defaultFallback = "I don't have an answer for that now";

    public String messageReceiver(String message){
        System.out.println("AI received message: " + message);
        String response = response(message);
        return response;
    }

    private String response(String userMessage) {
        // Replace this logic with your own or use an AI/chatbot API

        if (userMessage.equalsIgnoreCase("hello")) {
            System.out.println("Bot's reply: " + defaultGreeting);
            return defaultGreeting;
        } else if (userMessage.toLowerCase().startsWith("how are you")) {
            System.out.println("Bot's reply: " + defaultHowAreYou);
            return defaultHowAreYou;
        } else {
            System.out.println("Bot's reply: " + defaultFallback );
            return defaultFallback;
        }
    }

}

class gespreksManager {
    private ArrayList<Gesprek> gesprekken;

    public gespreksManager() {
        gesprekken = new ArrayList<Gesprek>();
        Gesprek EersteGesprek = new Gesprek();
        EersteGesprek.setId(0);
        gesprekken.add(EersteGesprek);
    }

    public Gesprek newGesprek() {
        Gesprek gesprek = new Gesprek();
        gesprek.setId(gesprekken.size());
        gesprekken.add(gesprek);
        return gesprek;
    }

    public ArrayList<Gesprek> getGesprekken() {
        return gesprekken;
    }

    public Gesprek getGesprek(int gesprekId) {
        Gesprek DitGesprek = null;
        for (Gesprek gesprek : gesprekken) {
            if (gesprek.getId() == gesprekId) {
                DitGesprek = gesprek;
            }
        }
        return DitGesprek;
    }

    public ArrayList<String> getOnderwerpen() {
        ArrayList<String> Onderwerpen = new ArrayList<String>();
        for (Gesprek gesprek : gesprekken) {
            Onderwerpen.add(gesprek.getOnderwerp());
        }
        return Onderwerpen;
    }

    public String GenerateResponseJuisteGesprek(Gesprek gesprek, String input) {
        System.out.println("asdasd");
        for (Gesprek locaalgesprek : gesprekken) {
            if (locaalgesprek.equals(gesprek)) {
                System.out.println(locaalgesprek.generateResponse(input));
                return locaalgesprek.generateResponse(input);
            }
        }
        return null;
    }
}

public class Gesprek {
    private String Onderwerp;
    private ArrayList<String> GespreksData;
    private ResponseGenerator responseGenerator = new conceptResponseGenerator();
    private int id = 0;
    public Gesprek(){
        GespreksData = new ArrayList<String>();
        Onderwerp = "Geen onderwerp";
    }
    public String generateResponse(String userMessage) {
        String response = responseGenerator.messageReceiver(userMessage);
        this.OnthoudData(userMessage, response);
        return response;
    }
    public void OnthoudData(String data1, String data2){
        GespreksData.add(data1);
        GespreksData.add(data2);
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

    public void setId(int id) {
        this.id = id;
    }

    public void setOnderwerp(String onderwerp) {
        Onderwerp = onderwerp;
    }
}
