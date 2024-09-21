package com.thetestingacademy.ex_15092024.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class NONBDDStyle {

    public static void main(String[] args) {

        String payload = "{\n" +
                "                \"username\" : \"admin\",\n" +
                "                \"password\" : \"password123\"\n" +
                "        }";

       // Given - Request Spec
        RequestSpecification r =  RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON).log().all();
        r.body(payload);

        // when  response
        Response response = r.when().post();
        // validation
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200).log().all();
    }
}
