package com.example.aiui;
import java.util.ArrayList;


abstract class UserFactory{

    abstract User createUser(ArrayList<String > Userdata);

    public void AddUser(User user, UserDB userDB) {
        userDB.addUser(user);
    }

    final void CreateAndAddUser(ArrayList<String > Userdata, UserDB userDB){
        User user = createUser(Userdata);
        AddUser(user,userDB);
    }
}

class administratorFactory extends UserFactory {


    @Override
    User createUser(ArrayList<String> Userdata) {
        return new Administrator(Userdata);
    }
}
class employeeFactory extends UserFactory {


    @Override
    User createUser(ArrayList<String> Userdata) {
        return new Employee(Userdata);
    }
}


abstract class User{
    private GespreksManager gespreksManager;
    private static int IDcounter = 0;
    private String firstName;
    private String lastName;
    private String password;
    private String Username;
    private final int employeeID;
    private String Email;


    public User(ArrayList<String> Userdata) {
        this.firstName = Userdata.get(0);
        this.lastName = Userdata.get(1);
        this.Email = Userdata.get(2);
        this.Username = Userdata.get(3);
        this.password = Userdata.get(4);
        this.employeeID = IDcounter;
        IDcounter += 1;
        this.gespreksManager = new GespreksManager();

    }


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

    public GespreksManager getGespreksManager() {
        return gespreksManager;
    }

    public void setGespreksManager(GespreksManager gespreksManager) {
        this.gespreksManager = gespreksManager;
    }
}
class Employee extends User{
    Employee(ArrayList<String > Userdata){
        super( Userdata);
    }
}

class Administrator extends User{
    Administrator(ArrayList<String > Userdata){
        super(Userdata);
    }
}

class UserDB{
    private final ArrayList<User> users;
    public UserDB(){
        users = new ArrayList<>();
    }
    public void addUser(User user){
        users.add(user);
    }
    public ArrayList<User> getUsers(){
        return users;
    }
}



class Data {
    private final UserDB UserDB;
    public Data(){
        UserDB = new UserDB();
        Standaardlogin standaardlogin = new Standaardlogin();
        this.registerUser(standaardlogin.getStandaardLoginGegevens(), true);
    }

    public Boolean registerUser(ArrayList<String> Userdata,  boolean administrator) {
        //check password strenght
        PasswordCheck passwordCheck = new PasswordCheck();
        if (passwordCheck.wachtwoordSterkteVerwerker(Userdata.get(4))) {
            UserFactory userFactory = (administrator)? new administratorFactory() : new employeeFactory();
            userFactory.CreateAndAddUser(Userdata, this.UserDB);
            return true;
        }
        else {
            return false;
        }
    }


    public UserDB getUserDB() {
        return UserDB;
    }

    public User login(String username, String password) {
        for(User user: UserDB.getUsers()){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return user;
            }
        }
        System.out.println("no user matches the given credentials");
        return null; //login credentials match no user
    }

}
class Standaardlogin{
    ArrayList<String> StandaardLoginGegevens;

    public Standaardlogin() {
        StandaardLoginGegevens = new ArrayList<String>();
        StandaardLoginGegevens.add("john");
        StandaardLoginGegevens.add("doe");
        StandaardLoginGegevens.add("johndoe@emailadress.com");
        StandaardLoginGegevens.add("abc");
        StandaardLoginGegevens.add("Lol123");
    }

    public ArrayList<String> getStandaardLoginGegevens() {
        return StandaardLoginGegevens;
    }
}
