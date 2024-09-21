package com.thetestingacademy.ex_21092024.testngexamples;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG04 {

    @Test(priority = 1)
    public void gettoken(){
        System.out.println("2");
    }

    @Test(priority = 2)
    public void getid(){
        System.out.println("1");
    }

    @Test(priority = 3)
    public void test_put(){
        System.out.println("3");
    }
}
