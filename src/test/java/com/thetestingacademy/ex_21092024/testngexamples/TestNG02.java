package com.thetestingacademy.ex_21092024.testngexamples;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG02 {

    String token;
    Integer bookingid;

    @BeforeTest
    public String getToken(){
        token = "123";
        return  token;
    }

    @BeforeTest
    public void getTokenAndBookingID(){
        token = getToken();

        bookingid = 123;
    }

    @Test
    public void testPUTreq(){
        System.out.println(token);
        System.out.println(bookingid);
    }


    @Test
    public void testPUTreq2(){
        System.out.println(token);
        System.out.println(bookingid);
    }


    @Test
    public void testPUTreq3(){
        System.out.println(token);
        System.out.println(bookingid);
    }





}
