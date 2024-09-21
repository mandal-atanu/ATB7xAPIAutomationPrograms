package com.thetestingacademy.ex_15092024.CRUD.TestNG;

import org.testng.annotations.*;

public class Test002 {

    @BeforeSuite
    void demo1(){
        System.out.println("Before suite");
    }

    @BeforeTest
    void demo2(){
        System.out.println("Before test");

    }

    @BeforeClass
    void demo3(){
        System.out.println("Before class");
    }

    @BeforeMethod
    void demo4(){
        System.out.println("Before method");
    }

    @Test
    void demo5(){
        System.out.println("test");
    }

    @AfterMethod
    void demo6(){
        System.out.println("After method");

    }

    @AfterClass
    void demo7(){
        System.out.println("After class");

    }

    @AfterTest
    void demo8(){
        System.out.println("after test");
    }

    @AfterSuite
    void demo9(){
        System.out.println("After suite");
    }



}
