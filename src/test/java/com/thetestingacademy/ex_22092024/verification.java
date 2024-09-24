package com.thetestingacademy.ex_22092024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class verification {

    @Test
    public  void test_verify(){

        String name = "Atanu";

        Assert.assertEquals("Atanu",name);

    }
}
