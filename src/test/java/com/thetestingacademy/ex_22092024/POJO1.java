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

        Map<String,Object> payload_Post = new LinkedHashMap();
        payload_Post.put("firstname","Atanu");
        payload_Post.put("lastname", "Mandal");
        payload_Post.put("totalprice", 111);
        payload_Post.put("depositpaid", true);
        payload_Post.put("additionalneeds", "Lunch");
        Map<String,Object> bookingDatesMap = new LinkedHashMap();
        bookingDatesMap.put("checkin", "2021-07-01");
        bookingDatesMap.put("checkout", "2021-07-01");

        payload_Post.put("bookingdates",bookingDatesMap);


        System.out.println(payload_Post);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);

        requestSpecification.body(payload_Post).log().all();

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
