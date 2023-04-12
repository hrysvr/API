package baseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseURL {

    // https://jsonplaceholder.typicode.com

    protected RequestSpecification specJasonPlace;

    @Before
    public void setUp(){

        specJasonPlace = new RequestSpecBuilder()
                         .setBaseUri("https://jsonplaceholder.typicode.com")
                         .build();


    }

}
