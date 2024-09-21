package com.thetestingacademy.ex_21092024.testngexamples;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG01 {


    @Description("Verify that true == true ")
    @Test
    public void testcase1(){
        Assert.assertEquals(true,true);
    }

    @Description("Verify that false!= true ")
    @Test
    public void testcase2(){
        Assert.assertEquals(true,false);
    }
}
