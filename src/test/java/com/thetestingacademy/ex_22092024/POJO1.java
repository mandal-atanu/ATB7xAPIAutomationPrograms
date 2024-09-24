package com.thetestingacademy.ex_22092024;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class POJO1 {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String token;
    String bookingid;

    @Test
    public void test_crate_booking_hashmap() {

        Map<String, Object> jsonbodyusingmap = new LinkedHashMap<>();
        jsonbodyusingmap.put("fisrtname","Atanu");
        jsonbodyusingmap.put("lastname","Mandal");
        jsonbodyusingmap.put("total_price",111);
        jsonbodyusingmap.put("deposit_paid",true);

        Map<String,Object> bookingdatesmap = new LinkedHashMap<>();

        bookingdatesmap.put("checkin","2018-01-01");
        bookingdatesmap.put("checkout","2019-01-01");

        jsonbodyusingmap.put("bookingdates",bookingdatesmap);
        jsonbodyusingmap.put("additionalneeds","breakfast");

        System.out.println(jsonbodyusingmap);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);

        requestSpecification.body(jsonbodyusingmap).log().all();

        Response response = requestSpecification.when().post();

//        System.out.println(response.asString());
//
//        JsonPath json1 = new JsonPath(response.asString());

        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);

        bookingid = response.jsonPath().getString("bookingid");

        System.out.println(bookingid);





    }
}
