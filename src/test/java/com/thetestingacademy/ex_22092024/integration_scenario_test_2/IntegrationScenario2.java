package com.thetestingacademy.ex_22092024.integration_scenario_test_2;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationScenario2 {


    ValidatableResponse validatableResponse;
    RequestSpecification requestSpecification;
    Response response;
    String bookingid;
    String token;

    @BeforeTest
    public String createauthtoken(){

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

        return  token;


    }

    @BeforeTest
    @Description("Get booking id")
    public  String getbookingid(){

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
        validatableResponse.statusCode(200).log().all();

      bookingid = response.jsonPath().getString("bookingid");

        System.out.println(bookingid);

        return bookingid;

    }

    @Test(priority = 1)
    @Description("Update the Booking Name, Get the Booking by Id and verify.")
    public void updatebooking(){

        token = createauthtoken();




        bookingid = getbookingid();



        String payloadPutRequest = "{\n" +
                "    \"firstname\" : \"Atanu\",\n" +
                "    \"lastname\" : \"Mandal\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";





        System.out.println(payloadPutRequest);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payloadPutRequest).log().all();

        response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200).log().all();



    }

    @Test(priority = 2)
    @Description("Validatiing the firstname")
    public void getbooking(){

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingid);




        response = requestSpecification.when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200).log().all();

        String firstname = response.then().extract().path("firstname");

        String lastname = response.then().extract().path("lastname");

        assertThat(firstname).isEqualTo("Atanu").isNotEmpty().isNotBlank();
        assertThat(lastname).isEqualTo("Mandal").isNotEmpty().isNotBlank();

        System.out.println(bookingid);
        System.out.println(firstname);
        System.out.println(lastname);



    }

    @Test(priority = 3)
    @Description("Delete booking")
    public void deletebooking(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.log().all();

        response = requestSpecification.when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);



    }

    @Test(priority = 4)
    @Description("Create a Booking, Delete the Booking with Id and Verify using GET request that it should not exist.")
    public void fetchdataafterdeleteBooking(){

       requestSpecification = RestAssured.given();
       requestSpecification.baseUri("https://restful-booker.herokuapp.com");
       requestSpecification.basePath("/booking/"+bookingid);

       response = requestSpecification.when().get();

       validatableResponse = response.then().log().all();
       validatableResponse.statusCode(404);
    }

    @Test(priority = 5)
    @Description("Trying to Update on a Delete Id -> 404")
    public void updateDataAfterdeleteBooking(){
        String pl = "{\n" +
                "    \"firstname\" : \"Pramod\",\n" +
                "    \"lastname\" : \"Brownn\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(pl).log().all();
        response = requestSpecification.when().put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);
    }





}
