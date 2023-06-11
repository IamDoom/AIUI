package com.example.aiui;
import java.util.ArrayList;

interface MessageReceiver {
    void receiveMessage(String message);

}
/*
user has method startchat() start chat creates chat object which calls chat which may or may not create a bot (still considering)
then this method interfaces with the guy and then when it calls on the method to append to chat log. then when new chat is started
or chat is closed the startchat method returns a method of getting the chat and storing it while restarting the startchat method.
this is a mere sggestion based on the college class 30/05/2023
*/

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
