package com.thetestingacademy.ex_15092024.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class NonBDDStylewithfunction {

    static RequestSpecification r = RestAssured.given();


    public static void main(String[] args) {

        r.baseUri("https://api.zippopotam.us").log().all();

        testnonbdd1();

        testnonbdd2();

    }

    private static void testnonbdd2() {

        r.basePath("/IN/700121").log().all();
        r.when().get();
        r.then().log().all().statusCode(200);


    }

    private static void testnonbdd1() {

        r.basePath("/IN/700064").log().all();
        r.when().get();
        r.then().log().all().statusCode(200);
    }


}
