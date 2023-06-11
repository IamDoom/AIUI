package com.example.aiui;
import java.util.ArrayList;

interface MessageReceiver {
    void receiveMessage(String message);

}

class tempBot implements MessageReceiver {
    @Override
    public void receiveMessage(String message) {
        System.out.println("Bot received message: " + message);
        String reply = generateReply(message);
        System.out.println("Bot's reply: " + reply);
    }

    private String generateReply(String message) {

        return "This is the bot's reply to: " + message;
    }

}
class tempUser {
    private MessageReceiver messageReceiver;

    public void setMessageReceiver(MessageReceiver messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public void sendMessage(String message) {
        System.out.println("User sent message: " + message);
        messageReceiver.receiveMessage(message);
    }
}
public class chat{
            tempUser tempuser = new tempUser();
            tempBot tempbot = new tempBot();

            //tempuser.setMessageReceiver(tempBot);
            //tempuser.sendMessage("Hello, bot!");

}
