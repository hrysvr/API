package test;

import baseURL.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_ODEV extends HerokuAppBaseUrl {

    /*
        1-  https://restful-booker.herokuapp.com/booking endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve Response’ta 24389 id'sine sahip bir booking oldugunu test edin
           {
           "firstname" : "Ahmet",
           "lastname" : “Bulut",
           "totalprice" : 500,
           "depositpaid" : false,
           "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"
                },
           "additionalneeds" : "wi-fi"
           }
    2- https://restful-booker.herokuapp.com/booking
    endpointine asagidaki body’ye sahip bir POST
    request gonderdigimizde donen response’un
    status code’unun 200 oldugunu ve “firstname”
    degerinin “Ahmet” oldugunu test edin

     */


    @Test
    public void get01(){

        // 1 - Url hazirla

        specHerokuApp.pathParam("pp1","booking");

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response = given().spec(specHerokuApp).when().get("/{pp1}");

        response.prettyPrint();

        // 4 - Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(2148));


    }



    @Test
    public void post01(){
        /*

        {
           "firstname" : "Ahmet",
           "lastname" : “Bulut",
           "totalprice" : 500,
           "depositpaid" : false,
           "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"
                },
           "additionalneeds" : "wi-fi"
           }

         */

        // 1 - Url ve Body hazirla

        specHerokuApp.pathParam("pp1","booking");

        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin" , "2021-06-01");
        bookingdates.put("checkout" , "2021-06-10");

        JSONObject expBody = new JSONObject();

        expBody.put("firstname" , "Ahmet");
        expBody.put("lastname" , "Bulut");
        expBody.put("totalprice" , 500);
        expBody.put("depositpaid" , false);
        expBody.put("bookingdates",bookingdates);
        expBody.put("additionalneeds" , "wi-fi");


        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response = given()
                .spec(specHerokuApp)
                .contentType(ContentType.JSON)
                .when()
                .body(expBody.toString())
                .post("/{pp1}");

        // 4 - Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname",Matchers.equalTo("Ahmet"));

    }
}
