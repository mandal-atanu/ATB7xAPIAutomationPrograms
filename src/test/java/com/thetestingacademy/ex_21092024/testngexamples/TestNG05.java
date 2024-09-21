package com.thetestingacademy.ex_21092024.testngexamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG05 {

    @Test(groups = {"Sanity","QA"})
    public void sanityrun(){
        System.out.println("Sanity");
        System.out.println("QA");
    }

    @Test(groups = {"QA"})
    public void Regrun(){
        System.out.println("Regression");
    }

    @Test(groups = {"Dev"})
    public void smokerun(){
        System.out.println("smoke");
        Assert.assertTrue(false);
    }
}
