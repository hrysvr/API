package test;

import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;


public class C18_Get_TestDataClassKullanimi extends JsonPlaceHolderBaseURL {

    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
    request yolladigimizda donen response’in status kodunun 200 ve
    response body’sinin asagida verilen ile ayni oldugunu test ediniz

      Response body = Expected Body
         {
         "userId":3,
         "id":22,
         "title":"dolor sint quo a velit explicabo quia nam",
         "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
         um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
     */


    @Test
    public void get01(){

        // 1 - Url hazirla

        specJasonPlace.pathParams("pp1","posts","pp2",22);

        // 2 - Expected Data

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        JSONObject expData = testDataJsonPlaceHolder.expectedBodyOlusturJson();


        // 3 - Response'i kaydet

        Response response = given().spec(specJasonPlace).when().get("/{pp1}/{pp2}");

        // 4 - Assertion

        JsonPath resJP = response.jsonPath();

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode, response.getStatusCode());
        Assert.assertEquals(expData.get("userId"), resJP.get("userId"));
        Assert.assertEquals(expData.get("id"), resJP.get("id"));
        Assert.assertEquals(expData.get("title"), resJP.get("title"));
        Assert.assertEquals(expData.get("body"), resJP.get("body"));


    }

}
