package com.example.aiui;

import java.util.ArrayList;

interface ResponseGenerator {
    String generateResponse(String message);
}

class ConceptResponseGenerator implements ResponseGenerator {
    private final String defaultGreeting = "Hi! How can I assist you?";
    private String defaultHowAreYou = "As an AI, I don't have feelings or emotions, but I'm functioning as intended and ready to help you. How can I assist you today?";
    private String defaultFallback = "I don't have an answer for that now";

    @Override
    public String generateResponse(String message) {
        System.out.println("AI received message: " + message);
        String response = getResponse(message);
        System.out.println("Bot's reply: " + response);
        return response;
    }

    private String getResponse(String userMessage) {
        // Replace this logic with your own or use an AI/chatbot API
        switch (userMessage){
            case "hello":
                return defaultGreeting;
            case "how are you":
                return defaultHowAreYou;
            default:
                return defaultFallback;
        }
    }
}

class GespreksManager {
    private ArrayList<Gesprek> gesprekkenLijst;
    ResponseGenerator responseGenerator = new ConceptResponseGenerator();

    public GespreksManager() {
        gesprekkenLijst = new ArrayList<>();
        Gesprek eersteGesprek = new Gesprek(responseGenerator);
        eersteGesprek.setId(0);
        gesprekkenLijst.add(eersteGesprek);
    }

    public Gesprek newGesprek() {
        Gesprek gesprek = new Gesprek(responseGenerator);
        gesprek.setId(gesprekkenLijst.size());
        gesprekkenLijst.add(gesprek);
        return gesprek;
    }

    public ArrayList<Gesprek> getGesprekkenLijst() {
        return gesprekkenLijst;
    }

    public Gesprek getGesprek(int gesprekId) {
        for (Gesprek gesprek : gesprekkenLijst) {
            if (gesprek.getId() == gesprekId) {
                return gesprek;
            }
        }
        return null;
    }

    public ArrayList<String> getOnderwerpen() {
        ArrayList<String> onderwerpen = new ArrayList<>();
        for (Gesprek gesprek : gesprekkenLijst) {
            onderwerpen.add(gesprek.getOnderwerp());
        }
        return onderwerpen;
    }

    public String generateResponseVoorGesprek(Gesprek gesprek, String input) {
        return gesprek.getGesprekDataManager().generateResponse(input);
    }
}

public class Gesprek {
    private String onderwerp;
    private int id = 0;
    private GesprekDataManager gesprekDataManager;
    private ResponseGenerator responseGenerator;

    public Gesprek(ResponseGenerator responseGenerator) {
        onderwerp = "Geen onderwerp";
        gesprekDataManager = new GesprekDataManager(new ConceptResponseGenerator());
        this.responseGenerator = responseGenerator;
    }

    public GesprekDataManager getGesprekDataManager() {
        return gesprekDataManager;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }
}

class GesprekDataManager {
    private ArrayList<String> gespreksData;
    private ResponseGenerator responseGenerator;

    public GesprekDataManager(ResponseGenerator responseGenerator) {
        this.responseGenerator = responseGenerator;
        gespreksData = new ArrayList<>();
    }

    public String generateResponse(String userMessage) {
        String response = responseGenerator.generateResponse(userMessage);
        rememberData(userMessage, response);
        return response;
    }

    public void rememberData(String data1, String data2) {
        gespreksData.add(data1);
        gespreksData.add(data2);
    }

    public ArrayList<String> getGespreksData() {
        return gespreksData;
    }
}
