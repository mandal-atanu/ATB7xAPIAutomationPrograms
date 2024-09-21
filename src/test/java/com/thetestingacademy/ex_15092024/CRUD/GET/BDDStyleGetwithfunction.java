package com.thetestingacademy.ex_15092024.CRUD.GET;

import io.restassured.RestAssured;

public class BDDStyleGetwithfunction {
    public static void main(String[] args) {

        test1();

        test2();






    }

    private static void test2() {
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/700091").log().all()
                .when()
                .get()
                .then()
                .statusCode(200).log().all();


    }

    private static void test1() {

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
