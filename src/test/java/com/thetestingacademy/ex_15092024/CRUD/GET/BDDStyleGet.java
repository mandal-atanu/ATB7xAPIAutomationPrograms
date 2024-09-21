package com.thetestingacademy.ex_15092024.CRUD.GET;

import io.restassured.RestAssured;

public class BDDStyleGet {
    public static void main(String[] args) {

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
