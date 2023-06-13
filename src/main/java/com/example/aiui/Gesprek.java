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

class gespreksManager{
    private ArrayList<Gesprek> gesprekken;
    public Gesprek newGesprek(){
        Gesprek gesprek = new Gesprek();
        gesprekken.add(gesprek);
        return gesprek;
    }

    public ArrayList<Gesprek> getGesprekken() {
        return gesprekken;
    }
}

public class Gesprek {
    private String Onderwerp;
    private ArrayList<String> GespreksData;
    private ResponseGenerator responseGenerator = new conceptResponseGenerator();
    private static int id = 0;
    public Gesprek(){
        this.id ++;
        GespreksData = new ArrayList<String>();
        Onderwerp = "Leeg onderwerp";
    }

    public String generateResponse(String userMessage) {
        // Replace this logic with your own or use an AI/chatbot API
        return responseGenerator.messageReceiver(userMessage);
    }

    public void saveConversation(ObservableList<String> conversation) {
        // Stap 1: Implementeer hier je logica om het gesprek op te slaan
        // In dit voorbeeld wordt het gesprek opgeslagen naar een tekstbestand met de naam "conversation.txt"
        try {
            FileWriter writer = new FileWriter("conversation.txt");
            for (String message : conversation) {
                writer.write(message + "\n");
            }
            writer.close();
            System.out.println("Gesprek opgeslagen.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
