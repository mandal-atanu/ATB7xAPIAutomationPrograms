package com.thetestingacademy.ex_21092024.testngexamples;

import org.apache.log.Priority;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG03 {

    @BeforeTest
    public void gettoken(){
        System.out.println("2");
    }

    @BeforeTest
    public void getid(){
        System.out.println("1");
    }

    @Test
    public void test_put(){
        System.out.println("3");
    }
}
