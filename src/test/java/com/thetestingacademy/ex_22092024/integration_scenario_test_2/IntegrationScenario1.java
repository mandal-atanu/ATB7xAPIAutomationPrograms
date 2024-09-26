package com.thetestingacademy.ex_22092024.integration_scenario_test_2;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.data.MapEntry;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class IntegrationScenario1 {


    ValidatableResponse validatableResponse;
    RequestSpecification requestSpecification;
    Response response;
    Integer bookingid;
    String token;

    @Test(priority =  1)
    @Description("Create token")
    public void createauthtoken(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.body(payload);
        requestSpecification.contentType(ContentType.JSON);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200).log().all();

        token = response.jsonPath().getString("token");

        System.out.println(token);


    }

    @Test(priority = 2)
    @Description("Get booking id")
    public  void getbookingid(){

        Map<String, Object> payload_Post = new LinkedHashMap();
        payload_Post.put("firstname","John");
        payload_Post.put("lastname", "Brown");
        payload_Post.put("totalprice", 111);
        payload_Post.put("depositpaid", true);
        payload_Post.put("additionalneeds", "Lunch");
        Map<String,Object> bookingDatesMap = new LinkedHashMap();
        bookingDatesMap.put("checkin", "2021-07-01");
        bookingDatesMap.put("checkout", "2021-07-01");

        payload_Post.put("bookingdates",bookingDatesMap);
//
        payload_Post.put("bookingdates",bookingDatesMap);


//        Map<String,Object> payload_Post = new LinkedHashMap();
//        payload_Post.put("firstname","John");
//        payload_Post.put("lastname", "Brown");
//        payload_Post.put("totalprice", 111);
//        payload_Post.put("depositpaid", true);
//        payload_Post.put("additionalneeds", "Lunch");
//        Map<String,Object> bookingDatesMap = new LinkedHashMap();
//        bookingDatesMap.put("checkin", "2021-07-01");
//        bookingDatesMap.put("checkout", "2021-07-01");
//
//        payload_Post.put("bookingdates",bookingDatesMap);

        System.out.println(payload_Post);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_Post);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

//        bookingid = response.jsonPath().getString("bookingid");
//        System.out.println("booking id is "+bookingid);

    }

    @Test(priority = 3)
    @Description("Update the Booking Name, Get the Booking by Id and verify.")
    public void updatebooking(){



        Map<String,Object> payloadPutRequest = new LinkedHashMap<>();
        payloadPutRequest.put("firstname","Atanu");
        payloadPutRequest.put("lastname", "Mandal");
        payloadPutRequest.put("totalprice", 111);
        payloadPutRequest.put("depositpaid", true);
        payloadPutRequest.put("additionalneeds", "Lunch");
        Map<String,Object> bookingDatesMapput = new LinkedHashMap<>();
        bookingDatesMapput.put("Checkin", "2021-07-01");
        bookingDatesMapput.put("Checkout", "2021-07-01");

        payloadPutRequest.put("bookingdates",bookingDatesMapput);






        System.out.println(payloadPutRequest);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payloadPutRequest).log().all();

        response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);



    }






}
