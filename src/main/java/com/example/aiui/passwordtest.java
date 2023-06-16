package com.example.aiui;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class passwordtest {
    PasswordCheck passwordCheck = new PasswordCheck();
    @Test
    public void passwordlowstrengthtest(){
        ArrayList<String > Userdata = new ArrayList<>();
        Userdata.add("");
        Userdata.add("");
        Userdata.add("");
        Userdata.add("");
        Userdata.add("pass");
        Employee employee = new Employee(Userdata);
        passwordCheck.wachtwoordSterkteVerwerker(employee.getPassword());

    }
    @Test
    public void passwordhighstrengthtest(){
        ArrayList<String > Userdata = new ArrayList<>();
        Userdata.add("");
        Userdata.add("");
        Userdata.add("");
        Userdata.add("");
        Userdata.add("Password123*");
        Employee employee = new Employee(Userdata);
        passwordCheck.wachtwoordSterkteVerwerker(employee.getPassword());
    }

}