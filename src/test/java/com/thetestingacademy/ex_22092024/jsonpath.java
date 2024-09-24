package com.thetestingacademy.ex_22092024;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class jsonpath {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String bookingid;

    @Test
    public void  getbookingid() {

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
        requestSpecification.basePath("/booking/");
        requestSpecification.contentType(ContentType.JSON);

        requestSpecification.body(payload).log().all();

        response = requestSpecification.when().post();

        System.out.println(response.asString());

//        validatableResponse = response.then().log().all();
//        validatableResponse.statusCode(200).log().all();
//
//        bookingid = response.jsonPath().getString("bookingid");
//
//        System.out.println(bookingid);
//
//        return bookingid;

        JsonPath jsonPath = new JsonPath(response.asString());

        //JsonPath jsonPath1 = JsonPath.from(payload);

        String firstname = jsonPath.getString("booking.firstname");

        System.out.println(firstname);

        assertThat(firstname).isEqualTo("James").isNotNull();


    }
}

