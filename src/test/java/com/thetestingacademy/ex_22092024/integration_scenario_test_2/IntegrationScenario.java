package com.thetestingacademy.ex_22092024.integration_scenario_test_2;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.log.Priority;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

public class IntegrationScenario {


    ValidatableResponse validatableResponse;
    RequestSpecification requestSpecification;
    Response response;
    String bookingid;
    String token;

    @Test(priority =  1)
    @Description("Create token")
    public void createauthtoken(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
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



        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_Post);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200).log().all();

        bookingid = response.jsonPath().getString("bookingid");
        System.out.println(bookingid);

    }


}
