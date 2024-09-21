package com.thetestingacademy.ex_21092024.testngexamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG08 {

    @Test
    public void test01(){
        Assert.assertTrue(false);
    }

    @Test(enabled = false )
    public void test02(){
        Assert.assertTrue(false);
    }

    @Test(alwaysRun = true)
    public void test03(){
        Assert.assertTrue(true);
    }
}
