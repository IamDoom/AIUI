package com.example.aiui;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class passwordtest {
    PasswordCheck passwordCheck = new PasswordCheck();
    @Test
    public void passwordlowstrengthtest(){
        Employee employee = new Employee("", "", "", "", "pass");
        passwordCheck.WachtWoordStengthVerwerker(employee.getPassword());

    }
    @Test
    public void passwordhighstrengthtest(){
        Employee employee = new Employee("", "", "", "", "Password123*");
        passwordCheck.WachtWoordStengthVerwerker(employee.getPassword());
    }

}
