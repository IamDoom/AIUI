package com.example.aiui;

interface messageFunction{
     void consolePresentation();

     String sendMessage();

}

abstract class message implements messageFunction{
    private String message;
    message(String message){
        this.message = message;
    }

    public String sendMessage(){
        return this.message;
    }

    public String getMessage(){
        return this.message;
    }

    @Override
    public void consolePresentation() {
        System.out.println(this.sendMessage());
    }

}
class question extends message{
    private String type;
    private String sender;
    question(String message, String sender){
        super(message);
        this.type = "question";
        this.sender = sender;
    }

    public String getSender(){
        return sender;
    }

    @Override
    public String sendMessage(){
        String identity = this.sender;
        String question = super.sendMessage();
        return identity+": "+question;
    }

}

class answer extends message {
    private String question;
    private String sender;

    answer(question question, String message) {
        super(message);
        this.question = question.getMessage();
        this.sender = question.getSender();
    }
    //for a question to be answered with an answer the method or answer must first be supplied with the question

    @Override
    public void consolePresentation(){
        System.out.println(super.getMessage());
    }


}



public class chat {


}
