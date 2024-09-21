package com.thetestingacademy.ex_15092024.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDStylePUT {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;

    @Test
    public void test_put_positve_tc(){

        String token = "26b6091f21d4f4a";

        String bookingid = "5320";

        String payload = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload).log().all();

        response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200).log().all();



    }

}
