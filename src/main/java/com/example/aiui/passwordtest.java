package com.example.aiui;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class passwordtest {
    PasswordCheck passwordCheck = new PasswordCheck();
    @Test
    public void passwordlowstrengthtest(){
        Employee employee = new Employee("", "", "", "", "pass");
     passwordCheck.WachtWoordCheck(employee.getPassword());
     passwordCheck.WachtWoordStengthVerwerker(employee.getPassword());
    }
    @Test (expected = IllegalArgumentException.class)
    public void passwordlengthtest(){
        Employee employee = new Employee("", "", "", "", "pass");
        passwordCheck.WachtWoordCheck(employee.getPassword());
        passwordCheck.WachtWoordStengthVerwerker(employee.getPassword());
    }

}
