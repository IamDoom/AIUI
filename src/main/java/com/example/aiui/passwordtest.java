package com.example.aiui;
import org.testng.annotations.Test;

public class passwordtest {
    PasswordCheck passwordCheck = new PasswordCheck();
    @Test
    public void passwordlowstrengthtest(){
        Employee employee = new Employee("", "", "", "", "pass");
        passwordCheck.wachtwoordSterkteVerwerker(employee.getPassword());

    }
    @Test
    public void passwordhighstrengthtest(){
        Employee employee = new Employee("", "", "", "", "Password123*");
        passwordCheck.wachtwoordSterkteVerwerker(employee.getPassword());
    }

}