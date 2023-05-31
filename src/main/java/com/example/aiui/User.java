package com.example.aiui;

interface messageReceiver{
    String receiveMessage(String message);
}
class Bot implements messageReceiver{
    private String name;

    Bot(String name){
        this.name = name;
    }
    @Override
    public String receiveMessage(String message) {
        System.out.println("Bot received message: " + message);
        String reply = generateReply(message);
        System.out.println(this.name+": " + reply);
        return reply;
    }
    private String generateReply(String message) {

        return "This is the bot's reply to: " + message;
    }

}

class User {
    private static int IDcounter = 1;
    private String firstName;
    private String lastName;
    private String password;
    private String Username;
    private int employeeID;
    private String Email;
    private boolean administrator;
    private Bot bot;

    public User(String firstName, String lastName, String password, String username, int employeeID, String email, boolean administrator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        Username = username;
        this.employeeID = employeeID;
        Email = email;
        this.administrator = administrator;
        bot = new Bot("bot");
    }

    public User(String firstName, String lastName, String password, String username, String email, boolean administrator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        Username = username;
        IDcounter += 1;
        this.employeeID = IDcounter;
        Email = email;
        this.administrator = administrator;
        bot = new Bot("bot");
    }

    public User(){}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public boolean isAdministrator() {
        return administrator;
    }
    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public String sendMessage(String message){
        System.out.println(getUsername()+": "+message);
        return bot.receiveMessage(message);
    }


}
