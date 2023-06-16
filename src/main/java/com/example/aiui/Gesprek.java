package com.example.aiui;

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
        switch (userMessage){
            case "hello":
                System.out.println("Bot's reply: " + defaultGreeting);
                return defaultGreeting;
            case "how are you":
                System.out.println("Bot's reply: " + defaultHowAreYou);
                return defaultHowAreYou;
            default:
                System.out.println("Bot's reply: " + defaultFallback );
                return defaultFallback;
        }
    }

}

class GesprekManager {
    private final ArrayList<Gesprek> GesprekkenLijst;

    public GesprekManager() {
        GesprekkenLijst = new ArrayList<>();
        Gesprek EersteGesprek = new Gesprek();
        EersteGesprek.setId(0);
        GesprekkenLijst.add(EersteGesprek);
    }

    public Gesprek newGesprek() {
        Gesprek gesprek = new Gesprek();
        gesprek.setId(GesprekkenLijst.size());
        GesprekkenLijst.add(gesprek);
        return gesprek;
    }

    public ArrayList<Gesprek> getGesprekkenLijst() {
        return GesprekkenLijst;
    }

    public Gesprek getGesprek(int gesprekId) {
        Gesprek DitGesprek = null;
        for (Gesprek gesprek : GesprekkenLijst) {
            if (gesprek.getId() == gesprekId) {
                DitGesprek = gesprek;
            }
        }
        return DitGesprek;
    }

    public ArrayList<String> getOnderwerpen() {
        ArrayList<String> Onderwerpen = new ArrayList<String>();
        for (Gesprek gesprek : GesprekkenLijst) {
            Onderwerpen.add(gesprek.getOnderwerp());
        }
        return Onderwerpen;
    }

    public String GenerateResponse(Gesprek gesprek, String input) {
        for (Gesprek locaalGesprek : GesprekkenLijst) {
            if (locaalGesprek.equals(gesprek)) {
                return locaalGesprek.getGesprekDataManager().generateResponse(input);
            }
        }
        return null;
    }


}

public class Gesprek {
    private String Onderwerp;
    private int id = 0;
    private GesprekDataManager gesprekDataManager;
    public Gesprek(){
        Onderwerp = "Geen onderwerp";
        gesprekDataManager = new GesprekDataManager();
    }

    public GesprekDataManager getGesprekDataManager() {
        return gesprekDataManager;
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


class GesprekDataManager {
    public GesprekDataManager(){
        GespreksData = new ArrayList<String>();
    }
    private ArrayList<String> GespreksData;
    private ResponseGenerator responseGenerator = new conceptResponseGenerator();

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

}
