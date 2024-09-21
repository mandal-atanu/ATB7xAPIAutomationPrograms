package com.thetestingacademy.ex_15092024.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BDDStyle {

    public static void main(String[] args) {

        String payload = "{\n" +
                "                \"username\" : \"admin\",\n" +
                "                \"password\" : \"password123\"\n" +
                "        }";


        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON).log().all().body(payload)
                .when()
                .post()
                .then()
                .statusCode(200).log().all();
    }
}
