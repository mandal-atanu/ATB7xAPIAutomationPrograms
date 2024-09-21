package com.thetestingacademy.ex_21092024.testngexamples.CRUD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;


public class TestcaseIntegration {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;

    String token;
    String bookingid;

    // create token
    // create bookingid
    //perform a put resquest
    // verify that the put is sucesss by get request
    // Delete the id
    // verify it deosnt exist in get request
    @BeforeTest
    public String gettoken(){

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

        token = response.jsonPath().getString("token");
        System.out.println(token);
        return token;

    }
    @BeforeTest
    public String getbookingid(){

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

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200).log().all();

        bookingid = response.jsonPath().getString("bookingid");

        System.out.println(bookingid);

        return bookingid;

    }

    @Test(priority = 1)
    public void test_update_req_put(){

        token = gettoken();
        bookingid = getbookingid();


        String payload = "{\n" +
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
    @Test(priority = 2)
    public void test_update_request_get(){

        //bookingid = getbookingid();

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
    public void test_update_request_delete_bookingid(){
        bookingid = getbookingid();
        token = gettoken();

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/" +bookingid);
        requestSpecification.cookie("token",token);
        requestSpecification.log().all();

        response = requestSpecification.delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

        assertThat(validatableResponse.extract().statusCode()).isEqualTo(201);





    }

    @Test(priority = 4)
    public void test_after_delete_request_get(){
        requestSpecification = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/booking/" + bookingid);

        response = requestSpecification.get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

        assertThat(validatableResponse.extract().statusCode()).isEqualTo(404);



    }






}
