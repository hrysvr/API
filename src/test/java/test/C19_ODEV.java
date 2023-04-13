package test;

import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C19_ODEV extends JsonPlaceHolderBaseURL {

    /*

    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
    PUT request yolladigimizda donen response’in
    status kodunun 200, content type’inin “application/json; charset=utf-8”,
    Connection header degerinin “keep-alive”
    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz
    TEST DATA CLASS KULLANIMI

       Expected Data :
       {
       "title": "Ahmet",
       "body": "Merhaba",
       "userId": 10,
       "id": 70
       }

       Request Body
       {
       "title": "Ahmet",
       "body": "Merhaba",
       "userId": 10,
       "id": 70
       }

     */

    @Test
    public void put01(){

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        // 1 - URL ve Body hazirla

        specJasonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder1 = new TestDataJsonPlaceHolder();

        JSONObject reqBody = testDataJsonPlaceHolder1.expectedBodyOlusturJson();

        // 2 - Expected Data hazirla

        JSONObject expData = testDataJsonPlaceHolder1.expectedBodyOlusturJson();

        // 3 - Response'i kaydet

        Response response =given().
                spec(specJasonPlace).
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                put("/{pp1}/{pp2}");

        // 4 - Assertion

        assertEquals(testDataJsonPlaceHolder1.basariliStatusCode,response.getStatusCode());
        assertEquals(testDataJsonPlaceHolder1.contentType,response.getContentType());
        assertEquals(testDataJsonPlaceHolder1.connectionHeaderDegeri,response.getHeader("Connection"));

        JsonPath resJP = response.jsonPath();

        assertEquals(expData.get( "title"),resJP.get( "title"));
        assertEquals(expData.get( "body"),resJP.get( "body"));
        assertEquals(expData.get( "userId"),resJP.get( "userId"));
        assertEquals(expData.get( "id"),resJP.get( "id"));


    }

}
