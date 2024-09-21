package com.thetestingacademy.ex_21092024.testngexamples;

import org.testng.annotations.Test;

public class TestNG06 {

    @Test
    public void serverstartok(){
        System.out.println("server starts");
    }
   @Test(dependsOnMethods = "serverstartok")
    public void method(){
        System.out.println("fffffffffff");
    }
}
