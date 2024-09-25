package com.thetestingacademy.ex_22092024.integration_scenario_test_2;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class IntegrationScenario1 {


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
        Map<String,Object> Payload_post = new LinkedHashMap();

        Payload_post.put("firstname","John");
        Payload_post.put("lastname","Brown");
        Payload_post.put("totalprice",111);
        Payload_post.put("depositpaid",true);


        Map<String,Object> bookingdates = new LinkedHashMap();

        bookingdates.put("Checkin","2018-01-01");
        bookingdates.put("Checkout","2019-01-01");

        Payload_post.put("bookingdates",bookingdates);
        Payload_post.put("additionalneeds","breakfast");

        System.out.println(Payload_post);



        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(Payload_post);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingid = response.then().extract().path("bookingid");
        System.out.println("booking id is "+bookingid);

    }


}
