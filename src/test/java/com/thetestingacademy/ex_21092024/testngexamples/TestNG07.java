package com.thetestingacademy.ex_21092024.testngexamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG07 {
    @Parameters("browser")
    @Test
    void demo1(String value){
        System.out.println("I am running " +value);
    }
}
