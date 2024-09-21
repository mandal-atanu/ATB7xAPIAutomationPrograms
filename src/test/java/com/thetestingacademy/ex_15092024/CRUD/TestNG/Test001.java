package com.thetestingacademy.ex_15092024.CRUD.TestNG;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Test001 {

    @Test
    public void test_get(){

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/700064").log().all()
                .when()
                .get()
                .then()
                .statusCode(200).log().all();

    }
}
